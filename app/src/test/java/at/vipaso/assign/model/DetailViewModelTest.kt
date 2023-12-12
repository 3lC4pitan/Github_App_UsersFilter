package at.vipaso.assign.model

import at.vipaso.assign.api.ApiService
import at.vipaso.assign.response.UserDetailResponse
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Call


class DetailViewModelTest {
    @Test
    fun testGetUserDetailSuccess() {
        val viewModel = DetailViewModel()
        val mockApiService = Mockito.mock(ApiService::class.java)
        val response = UserDetailResponse() // Set response data as needed
        Mockito.`when`<Call<UserDetailResponse>>(mockApiService.getUserDetail(Mockito.anyString()))
            .thenReturn(Call.success(response))
        viewModel.getUserDetail("testUser")

        // Assert the expected behavior or state changes in the viewModel
    }

    @Test
    fun testGetUserDetailFailure() {
        val viewModel = DetailViewModel()
        val mockApiService = Mockito.mock(ApiService::class.java)
        Mockito.`when`<Call<UserDetailResponse>>(mockApiService.getUserDetail(Mockito.anyString()))
            .thenReturn(Call.error(404, create.create(get.get("application/json"), "")))
        viewModel.getUserDetail("nonExistingUser")

        // Assert the expected behavior or state changes in the viewModel
    }
}


