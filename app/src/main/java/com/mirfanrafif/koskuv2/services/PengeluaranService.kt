package com.mirfanrafif.koskuv2.services

import com.mirfanrafif.koskuv2.models.Pengeluaran
import retrofit2.Call
import retrofit2.http.*

interface PengeluaranService {
    @GET("pengeluaran")
    fun getAllPengeluaran() : Call<List<Pengeluaran>>

    @POST("pengeluaran")
    fun savePengeluaran(@Body pengeluaran: Pengeluaran) : Call<Pengeluaran>

    @GET("pengeluaran/{id}")
    fun getPengeluaranById(@Path("id") id:String) : Call<Pengeluaran>

    @DELETE("pengeluaran/{id}")
    fun deletePengeluaran(@Path("id") id:String) : Call<Pengeluaran>

    @PUT("pengeluaran/{id}")
    fun updatePengeluaran(@Path("id") id:String,
                          @Body pengeluaran: Pengeluaran) : Call<Pengeluaran>
}