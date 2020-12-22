package com.mirfanrafif.koskuv2.ui.dashboard

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirfanrafif.koskuv2.models.AnakKos
import com.mirfanrafif.koskuv2.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {
    fun getAllAnakKos() : MutableLiveData<List<AnakKos>> {
        val listAnakKos : MutableLiveData<List<AnakKos>> = MutableLiveData()
        Client().getAnakKosService().getAllAnakKos().enqueue(object : Callback<List<AnakKos>> {
            override fun onResponse(call: Call<List<AnakKos>>, response: Response<List<AnakKos>>) {
                listAnakKos.value = response.body()
            }

            override fun onFailure(call: Call<List<AnakKos>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return listAnakKos
    }
}