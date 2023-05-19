package hr.algebra.world_wonders.api

import hr.algebra.world_wonders.model.PageResult
import retrofit2.http.GET
import retrofit2.http.Query

interface WondersApi {
    @GET("raw/n0hJFFxR")
    suspend fun getWonders(
        @Query("page") page: Int = 1
    ) : PageResult
}