package com.rahul.osloatrium.rest.service

import com.rahul.osloatrium.model.base.BaseResponse
import com.rahul.osloatrium.model.request.LoginBody
import com.rahul.osloatrium.model.response.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("auth/authenticate")
    fun login(@Body loginBody: LoginBody): Deferred<Response<BaseResponse<User>>>

}