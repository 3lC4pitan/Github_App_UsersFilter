package at.vipaso.assign.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.vipaso.assign.response.Event
import at.vipaso.assign.response.ItemsItem
import at.vipaso.assign.response.ListUsersResponse
import at.vipaso.assign.api.ApiGithub
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {


    private val _itemsItem = MutableLiveData<List<ItemsItem>>()
    val itemsItem: LiveData<List<ItemsItem>> = _itemsItem


    private val _setLoading = MutableLiveData<Boolean>()
    val setLoading: LiveData<Boolean> = _setLoading


    private val _resultFound = MutableLiveData<Int>()
    val resultFound: LiveData<Int> = _resultFound

    private val _errorMsg = MutableLiveData<Event<String>>()
    val errorMsg: LiveData<Event<String>> = _errorMsg


    fun searchUser(query: String) {
        //_setLoading.value = true
        val client = ApiGithub.getApiService().getUsers(query)
        client.enqueue(object : Callback<ListUsersResponse> {
            override fun onResponse(
                call: Call<ListUsersResponse>,
                response: Response<ListUsersResponse>
            ) {
                //_setLoading.value = false
                if(response.isSuccessful){
                    val responseBody = response.body()

                    _resultFound.value = responseBody!!.totalCount
                    _itemsItem.value = responseBody.items
                }else{
                    _errorMsg.value = Event("error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ListUsersResponse>, t: Throwable) {
                //_setLoading.value = false
                _errorMsg.value = Event("error: ${t.message}")
            }

        })
    }



}