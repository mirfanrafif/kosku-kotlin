package com.mirfanrafif.koskuv2.models

import com.google.gson.annotations.SerializedName

data class AnakKos(
    @SerializedName("_id") var _id : String? = null,
    @SerializedName("nama") var nama : String? = null,
    @SerializedName("asal") var asal : String? = null,
    @SerializedName("nohp") var nohp : String? = null,
    @SerializedName("tampil") var tampil : Boolean? = null,
    @SerializedName("__v") var __v : Int? = null
)