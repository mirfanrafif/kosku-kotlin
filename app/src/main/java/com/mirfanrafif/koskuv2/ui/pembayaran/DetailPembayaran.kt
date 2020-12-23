package com.mirfanrafif.koskuv2.ui.pembayaran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.mirfanrafif.koskuv2.MainActivity
import com.mirfanrafif.koskuv2.R
import com.mirfanrafif.koskuv2.databinding.ActivityDetailPembayaranBinding
import com.mirfanrafif.koskuv2.models.Pembayaran
import com.mirfanrafif.koskuv2.services.Client
import com.mirfanrafif.koskuv2.viewmodel.DetailPembayaranViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPembayaran : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPembayaranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_pembayaran)



        val intent = intent
        val id = intent.getStringExtra("EXTRA_ID")!!
        val viewModel = DetailPembayaranViewModel(id)

        viewModel.pembayaran.observe(this, {
            binding.viewModel = viewModel
        })

        binding.DetailPembayaranUbahButton.setOnClickListener {
            val intent = Intent(binding.DetailPembayaranUbahButton.context,
                UbahPembayaranActivity::class.java)
            intent.putExtra("EXTRA_ID", id)
            startActivity(intent)
            finish()
        }

        binding.DetailPembayaranHapusButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setTitle("Hapus Data")
                .setMessage("Apakah anda yakin ingin menghapus data ini")
            builder.setPositiveButton("Ya") { alertDialog, which ->
                hapusData(id)
            }

            builder.setNegativeButton("Tidak") { alertDialog, which ->
                alertDialog.cancel()
            }

            val alertDialog = builder.create()
            alertDialog.show()

        }
    }

    private fun hapusData(id: String) {
        Client().getPembayaranService().deletePembayaran(id).enqueue(object : Callback<Pembayaran> {
            override fun onResponse(call: Call<Pembayaran>, response: Response<Pembayaran>) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onFailure(call: Call<Pembayaran>, t: Throwable) {
                Toast.makeText(applicationContext, "Error : " + t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}