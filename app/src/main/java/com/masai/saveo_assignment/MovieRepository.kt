package com.masai.saveo_assignment

import com.masai.saveo_assignment.model_classes.ResponseModel

class MovieRepository {

    val apiClient = RetrofitGenerator.getInstance().create(ApiClient::class.java)
    val responseHandler = ResponseHandler()

    suspend fun getMovies():Resource<List<ResponseModel>>{
        val result =apiClient.getAllMovies()
        return responseHandler.handleSuccess(result)

    }

}