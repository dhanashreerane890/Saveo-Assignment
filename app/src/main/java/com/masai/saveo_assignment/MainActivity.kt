package com.masai.saveo_assignment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.masai.saveo_assignment.model_classes.ResponseModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val movie_responseList: MutableList<ResponseModel> = mutableListOf()
    val nowShowing_responseList: MutableList<ResponseModel> = mutableListOf()

    lateinit var horizontalSliderAdapter: HorizontalSliderAdapter
    lateinit var nowShowingAdapter: NowShowingAdapter

    lateinit var viewPager2: ViewPager2
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nowShowingAdapter = NowShowingAdapter(nowShowing_responseList)

        rvNowShowing.layoutManager = GridLayoutManager(this, 3)
        rvNowShowing.adapter = nowShowingAdapter
        viewPager2 = ViewPager2(this)
        viewPager2 = findViewById(R.id.viewPagerImageSliderHorizontal)
        handler = Handler()
        setHorizontalSlider()
        callViewModel()


    }

    private fun callViewModel() {
        val viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        viewModel.getMyMovies().observe(this, Observer {
            movie_responseList.addAll(it as MutableList<ResponseModel>)
            nowShowing_responseList.addAll(it)
            nowShowingAdapter.notifyDataSetChanged()
        })
    }


    private fun setHorizontalSlider() {
        horizontalSliderAdapter = HorizontalSliderAdapter(movie_responseList, viewPager2)
        viewPager2.adapter = horizontalSliderAdapter
        viewPager2.clipToPadding = false;
        viewPager2.clipChildren = false;
        viewPager2.offscreenPageLimit = 3;

        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER;
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(15))

        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        viewPager2.setPageTransformer(compositePageTransformer)
        viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(sliderRunnable)
                handler.postDelayed(sliderRunnable, 3000) //slider duration 3 second
            }
        })
    }

    val sliderRunnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    override fun onPause() {
        super.onPause()
        handler.post(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(sliderRunnable, 2000)
    }
}