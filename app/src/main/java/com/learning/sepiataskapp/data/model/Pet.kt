package com.learning.sepiataskapp.data.model

import com.google.gson.annotations.SerializedName

data class Pet(
    val title: String,
    @SerializedName("date_added")
    val dateAdded: String?,
    @SerializedName("image_url")
    val imageURL:String,
    @SerializedName("content_url")
    val contentURL:String
)