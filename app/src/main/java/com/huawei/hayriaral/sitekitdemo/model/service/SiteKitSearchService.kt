package com.huawei.hayriaral.sitekitdemo.model.service

import android.content.Context
import android.util.Log
import com.huawei.hayriaral.sitekitdemo.model.RestaurantModel
import com.huawei.hms.site.api.SearchResultListener
import com.huawei.hms.site.api.SearchService
import com.huawei.hms.site.api.SearchServiceFactory
import com.huawei.hms.site.api.model.*
import io.reactivex.Single
import java.net.URLEncoder


class SiteKitSearchService {
    lateinit var restaurants : ArrayList<RestaurantModel>
    //Get API KEY from AppGallery Connect
    private val API_KEY: String = "YOUR API KEY"

    //Declare a SearchService object.
    private var searchService: SearchService? = null

    //Instantiate the SearchService object.
    fun getSearchServiceInstance(context: Context): SearchService? {
        if (searchService == null) {
            searchService = SearchServiceFactory.create(context, encodeAPIKey())
        }
        return searchService
    }

    private fun encodeAPIKey(): String {
        return URLEncoder.encode(API_KEY, "utf-8")
    }

    //fun getRestaurantsByNearbySearch(): Single<List<RestaurantModel>>
    private fun setNearbySearchRequestBody(): NearbySearchRequest {
        val latitude: Double = 40.915904
        val longitude: Double = 29.156659
        val radius: Int = 1000
        val poiType: LocationType = LocationType.RESTAURANT
        val pageSize: Int = 20
        val pageIndex: Int = 1

        //Create a request body
        val request = NearbySearchRequest()
        request.setLocation(Coordinate(latitude, longitude))
        request.setPoiType(poiType)
        request.setRadius(radius)
        request.setPageSize(pageSize)
        request.setPageIndex(pageIndex)

        return request
    }

    //Call the nearby place search API
    fun callAPI(context: Context) {
        lateinit var restaurant: RestaurantModel
        getSearchServiceInstance(context)?.nearbySearch(
            setNearbySearchRequestBody(),
            object : SearchResultListener<NearbySearchResponse> {
                override fun onSearchResult(result: NearbySearchResponse?) {
                    val sites: List<Site> = result?.getSites() as List<Site>
                    for (site in sites) {
                        restaurant.name = site.getName()
                        restaurant.address = site.getFormatAddress()
                        restaurant.location = site.getLocation()
                        restaurants.add(restaurant)
                    }
                }

                override fun onSearchError(status: SearchStatus?) {
                    Log.i(
                        "onSearchError",
                        "Error Code: " + status?.getErrorCode() + "Error Message: " + status?.getErrorMessage()
                    )
                }
            })
    }

    fun getRestaurants(): Single<List<RestaurantModel>> {
        return getFirst
    }
}