package com.masai.saveo_assignment

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.masai.saveo_assignment.model_classes.ResponseModel
import kotlinx.coroutines.Dispatchers

class MovieViewModel :ViewModel(){
    val repository = MovieRepository()

    fun getMyMovies():LiveData<List<ResponseModel>>{
        return liveData(Dispatchers.IO){
          val result =repository.getMovies()
            emit(result.data!!)
        }
    }

}