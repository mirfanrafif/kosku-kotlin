package com.mirfanrafif.koskuv2.ui.pengeluaran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.mirfanrafif.koskuv2.MainActivity
import com.mirfanrafif.koskuv2.R
import com.mirfanrafif.koskuv2.models.Pengeluaran
import com.mirfanrafif.koskuv2.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UbahPengeluaran : AppCompatActivity() {
    private lateinit var namaPengeluaran : EditText
    private lateinit var jenisPengeluaran: Spinner
    private lateinit var nominal: EditText
    private lateinit var simpanButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_pengeluaran)
        val intent = intent
        val id = intent.getStringExtra("EXTRA_ID")

        namaPengeluaran = findViewById(R.id.UbahPengeluaran_namaPengeluaranEdit)
        jenisPengeluaran = findViewById(R.id.UbahPengeluaran_jenisPengeluaranSpinner)
        nominal = findViewById(R.id.UbahPengeluaran_nominalEdit)
        simpanButton = findViewById(R.id.UbahPengeluaran_simpanPengeluaran)

        tampilkanData(id!!)
        val jenisPengeluaranAdapter = ArrayAdapter.createFromResource(jenisPengeluaran.context,
            R.array.jenisPengeluaranList, R.layout.item_spinner)
        jenisPengeluaran.adapter = jenisPengeluaranAdapter

        simpanButton.setOnClickListener {
            simpanData(id!!)
        }
    }

    private fun simpanData(id: String) {
        if (namaPengeluaran.text.isNotEmpty()
            && jenisPengeluaran.selectedItem.toString().isNotEmpty()
            && nominal.text.isNotEmpty()
        ) {
            val pengeluaran = Pengeluaran()
            pengeluaran.namaPengeluaran = namaPengeluaran.text.toString()
            pengeluaran.jenisPengeluaran = jenisPengeluaran.selectedItem.toString()
            pengeluaran.nominal = nominal.text.toString().toInt()

            Client().getPengeluaranService().updatePengeluaran(id, pengeluaran)
                .enqueue(object : Callback<Pengeluaran> {
                    override fun onResponse(
                        call: Call<Pengeluaran>,
                        response: Response<Pengeluaran>
                    ) {
                        val intent = Intent(simpanButton.context, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    override fun onFailure(call: Call<Pengeluaran>, t: Throwable) {
                        t.printStackTrace()
                    }

                })
        }
    }

    private fun tampilkanData(id: String) {
        Client().getPengeluaranService().getPengeluaranById(id).enqueue(object :
            Callback<Pengeluaran> {
            override fun onResponse(call: Call<Pengeluaran>, response: Response<Pengeluaran>) {
                val pengeluaran = response.body()!!
                namaPengeluaran.setText(pengeluaran.namaPengeluaran)
                nominal.setText(pengeluaran.nominal.toString())
            }

            override fun onFailure(call: Call<Pengeluaran>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}