package at.vipaso.assign.api

import at.vipaso.assign.response.ListUsersResponse
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Response


class ApiServiceTest {
    @get:Throws(Exception::class)
    @get:Test
    val users_withValidUsername_shouldReturnUserData: Unit
        get() {
            val apiService = Mockito.mock(ApiService::class.java)
            val mockCall: Call<ListUsersResponse> = Mockito.mock(Call::class.java) as Call<ListUsersResponse>

            Mockito.`when`(mockCall.execute()).thenReturn(Response.success())
            Mockito.`when`(apiService.getUsers("validUsername")).thenReturn(mockCall)

            val response = apiService.getUsers("validUsername").execute().body()

            // Assertions
            Assert.assertNotNull(response)
        }
}

