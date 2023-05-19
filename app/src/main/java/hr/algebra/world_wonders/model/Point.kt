package hr.algebra.world_wonders.model

import com.google.android.gms.maps.model.LatLng
import kotlinx.serialization.Serializable

@Serializable
data class Point(
    val name : String,
    val lat: Double,
    val lng: Double,
    val image: String
) {
    fun latLng() = LatLng(lat, lng)
}
