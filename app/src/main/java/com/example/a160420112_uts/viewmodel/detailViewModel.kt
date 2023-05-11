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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class detailViewModel(application: Application):
    AndroidViewModel(application) {
    val DonationsLD = MutableLiveData<Donations>()
    val DonationsLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun fetch(id: String) {
        loadingLD.value = true
        DonationsLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url =  "https://my-json-server.typicode.com/StevenLabina/UbayaDonation/posts?id="+id
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Donations>>() {}.type
                val result = Gson().fromJson<ArrayList<Donations>>(it, sType)[0]
                DonationsLD.value = result
                loadingLD.value = false
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                DonationsLoadErrorLD.value = false
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

}
