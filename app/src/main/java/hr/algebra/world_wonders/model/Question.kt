package hr.algebra.world_wonders.model

import com.google.android.gms.maps.model.LatLng
import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val A: String,
    val B: String,
    val C: String,
    val question: String,
    val D: String,
    val answer: String,
    val image : String
    )
