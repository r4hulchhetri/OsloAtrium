package com.rahul.osloatrium.model.base

data class BaseResponse<T>(
    val body: T,
    val status: BaseStatus
)