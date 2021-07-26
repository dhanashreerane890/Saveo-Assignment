package com.masai.saveo_assignment

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.masai.saveo_assignment.adapter.HorizontalSliderAdapter
import com.masai.saveo_assignment.adapter.NowShowingAdapter
import com.masai.saveo_assignment.model_classes.ResponseModel
import com.masai.saveo_assignment.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val movie_responseList: MutableList<ResponseModel> = mutableListOf()
    val nowShowing_responseList: MutableList<ResponseModel> = mutableListOf()

    lateinit var horizontalSliderAdapter: HorizontalSliderAdapter
    lateinit var nowShowingAdapter: NowShowingAdapter

    lateinit var viewPager2: ViewPager2
    lateinit var handler: Handler

    // Pagination
    lateinit var mlayoutManager: GridLayoutManager
    private var isLoading: Boolean = true
    private var totalItemCount: Int = 0
    private var firstVisibleItemCount: Int = 0
    private var visibleItemCount: Int = 0
    private var previousTotal: Int = 0

    private var page = 1;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2 = ViewPager2(this)
        viewPager2 = findViewById(R.id.viewPagerImageSliderHorizontal)
        handler = Handler()
        setHorizontalSlider()
        callViewModel()

        nowShowingAdapter = NowShowingAdapter(nowShowing_responseList)
        mlayoutManager = GridLayoutManager(this, 3)
        rvNowShowing.layoutManager = mlayoutManager
        rvNowShowing.adapter = nowShowingAdapter
        pagination()

    }

    //pagination  code
    private fun pagination() {
        rvNowShowing.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = mlayoutManager.childCount
                totalItemCount = mlayoutManager.itemCount
                firstVisibleItemCount = mlayoutManager.findFirstVisibleItemPosition()
                if (isLoading) {
                    if (totalItemCount > previousTotal) {
                        previousTotal = totalItemCount;
                        page++
                        isLoading = false
                    }
                }
                if (!isLoading && (firstVisibleItemCount + visibleItemCount) >= totalItemCount) {
                    getNext()
                    isLoading = true
                }
            }
        })
    }

    private fun getNext() {
        val viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        viewModel.getMyMovies(page).observe(this, Observer {
            nowShowing_responseList.addAll(it)
            nowShowingAdapter.notifyDataSetChanged()
        })
    }


    // fetching data from server
    private fun callViewModel() {
        val viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        viewModel.getMyMovies(page).observe(this, Observer {
            movie_responseList.addAll(it as MutableList<ResponseModel>)
            nowShowing_responseList.addAll(it)
            nowShowingAdapter.notifyDataSetChanged()
        })
    }

          // horizontal slider implementation
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