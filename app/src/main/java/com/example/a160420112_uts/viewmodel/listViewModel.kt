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
import com.example.a160420112_uts.view.DonationFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class listViewModel(application: Application):
    AndroidViewModel(application) {
    val DonationsLD = MutableLiveData<ArrayList<Donations>>()
    val DonationsLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun refresh() {
        loadingLD.value = true
        DonationsLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://my-json-server.typicode.com/StevenLabina/UbayaDonation/posts"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Donations>>() {}.type
                val result = Gson().fromJson<List<Donations>>(it, sType)
                DonationsLD.value = result as ArrayList<Donations>?
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