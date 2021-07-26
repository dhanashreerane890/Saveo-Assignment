package com.masai.saveo_assignment.viewmodel

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.masai.saveo_assignment.model_classes.ResponseModel
import com.masai.saveo_assignment.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel :ViewModel(){
    val repository = MovieRepository()

    fun getMyMovies(q:Int):LiveData<List<ResponseModel>>{
        return liveData(Dispatchers.IO){
          val result =repository.getMovies(q)
            emit(result.data!!)
        }
    }

}