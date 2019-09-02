package com.rahul.osloatrium.model.response

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val email: String,
    val image: String
)