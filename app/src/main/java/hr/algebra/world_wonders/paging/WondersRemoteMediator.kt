package hr.algebra.world_wonders.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import hr.algebra.world_wonders.api.WondersApi
import hr.algebra.world_wonders.db.WonderDatabase
import hr.algebra.world_wonders.model.Wonder
import hr.algebra.world_wonders.model.WonderRemoteKeys

@ExperimentalPagingApi
class WondersRemoteMediator(
    private val wondersApi: WondersApi,
    private val wonderDatabase: WonderDatabase
) : RemoteMediator<Int, Wonder>() {
    private val wonderDao = wonderDatabase.wonderDao()
    private val wonderRemoteKeysDao = wonderDatabase.wonderRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Wonder>): MediatorResult {
        Log.d("MEDIATOR", loadType.toString())
        return try {

            val currentPage = when(loadType) {
                LoadType.REFRESH -> {
                    val wonderRemoteKeys: WonderRemoteKeys? = getWonderRemoteKeysClosestToCurrentPosition(state)
                    wonderRemoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val wonderRemoteKeys: WonderRemoteKeys? = getWonderRemoteKeysForFirstItem(state)
                    val prevPage = wonderRemoteKeys?.prevPage
                        ?: return MediatorResult.Success(wonderRemoteKeys != null)
                    prevPage
                }
                LoadType.APPEND -> {
                    val wonderRemoteKeys: WonderRemoteKeys? = getWonderRemoteKeysForLastItem(state)
                    val nextPage = wonderRemoteKeys?.nextPage
                        ?: return MediatorResult.Success(wonderRemoteKeys != null)
                    nextPage
                }
            }

            val response = wondersApi.getWonders(page = currentPage)
            val endOfPaginationReached = response.wonders.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            wonderDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    wonderDao.deleteWonders()
                    wonderRemoteKeysDao.deleteWonderRemoteKeys()
                }
                val wonderRemoteKeys = response.wonders.map { wonder ->
                    WonderRemoteKeys(
                        id = wonder.wonderId,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }

                wonderRemoteKeysDao.addWonderRemoteKeys(wonderRemoteKeys = wonderRemoteKeys)
                wonderDao.addWonder(wonders = response.wonders)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getWonderRemoteKeysForFirstItem(state: PagingState<Int, Wonder>): WonderRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { wonder ->
            wonderRemoteKeysDao.getWonderRemoteKeys(id = wonder.id)
        }
    }

    private suspend fun getWonderRemoteKeysForLastItem(state: PagingState<Int, Wonder>): WonderRemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { wonder ->
            wonderRemoteKeysDao.getWonderRemoteKeys(id = wonder.id)
        }
    }

    private suspend fun getWonderRemoteKeysClosestToCurrentPosition(state: PagingState<Int, Wonder>): WonderRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                wonderRemoteKeysDao.getWonderRemoteKeys(id = id)
            }
        }
    }
}