package com.ajs.virtusademoapp.data

import retrofit2.http.GET

interface APIs {
    @GET("api/users")
    suspend fun getUsersDetails(): UserDetails
}
