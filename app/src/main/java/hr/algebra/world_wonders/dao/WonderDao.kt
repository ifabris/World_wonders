package hr.algebra.world_wonders.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import hr.algebra.world_wonders.model.Wonder

@Dao
interface WonderDao {
    @Query("SELECT * FROM wonders_table")
    fun getWonders() : PagingSource<Int, Wonder>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWonder(wonders: List<Wonder>)

    @Query("DELETE FROM wonders_table")
    suspend fun deleteWonders()

    @Update
    suspend fun update(wonder: Wonder)

    @Delete
    suspend fun delete(wonder: Wonder)
}