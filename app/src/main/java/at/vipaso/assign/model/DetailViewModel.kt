package at.vipaso.assign.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.vipaso.assign.response.Event
import at.vipaso.assign.response.UserDetailResponse
import at.vipaso.assign.api.ApiGithub
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

    private var _userDetailResponse = MutableLiveData<UserDetailResponse>()
    val userDetailResponse: LiveData<UserDetailResponse> = _userDetailResponse

    private var _setLoadingDetail = MutableLiveData<Boolean>()

    private var _errUserDetailMsg = MutableLiveData<Event<String>>()
    val errUserDetailMsg: LiveData<Event<String>> = _errUserDetailMsg



    fun getUserDetail(username: String) {

        //_setLoadingDetail.value = true

        // Start loading state
        _setLoadingDetail.postValue(true)

        val client = ApiGithub.getApiService().getUserDetail(username)
        client.enqueue(object: Callback<UserDetailResponse> {
            override fun onResponse(
                call: Call<UserDetailResponse>,
                response: Response<UserDetailResponse>
            ) {
                _setLoadingDetail.value = false
                if(response.isSuccessful){
                    val responseBody = response.body()

                    _userDetailResponse.value = responseBody!!
                }else{
                    _errUserDetailMsg.value = Event("error: API Rate Limiting")
                }
            }

            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                _setLoadingDetail.value = false
                _errUserDetailMsg.value = Event("error: ${t.message} - getUserDetail")
            }

        })
    }

}