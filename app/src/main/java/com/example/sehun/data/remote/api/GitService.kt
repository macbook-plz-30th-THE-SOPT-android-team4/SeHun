package com.example.sehun.data.remote.api

import com.example.sehun.data.remote.response.ResponseHome
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitService {
    @GET("/users/{username}/following")
    fun getGit(
        @Path("username") username: String
    ): Call<List<ResponseHome>>
}
