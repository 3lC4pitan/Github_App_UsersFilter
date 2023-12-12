package at.vipaso.assign.model

import at.vipaso.assign.api.ApiService
import at.vipaso.assign.response.ListUsersResponse
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Call


class MainViewModelTest {
    @Test
    fun testSearchUserSuccess() {
        val viewModel = MainViewModel()
        val mockApiService = Mockito.mock(ApiService::class.java)
        val response = ListUsersResponse() // Set response data as needed
        Mockito.`when`<Call<ListUsersResponse>>(mockApiService.getUsers(Mockito.anyString()))
            .thenReturn(Call.success(response))
        viewModel.searchUser("testQuery")

        // Assert the expected behavior or state changes in the viewModel
    }

    @Test
    fun testSearchUserFailure() {
        val viewModel = MainViewModel()
        val mockApiService = Mockito.mock(ApiService::class.java)
        Mockito.`when`<Call<ListUsersResponse>>(mockApiService.getUsers(Mockito.anyString()))
            .thenReturn(Call.error(500, create.create(get.get("application/json"), "")))
        viewModel.searchUser("errorQuery")

        // Assert the expected behavior or state changes in the viewModel
    }
}





