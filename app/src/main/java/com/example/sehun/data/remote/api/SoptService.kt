package com.example.sehun.data.remote.api

import com.example.sehun.data.remote.request.RequestSignIn
import com.example.sehun.data.remote.request.RequestSignUp
import com.example.sehun.data.remote.response.ResponseHome
import com.example.sehun.data.remote.response.ResponseSignIn
import com.example.sehun.data.remote.response.ResponseSignUp
import com.example.sehun.data.remote.response.ResponseWrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SoptService {

    @POST("/auth/signup")
    fun postSignUp(
        @Body body: RequestSignUp
    ): Call<ResponseWrapper<ResponseSignUp>>

    @POST("/auth/signin")
    fun postSignIn(
        @Body body: RequestSignIn
    ): Call<ResponseWrapper<ResponseSignIn>>

    @GET("/users/{username}/following")
    fun getGit(
        @Path("username") username: String
    ): Call<List<ResponseHome>>
}
