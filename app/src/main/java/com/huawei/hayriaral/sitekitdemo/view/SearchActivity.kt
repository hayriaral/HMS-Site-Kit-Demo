package com.huawei.hayriaral.sitekitdemo.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.huawei.hayriaral.sitekitdemo.R
import com.huawei.hayriaral.sitekitdemo.viewmodel.RestaurantListViewModel
import kotlinx.android.synthetic.main.activity_main.*


class SearchActivity : AppCompatActivity() {

    lateinit var viewModel: RestaurantListViewModel
    private val restaurantsAdapter = RestaurantListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(RestaurantListViewModel::class.java)
        viewModel.refresh()

        restaurantsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = restaurantsAdapter
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.restaurants.observe(this, Observer { restaurants ->
            restaurants?.let {
                restaurantsList.visibility=View.VISIBLE
                restaurantsAdapter.updateRestaurants(it)
            }
        })

        viewModel.restaurantLoadError.observe(this, Observer { isError ->
            isError?.let {
                list_error.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    restaurantsList.visibility = View.GONE
                }
            }
        })
    }
}
