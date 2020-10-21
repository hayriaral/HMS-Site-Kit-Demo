package com.huawei.hayriaral.sitekitdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.huawei.hayriaral.sitekitdemo.model.RestaurantModel
import com.huawei.hayriaral.sitekitdemo.model.service.SiteKitSearchService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RestaurantListViewModel : ViewModel() {
    private val siteKitSearchService = SiteKitSearchService()

    val restaurants = MutableLiveData<List<RestaurantModel>>()
    val restaurantLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchRestaurants()
    }

    private fun fetchRestaurants() {
        loading.value = true
        disposable.add(
            siteKitSearchService.getRestaurants()
                .subscribeOn(Schedulers.newThread())
                .observeOn
        )
//        val mockData = listOf(
//            RestaurantModel("RestaurantA", "Istanbul", "Turkey", "1232 32132"),
//            RestaurantModel("RestB", "Istanbul", "Turkey", "1232 32132")
//        )
//
//        restaurantLoadError.value = false
//        loading.value = false
//        restaurants.value = mockData
    }
}