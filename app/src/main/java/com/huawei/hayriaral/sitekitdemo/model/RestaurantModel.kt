package com.huawei.hayriaral.sitekitdemo.model

import com.huawei.hms.site.api.model.Coordinate

data class RestaurantModel(
    var name : String,
    var address : String,
    var location: Coordinate
)