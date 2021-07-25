package com.masai.saveo_assignment

import com.masai.saveo_assignment.model_classes.ResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {
    @GET("search/shows?q=god")
   suspend fun getAllMovies():List<ResponseModel>
}
//https://api.tvmaze.com/search/shows?q=god