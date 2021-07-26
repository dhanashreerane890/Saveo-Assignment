package com.masai.saveo_assignment.apiclient

import com.masai.saveo_assignment.model_classes.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("search/shows")
   suspend fun getAllMovies(@Query("q")q:Int):List<ResponseModel>
}
//https://api.tvmaze.com/search/shows?q=god