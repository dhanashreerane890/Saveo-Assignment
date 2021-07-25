package com.masai.saveo_assignment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.masai.saveo_assignment.model_classes.ImageModel
import com.masai.saveo_assignment.model_classes.ResponseModel
import com.masai.saveo_assignment.model_classes.ShowModel
import kotlinx.android.synthetic.main.horizontal_slider_item_layout.view.*

class HorizontalSliderAdapter(val responseList: MutableList<ResponseModel>,val viewPager2: ViewPager2):RecyclerView.Adapter<HorizontalSliderAdapter.HorizontalSliderViewHolder>(){


    // view holder class
    class HorizontalSliderViewHolder (view:View) :RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalSliderViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.horizontal_slider_item_layout,parent,false)
        Log.d("TAG", "onCreateViewHolder: "+ responseList.size)
        return HorizontalSliderViewHolder(view)

    }

    override fun onBindViewHolder(holder: HorizontalSliderViewHolder, position: Int) {
        val result = responseList[position].show
//        Log.d("TAG", "onBindViewHolder: "+ result?.image?.original)
        Log.d("TAG", "onBindViewHolder: "+ responseList)
        if (result != null) {
            Glide.with(holder.itemView.imageSlide).load(result?.image?.original).into(holder.itemView.imageSlide)
        }

        // implementation of horizontal slider
        if(position==responseList.size-2){
            viewPager2.post(runnable)

        }

    }
    val runnable = Runnable {
        responseList.addAll(responseList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return responseList.size
    }


}