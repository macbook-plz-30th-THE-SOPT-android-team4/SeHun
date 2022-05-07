package com.example.sehun.data.remote.response

data class ResponseSignIn(
    val status: Int,
    val message: String,
    val data: Data

) {
    data class Data(
        val email: String,
        val name: String
    )
}
