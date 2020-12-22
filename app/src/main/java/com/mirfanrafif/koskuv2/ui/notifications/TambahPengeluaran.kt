package com.mirfanrafif.koskuv2.ui.notifications

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.mirfanrafif.koskuv2.MainActivity
import com.mirfanrafif.koskuv2.R
import com.mirfanrafif.koskuv2.models.Pengeluaran
import com.mirfanrafif.koskuv2.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahPengeluaran : AppCompatActivity() {
    private lateinit var namaPengeluaran : EditText
    private lateinit var jenisPengeluaran: Spinner
    private lateinit var nominal: EditText
    private lateinit var simpanButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_pengeluaran)
        namaPengeluaran = findViewById(R.id.TambahPengeluaran_NamaPengeluaran)
        jenisPengeluaran = findViewById(R.id.TambahPengeluaran_jenisPengeluaranSpinner)
        nominal = findViewById(R.id.TambahPengeluaran_nominal)
        simpanButton = findViewById(R.id.TambahPengeluaran_simpanPengeluaran)

        val jenisPengeluaranAdapter = ArrayAdapter.createFromResource(jenisPengeluaran.context,
            R.array.jenisPengeluaranList, R.layout.item_spinner)
        jenisPengeluaran.adapter = jenisPengeluaranAdapter
        simpanButton.setOnClickListener {
            simpanPengeluaran()
        }
    }

    private fun simpanPengeluaran() {
        if (namaPengeluaran.text.toString().isNotEmpty()
            && jenisPengeluaran.selectedItem.toString().isNotEmpty()
            && nominal.text.toString().isNotEmpty()
        ){
            val pengeluaran = Pengeluaran()
            pengeluaran.namaPengeluaran = namaPengeluaran.text.toString()
            pengeluaran.jenisPengeluaran = jenisPengeluaran.selectedItem.toString()
            pengeluaran.nominal = nominal.text.toString().toInt()

            Client().getPengeluaranService().savePengeluaran(pengeluaran).enqueue(object : Callback<Pengeluaran> {
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
}