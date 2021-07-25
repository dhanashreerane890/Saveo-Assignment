package com.masai.saveo_assignment

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        holder.itemView.setOnClickListener {
            val intent =Intent(holder.itemView.context,MovieDetailsActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
      return responseList.size
    }

}