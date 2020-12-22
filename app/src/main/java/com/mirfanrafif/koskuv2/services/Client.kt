package com.mirfanrafif.koskuv2.services
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
     fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://kosku-service.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
     }

    fun getAnakKosService() : AnakKosService{
        return getRetrofit().create(AnakKosService::class.java)
    }

    fun getPembayaranService() : PembayaranService {
        return getRetrofit().create(PembayaranService::class.java)
    }

    fun getPengeluaranService() : PengeluaranService {
        return getRetrofit().create(PengeluaranService::class.java)
    }


}