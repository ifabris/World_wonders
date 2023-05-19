package hr.algebra.world_wonders.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wonder_remote_keys_table")
data class WonderRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
