package com.ajs.virtusademoapp.data

import com.google.gson.annotations.SerializedName

data class UserSupport(
    @SerializedName("url") val url : String,
    @SerializedName("text") val text : String
)
