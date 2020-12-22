package com.mirfanrafif.koskuv2.models

import com.google.gson.annotations.SerializedName

data class Pembayaran(
    @SerializedName("_id") var _id : String? = null,
    @SerializedName("idanakkos") var idanakkos : AnakKos?  = null,
    @SerializedName("bulan") var bulan : String? = null,
    @SerializedName("tahun") var tahun : Int? = null,
    @SerializedName("tampil") var tampil : Boolean? = null,
    @SerializedName("__v") var __v : Int? = null
)