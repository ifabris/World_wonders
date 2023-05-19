package hr.algebra.world_wonders.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.algebra.world_wonders.model.WonderRemoteKeys

@Dao
interface WonderRemoteKeysDao {
    @Query("SELECT * FROM wonder_remote_keys_table WHERE id=:id")
    suspend fun getWonderRemoteKeys(id: Int) : WonderRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWonderRemoteKeys(wonderRemoteKeys: List<WonderRemoteKeys>)

    @Query("DELETE FROM wonder_remote_keys_table")
    suspend fun deleteWonderRemoteKeys()
}