package com.mirfanrafif.koskuv2.services

import com.mirfanrafif.koskuv2.models.AnakKos
import retrofit2.Call
import retrofit2.http.*

interface AnakKosService {
    @GET("anakkos")
    fun getAllAnakKos() : Call<List<AnakKos>>

    @POST("anakkos")
    fun saveAnakKos(@Body anakKos: AnakKos) : Call<AnakKos>

    @GET("anakkos/{id}")
    fun getAnakKosById(@Path("id") id : String) : Call<AnakKos>

    @PUT("anakkos/{id}")
    fun updateAnakKos(@Path("id") id : String, @Body anakKos: AnakKos) : Call<AnakKos>

    @DELETE("anakkos/{id}")
    fun deleteAnakKos(@Path("id") id: String) : Call<AnakKos>

}