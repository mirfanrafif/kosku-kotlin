package com.mirfanrafif.koskuv2.services

import retrofit2.Call
import com.mirfanrafif.koskuv2.models.Pembayaran
import retrofit2.http.*

interface PembayaranService {
    @GET("pembayaran")
    fun getAllPembayaran() : Call<List<Pembayaran>>

    @POST("pembayaran")
    fun savePembayaran(@Body pembayaran: Pembayaran) : Call<Pembayaran>

    @GET("pembayaran/{id}")
    fun getPembayaranById(@Path("id") id: String) : Call<Pembayaran>

    @PUT("pembayaran/{id}")
    fun updatePembayaran(@Path("id") id: String, @Body pembayaran: Pembayaran) : Call<Pembayaran>

    @DELETE("pembayaran/{id}")
    fun deletePembayaran(@Path("id") id: String) : Call<Pembayaran>
}