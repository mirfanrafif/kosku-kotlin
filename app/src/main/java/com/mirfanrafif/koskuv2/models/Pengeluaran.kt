package com.mirfanrafif.koskuv2.models

import com.google.gson.annotations.SerializedName

data class Pengeluaran(

	@field:SerializedName("__v")
	var V: Int? = null,

	@field:SerializedName("_id")
	var id: String? = null,

	@field:SerializedName("tanggal")
	var tanggal: String? = null,

	@field:SerializedName("jenis_pengeluaran")
	var jenisPengeluaran: String? = null,

	@field:SerializedName("nama_pengeluaran")
	var namaPengeluaran: String? = null,

	@field:SerializedName("nominal")
	var nominal: Int? = null,

	@field:SerializedName("tampil")
	var tampil: Boolean? = null
)
