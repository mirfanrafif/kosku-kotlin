package com.mirfanrafif.koskuv2.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mirfanrafif.koskuv2.MainActivity
import com.mirfanrafif.koskuv2.R
import com.mirfanrafif.koskuv2.models.Pembayaran
import com.mirfanrafif.koskuv2.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPembayaran : AppCompatActivity() {
    private lateinit var idPembayaran: TextView
    private lateinit var bulan: TextView
    private lateinit var tahun: TextView
    private lateinit var namaAnakKos: TextView
    private lateinit var asal: TextView
    private lateinit var nohp: TextView
    private lateinit var ubahButton: Button
    private lateinit var hapusButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pembayaran)
        idPembayaran = findViewById(R.id.DetailPembayaran_idPembayaranText)
        bulan = findViewById(R.id.DetailPembayaran_bulanText)
        tahun = findViewById(R.id.DetailPembayaran_tahunText)
        namaAnakKos = findViewById(R.id.DetailPembayaran_namaAnakKos)
        asal = findViewById(R.id.DetailPembayaran_asalAnakKos)
        nohp = findViewById(R.id.DetailPembayaran_nohpAnakKos)
        ubahButton = findViewById(R.id.DetailPembayaran_ubahButton)
        hapusButton = findViewById(R.id.DetailPembayaran_hapusButton)

        val intent = intent
        val id = intent.getStringExtra("EXTRA_ID")
        siapkanData(id!!)

        ubahButton.setOnClickListener {
            val intent = Intent(ubahButton.context, UbahPembayaranActivity::class.java)
            intent.putExtra("EXTRA_ID", id)
            startActivity(intent)
            finish()
        }


        hapusButton.setOnClickListener {
            val builder = AlertDialog.Builder(hapusButton.context)
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

    private fun siapkanData(id: String) {
        Client().getPembayaranService().getPembayaranById(id).enqueue(object : Callback<Pembayaran> {
            override fun onResponse(call: Call<Pembayaran>, response: Response<Pembayaran>) {
                val data = response.body()!!
                idPembayaran.text = data._id!!
                bulan.text = data.bulan!!
                tahun.text = data.tahun!!.toString()
                namaAnakKos.text = data.idanakkos!!.nama
                asal.text = data.idanakkos!!.asal
                nohp.text = data.idanakkos!!.nohp
            }

            override fun onFailure(call: Call<Pembayaran>, t: Throwable) {
                t.printStackTrace()
            }

        })
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