package com.example.a160420112_uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420112_uts.model.Donations
import com.example.a160420112_uts.model.News
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class  listNewsViewModel(application: Application):
    AndroidViewModel(application) {
    val NewsLD = MutableLiveData<ArrayList<News>>()
    val NewsLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun refresh() {
        loadingLD.value = true
        NewsLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://my-json-server.typicode.com/StevenLabina/UbayaDonation/news"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<News>>() {}.type
                val result = Gson().fromJson<List<News>>(it, sType)
                NewsLD.value = result as ArrayList<News>?
                loadingLD.value = false
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                NewsLoadErrorLD.value = false
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}