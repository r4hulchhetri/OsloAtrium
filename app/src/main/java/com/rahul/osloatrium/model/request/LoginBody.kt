package com.rahul.osloatrium.model.request

import com.google.gson.annotations.SerializedName

data class LoginBody(
    @SerializedName("username") val userName: String,
    val password: String
)