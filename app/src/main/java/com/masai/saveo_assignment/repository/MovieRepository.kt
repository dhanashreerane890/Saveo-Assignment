package com.masai.saveo_assignment.repository

import com.masai.saveo_assignment.apiclient.ApiClient
import com.masai.saveo_assignment.Resource
import com.masai.saveo_assignment.ResponseHandler
import com.masai.saveo_assignment.network.RetrofitGenerator
import com.masai.saveo_assignment.model_classes.ResponseModel

class MovieRepository {

    val apiClient = RetrofitGenerator.getInstance().create(ApiClient::class.java)
    val responseHandler = ResponseHandler()

    suspend fun getMovies(q:Int): Resource<List<ResponseModel>> {
        val result =apiClient.getAllMovies(q)
        return responseHandler.handleSuccess(result)

    }

}