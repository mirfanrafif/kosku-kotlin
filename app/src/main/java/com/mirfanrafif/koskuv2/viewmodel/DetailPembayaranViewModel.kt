package com.mirfanrafif.koskuv2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mirfanrafif.koskuv2.models.Pembayaran
import com.mirfanrafif.koskuv2.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPembayaranViewModel(id: String) : ViewModel() {
    val pembayaran = MutableLiveData<Pembayaran>().apply {
        Client().getPembayaranService().getPembayaranById(id).enqueue(object : Callback<Pembayaran> {
            override fun onResponse(call: Call<Pembayaran>, response: Response<Pembayaran>) {
                value = response.body()
            }

            override fun onFailure(call: Call<Pembayaran>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}