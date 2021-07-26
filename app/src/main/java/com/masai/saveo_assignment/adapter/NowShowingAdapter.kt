package com.masai.saveo_assignment.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masai.saveo_assignment.MovieDetailsActivity
import com.masai.saveo_assignment.R
import com.masai.saveo_assignment.model_classes.ResponseModel
import kotlinx.android.synthetic.main.now_showing_item_layout.view.*

class NowShowingAdapter (val responseList:MutableList<ResponseModel>):RecyclerView.Adapter<NowShowingAdapter.NowShowingViewHolder>(){

    class NowShowingViewHolder(view : View):RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowShowingViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.now_showing_item_layout,parent,false)
        Log.d("TAG", "onCreateViewHolder: "+ responseList.size)
        return NowShowingViewHolder(view)
    }

    override fun onBindViewHolder(holder: NowShowingViewHolder, position: Int) {
        val result = responseList[position].show
        if (result != null) {
            Glide.with(holder.itemView.iv_NowShowing).load(result?.image?.original).into(holder.itemView.iv_NowShowing)
        }
        //data passing to scond activity
        holder.itemView.setOnClickListener {
            val intent =Intent(holder.itemView.context, MovieDetailsActivity::class.java)
            intent.putExtra("movieImage",result?.image?.original.toString())
            intent.putExtra("movieName",result?.name.toString())
            intent.putExtra("movieTime",result?.schedule?.time.toString())
            intent.putExtra("movieSummery",result?.summary.toString())
            intent.putExtra("movieDate",result?.premiered.toString())
            intent.putExtra("movieRating",result?.rating?.average.toString())
            intent.putExtra("movieReviews",result?.runtime.toString())
            intent.putExtra("movieUsers",result?.id.toString())
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
      return responseList.size
    }

}