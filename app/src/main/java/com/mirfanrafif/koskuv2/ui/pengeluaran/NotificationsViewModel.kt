package com.mirfanrafif.koskuv2.ui.pengeluaran

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mirfanrafif.koskuv2.models.Pengeluaran
import com.mirfanrafif.koskuv2.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsViewModel : ViewModel() {

    fun getAllPengeluaran() : MutableLiveData<List<Pengeluaran>> {
        val listPengeluaran  = MutableLiveData<List<Pengeluaran>>()
        Client().getPengeluaranService().getAllPengeluaran()
            .enqueue(object : Callback<List<Pengeluaran>> {
            override fun onResponse(
                call: Call<List<Pengeluaran>>,
                response: Response<List<Pengeluaran>>
            ) {
                listPengeluaran.value = response.body()
            }

            override fun onFailure(call: Call<List<Pengeluaran>>, t: Throwable) {
                t.printStackTrace()
            }

        })
        return listPengeluaran
    }
}