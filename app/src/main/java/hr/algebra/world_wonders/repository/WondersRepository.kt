package hr.algebra.world_wonders.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import hr.algebra.world_wonders.paging.WondersRemoteMediator
import hr.algebra.world_wonders.api.WondersApi
import hr.algebra.world_wonders.db.WonderDatabase
import hr.algebra.world_wonders.model.Wonder
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class WondersRepository @Inject constructor(
    private val wondersApi: WondersApi,
    private val wonderDatabase: WonderDatabase
) {
    fun getWonders(): Flow<PagingData<Wonder>> {
        val pagingSource = { wonderDatabase.wonderDao().getWonders() }

        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = WondersRemoteMediator(
                wondersApi,
                wonderDatabase
            ),
            pagingSourceFactory = pagingSource
        ).flow
    }

    suspend fun update(wonder: Wonder) = wonderDatabase.wonderDao().update(wonder)

    suspend fun delete(wonder: Wonder) = wonderDatabase.wonderDao().delete(wonder)

}