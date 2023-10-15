package com.ajs.virtusademoapp

import com.ajs.virtusademoapp.data.APIs
import com.ajs.virtusademoapp.data.UserData
import com.ajs.virtusademoapp.data.UserDetails
import com.ajs.virtusademoapp.data.UserSupport
import com.ajs.virtusademoapp.domain.SampleUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SampleUseCaseTest {
    // The class being tested
    private lateinit var sampleUseCase: SampleUseCase

    // Mock the APIs dependency
    @Mock
    private lateinit var apIs: APIs
    @Before
    fun setup() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this)
        sampleUseCase = SampleUseCase(apIs)


    }
    @Test
   fun invokeShouldReturnUserDetailsOnSuccessfulNetworkCall()= runBlocking {
        // Arrange
        val expectedResponse = UserDetails(
            page = 1,
            per_page = 6,
            total = 12,
            total_pages = 2,
            data = listOf(
                UserData(1, "george.bluth@reqres.in", "George", "Bluth", "https://reqres.in/img/faces/1-image.jpg"),
                UserData(2, "janet.weaver@reqres.in", "Janet", "Weaver", "https://reqres.in/img/faces/2-image.jpg"),
                UserData(3, "emma.wong@reqres.in", "Emma", "Wong", "https://reqres.in/img/faces/3-image.jpg")

            ),
            support = UserSupport(
                url = "https://google.com",
                text = "google is more powerful"
            )
        )

        `when`(apIs.getUsersDetails()).thenReturn(expectedResponse)

        // Act
        val result = sampleUseCase()

        // Assert
        assertEquals(expectedResponse, result)
    }


    @Test
    fun invokeShouldReturnEmptyUserDetailsOnEmptyNetworkResponse() = runBlocking {
        // Arrange
        val emptyResponse = UserDetails(
            page = 1,
            per_page = 6,
            total = 0,
            total_pages = 1,
            data = emptyList(),
            support = UserSupport(
                url = "https://google.com",
                text = "google is more powerful"
            )
        )

        `when`(apIs.getUsersDetails()).thenReturn(emptyResponse)

        // Act
        val result = sampleUseCase()

        // Assert
        assertEquals(emptyResponse, result)
    }


}