package hr.algebra.world_wonders.api

import hr.algebra.world_wonders.model.Point
import retrofit2.http.GET

interface PointsApi {
    @GET("/wonder_points.json")
    suspend fun getPoints(): Map<String, Point>
}