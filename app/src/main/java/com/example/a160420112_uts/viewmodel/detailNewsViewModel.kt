package com.example.a160420112_uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420112_uts.model.Histories
import com.example.a160420112_uts.model.News
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class detailNewsViewModel(application: Application):
    AndroidViewModel(application) {
    val NewsLD = MutableLiveData<News>()
    val NewsLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun fetch(id: String) {
        loadingLD.value = true
        NewsLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url =  "https://my-json-server.typicode.com/StevenLabina/UbayaDonation/news?id="+id
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<News>>() {}.type
                val result = Gson().fromJson<ArrayList<News>>(it, sType)
                result.forEach { news->
                    if(news.idBerita.toString()==id){
                        NewsLD.value = news
                        loadingLD.value = false
                        Log.d("showvoley", result.toString())
                    }
                }
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