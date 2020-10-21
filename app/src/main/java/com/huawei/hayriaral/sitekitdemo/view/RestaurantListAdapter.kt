package com.huawei.hayriaral.sitekitdemo.view

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.huawei.hayriaral.sitekitdemo.R
import com.huawei.hayriaral.sitekitdemo.model.RestaurantModel
import kotlinx.android.synthetic.main.item_restaurant.view.*

class RestaurantListAdapter(var restaurants: ArrayList<RestaurantModel>) :
    RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>() {

    fun updateRestaurants(newRestaurants: List<RestaurantModel>) {
        restaurants.clear()
        restaurants.addAll(newRestaurants)
        notifyDataSetChanged()
    }

    class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val restaurantName = view.placeName
        private val restaurantAddress = view.placeAddress
        private val restaurantLocation = view.placeLocation

        fun bind(restaurant: RestaurantModel) {
            var location: String =
                restaurant.location.lat.toString() + " , " + restaurant.location.lng.toString()
            restaurantName.text = restaurant.name
            restaurantAddress.text = restaurant.address
            restaurantLocation.text = location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurants[position])
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }
}