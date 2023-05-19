package hr.algebra.world_wonders.db

import androidx.room.Database
import androidx.room.RoomDatabase
import hr.algebra.world_wonders.dao.WonderDao
import hr.algebra.world_wonders.dao.WonderRemoteKeysDao
import hr.algebra.world_wonders.model.Wonder
import hr.algebra.world_wonders.model.WonderRemoteKeys

@Database(entities = [Wonder::class, WonderRemoteKeys::class], version = 1, exportSchema = false)
abstract class WonderDatabase : RoomDatabase() {
    abstract fun wonderDao() : WonderDao
    abstract fun wonderRemoteKeysDao() : WonderRemoteKeysDao
}