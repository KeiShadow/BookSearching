package com.noga.booksearching.models

import com.google.gson.annotations.SerializedName

data class ItBook(
    @SerializedName("title")
    var title: String? = null,

    @SerializedName("subtitle")
    var subTitle: String? = null,

    @SerializedName("isbn13")
    var isbn13: String? = null,

    @SerializedName("price")
    var price: String? = null,

    @SerializedName("image")
    var image: String? = null,

    @SerializedName("url")
    var url: String? = null
){

    override fun toString(): String {
        return "Book(title= $title, subTitle= $subTitle, isbn13= $isbn13, price= $price, image=$image, url=$url)"
    }
}
