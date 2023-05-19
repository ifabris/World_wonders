package hr.algebra.world_wonders.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class PageResult(
    @SerialName("results")
    val wonders: List<Wonder>
)
