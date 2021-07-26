package com.masai.saveo_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        getMovieDetails()
        backArrow.setOnClickListener {
            onBackPressed()
        }
    }

     //data get via intent
    private fun getMovieDetails() {
       if(intent!=null && intent.extras != null){
           Glide.with(this).load(intent.getStringExtra("movieImage")).into(iv_MovieImage)
           tvMovieName.text = intent.getStringExtra("movieName")
           Log.d("TAG", "getMovieDetails: "+intent.getStringExtra("movieName"))
           tvSummery.text = intent.getStringExtra("movieSummery")
           tvDate.text = intent.getStringExtra("movieDate")
           tvTime.text =intent.getStringExtra("movieTime")
           tvReviews.text = intent.getStringExtra("movieReviews")
           tvUser.text = intent.getStringExtra("movieUsers")
           tvRating.text = intent.getStringExtra("movieRating")

       }
    }

}