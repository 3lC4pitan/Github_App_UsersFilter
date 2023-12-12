package at.vipaso.assign.api

import at.vipaso.assign.response.ListUsersResponse
import at.vipaso.assign.response.UserDetailResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("search/users")
    fun getUsers(@Query("q") username: String): Call<ListUsersResponse>

    @GET("users/{username}")
    fun getUserDetail(@Path("username") username: String): Call<UserDetailResponse>


}