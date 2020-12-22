package com.mirfanrafif.koskuv2.ui.home

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirfanrafif.koskuv2.models.Pembayaran
import com.mirfanrafif.koskuv2.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    fun getAllPembayaran() : MutableLiveData<List<Pembayaran>> {
        val listPembayaran : MutableLiveData<List<Pembayaran>> = MutableLiveData()
        Client().getPembayaranService().getAllPembayaran().enqueue(object :
            Callback<List<Pembayaran>> {
            override fun onResponse(
                call: Call<List<Pembayaran>>,
                response: Response<List<Pembayaran>>
            ) {
                listPembayaran.value = response.body()
            }

            override fun onFailure(call: Call<List<Pembayaran>>, t: Throwable) {
                t.printStackTrace()
            }

        })
        return listPembayaran
    }
}