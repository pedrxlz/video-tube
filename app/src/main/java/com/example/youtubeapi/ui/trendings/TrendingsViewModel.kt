package com.example.youtubeapi.ui.trendings

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapi.model.VideoYtModel
import com.example.youtubeapi.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class TrendingsViewModel : ViewModel() {

    private val _video = MutableLiveData<VideoYtModel?>()
    val video = _video
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading
    private val _message = MutableLiveData<String>()
    val message = _message

    init {
        getVideoList()
    }

    private fun getVideoList() {
        _isLoading.value = true
        val client = ApiConfig.getService().getPlaylist("snippet", "PLEWxb4I8sM9IANpBRkCH-c7fL6sIAq-Ql", "10")
        client.enqueue(object : Callback<VideoYtModel> {
            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(call: Call<VideoYtModel>, response: Response<VideoYtModel>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null && data.items.isNotEmpty()){
                        _video.value = data
                    } else {
                        _message.value = "No video"
                    }
                } else {
                    _message.value = response.message()
                }

            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected exception
             * occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<VideoYtModel>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "Failed", t)
                _message.value = t.message
            }

        })
    }

    companion object {
        private val TAG = TrendingsViewModel::class.java.simpleName
    }

}


