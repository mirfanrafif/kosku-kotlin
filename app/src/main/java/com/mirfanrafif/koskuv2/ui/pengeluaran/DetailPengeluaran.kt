package com.mirfanrafif.koskuv2.ui.pengeluaran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.mirfanrafif.koskuv2.MainActivity
import com.mirfanrafif.koskuv2.R
import com.mirfanrafif.koskuv2.models.Pengeluaran
import com.mirfanrafif.koskuv2.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPengeluaran : AppCompatActivity() {
    private lateinit var namaPengeluaran: TextView
    private lateinit var jenisPengeluaran: TextView
    private lateinit var nominal: TextView
    private lateinit var ubahPengeluaran: Button
    private lateinit var hapusPengeluaran: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pengeluaran)
        val intent = intent
        val id = intent.getStringExtra("EXTRA_ID")
        namaPengeluaran = findViewById(R.id.DetailPengeluaran_namaPengeluaran)
        jenisPengeluaran = findViewById(R.id.DetailPengeluaran_jenisPengeluaran)
        nominal = findViewById(R.id.DetailPengeluaran_Nominal)
        ubahPengeluaran = findViewById(R.id.DetailPengeluaran_ubahButton)
        hapusPengeluaran = findViewById(R.id.DetailPengeluaran_hapusButton)

        ubahPengeluaran.setOnClickListener {
            val intent = Intent(this, UbahPengeluaran::class.java)
            intent.putExtra("EXTRA_ID", id)
            startActivity(intent)
            finish()
        }
        hapusPengeluaran.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setTitle("Hapus Data")
                .setMessage("Apakah anda yakin ingin menghapus data ini")
            builder.setPositiveButton("Ya") { _, _ ->
                hapusData(id)
            }

            builder.setNegativeButton("Tidak") { alertDialog, _ ->
                alertDialog.cancel()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
        tampilkanData(id!!)
    }

    private fun hapusData(id: String?) {
        Client().getPengeluaranService().deletePengeluaran(id!!).enqueue(object : Callback<Pengeluaran> {
            override fun onResponse(call: Call<Pengeluaran>, response: Response<Pengeluaran>) {
                val intent = Intent(hapusPengeluaran.context, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onFailure(call: Call<Pengeluaran>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    private fun tampilkanData(id: String) {
        Client().getPengeluaranService().getPengeluaranById(id).enqueue(object : Callback<Pengeluaran> {
            override fun onResponse(call: Call<Pengeluaran>, response: Response<Pengeluaran>) {
                val pengeluaran = response.body()!!
                namaPengeluaran.text = pengeluaran.namaPengeluaran
                jenisPengeluaran.text = pengeluaran.jenisPengeluaran
                nominal.text = pengeluaran.nominal.toString()
            }

            override fun onFailure(call: Call<Pengeluaran>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}