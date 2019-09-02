package com.rahul.osloatrium.model.base

import com.google.gson.annotations.SerializedName

data class BaseStatus(
    val message: String,
    val code: String,
    @SerializedName("code_text") val codeText: String,
    @SerializedName("response_timestamp") val responseTimestamp: String
)