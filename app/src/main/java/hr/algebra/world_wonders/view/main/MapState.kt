package hr.algebra.world_wonders.view.main

import hr.algebra.world_wonders.model.Point

data class MapState(
    val points: List<Point> = emptyList(),
    val loading: Boolean = true

)