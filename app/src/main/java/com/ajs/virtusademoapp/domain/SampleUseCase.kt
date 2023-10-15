package com.ajs.virtusademoapp.domain

import com.ajs.virtusademoapp.data.APIs
import com.ajs.virtusademoapp.data.UserDetails
import javax.inject.Inject

class SampleUseCase @Inject constructor(
    private val apIs: APIs
) {
    suspend operator fun invoke(): UserDetails {
        val response = apIs.getUsersDetails()
        return response
    }
}