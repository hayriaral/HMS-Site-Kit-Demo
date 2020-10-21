package com.huawei.hayriaral.sitekitdemo.model.Repository

import android.content.Context
import com.huawei.hayriaral.sitekitdemo.model.service.SiteKitSearchService
import com.huawei.hayriaral.sitekitdemo.util.RequestResult
import com.huawei.hms.site.api.SearchResultListener
import com.huawei.hms.site.api.model.*

class SiteKitRepository() {
    //Create a request
    fun getRestaurants(
        context: Context,
        latitude: Double,
        longitude: Double,
        radius: Int,
        pageSize: Int,
        pageIndex: Int,
        requestResult: RequestResult<Any>
    ) {
        ////Create a request body
        val request = NearbySearchRequest()
        request.setPoiType(LocationType.RESTAURANT)
        request.setLocation(Coordinate(latitude, longitude))
        request.setRadius(radius)
        request.setPageSize(pageSize)
        request.setPageIndex(pageIndex)
        SiteKitSearchService().getSearchServiceInstance(context)
            ?.nearbySearch(request, object : SearchResultListener<NearbySearchResponse> {
                override fun onSearchResult(p0: NearbySearchResponse?) {
                    p0.let {
                        requestResult.onSuccess(it as Any)
                    }
                }

                override fun onSearchError(p0: SearchStatus?) {
                    requestResult.onFail()
                }

            })
//        val latitude : Double = 40.915904
//        val longitude : Double = 29.156659
//        val radius : Int = 1000
//        val poiType : LocationType = LocationType.RESTAURANT
//        val pageSize : Int = 20
//        val pageIndex : Int = 1
    }
}