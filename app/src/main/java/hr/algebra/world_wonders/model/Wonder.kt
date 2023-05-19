package hr.algebra.world_wonders.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "wonders_table")
@Serializable
data class Wonder(
    @PrimaryKey(autoGenerate = true)
    @kotlinx.serialization.Transient
    val wonderId: Int = 0,
    val id: Int,
    val name: String,
    val text: String,
    val backdrop_path: String,
    val country: String,
    val audio: String
)
