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
import com.example.a160420112_uts.model.Histories
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class listInboxViewModel(application: Application):
    AndroidViewModel(application) {
    val InboxLD = MutableLiveData<ArrayList<Histories>>()
    val InboxLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun refresh() {
        loadingLD.value = true
        InboxLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://my-json-server.typicode.com/StevenLabina/UbayaDonation/histories"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Histories>>() {}.type
                val result = Gson().fromJson<List<Histories>>(it, sType)
                InboxLD.value = result as ArrayList<Histories>?
                loadingLD.value = false
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                InboxLoadErrorLD.value = false
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}