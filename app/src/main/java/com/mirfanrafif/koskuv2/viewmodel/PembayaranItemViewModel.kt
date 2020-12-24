package com.mirfanrafif.koskuv2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mirfanrafif.koskuv2.models.Pembayaran

class PembayaranItemViewModel(pembayaran: Pembayaran) : ViewModel() {
    val pembayaran = MutableLiveData<Pembayaran>().apply {
        value = pembayaran
    }
}