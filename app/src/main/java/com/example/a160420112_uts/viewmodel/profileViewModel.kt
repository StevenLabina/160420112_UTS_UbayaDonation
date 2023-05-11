package com.example.a160420112_uts.viewmodel

import android.app.Application
import android.provider.ContactsContract.Profile
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160420112_uts.model.Donations
import com.example.a160420112_uts.model.Profiles
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class profileViewModel(application: Application):
    AndroidViewModel(application) {
    val ProfileLD = MutableLiveData<Profiles>()
    val ProfileLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun refresh() {
        loadingLD.value = true
        ProfileLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://my-json-server.typicode.com/StevenLabina/UbayaDonation/profile"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Profiles>>() {}.type
                val result = Gson().fromJson<ArrayList<Profiles>>(it, sType)[0]
                ProfileLD.value = result as Profiles?
                loadingLD.value = false
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                ProfileLoadErrorLD.value = false
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}