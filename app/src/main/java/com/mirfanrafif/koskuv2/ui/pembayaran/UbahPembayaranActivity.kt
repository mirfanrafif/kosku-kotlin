package com.mirfanrafif.koskuv2.ui.pembayaran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.mirfanrafif.koskuv2.MainActivity
import com.mirfanrafif.koskuv2.R
import com.mirfanrafif.koskuv2.models.AnakKos
import com.mirfanrafif.koskuv2.models.Pembayaran
import com.mirfanrafif.koskuv2.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UbahPembayaranActivity : AppCompatActivity() {
    private lateinit var namaAnakKosSpinner : Spinner
    private lateinit var bulanSpinner: Spinner
    private lateinit var tahunText: EditText
    private lateinit var simpanButton : Button
    private lateinit var listAnakKos : List<AnakKos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_pembayaran)
        val intent = intent
        val id = intent.getStringExtra("EXTRA_ID")!!
        namaAnakKosSpinner = findViewById(R.id.UbahPembayaran_anakKosSpinner)
        bulanSpinner = findViewById(R.id.UbahPembayaran_bulanSpinner)
        tahunText = findViewById(R.id.UbahPembayaran_tahunEdit)
        simpanButton = findViewById(R.id.simpanPembayaranButton)

        siapkanListAnakKos()
        val bulanAdapter = ArrayAdapter.createFromResource(
            bulanSpinner.context, R.array.bulanList, R.layout.item_spinner)
        bulanSpinner.adapter = bulanAdapter

        simpanButton.setOnClickListener {
            if (!tahunText.text.toString().isEmpty()) {
                val anakKos = listAnakKos.get(namaAnakKosSpinner.selectedItemPosition)
                val bulan = bulanSpinner.selectedItem.toString()
                val tahun = tahunText.text.toString().toInt()
                simpanData(anakKos, bulan, tahun, id)
            }
        }
    }

    private fun siapkanListAnakKos() {
        Client().getAnakKosService().getAllAnakKos().enqueue(object : Callback<List<AnakKos>> {
            override fun onResponse(call: Call<List<AnakKos>>, response: Response<List<AnakKos>>) {
                listAnakKos = response.body()!!

                val listNama = ArrayList<String>()
                for (anakKos in listAnakKos) {
                    listNama.add(anakKos.nama!!)
                }

                val namaAnakKosAdapter = ArrayAdapter(
                    namaAnakKosSpinner.context, R.layout.item_spinner, listNama
                )
                namaAnakKosSpinner.adapter = namaAnakKosAdapter
            }

            override fun onFailure(call: Call<List<AnakKos>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error : " + t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun simpanData(anakKos: AnakKos, bulan: String, tahun: Int, id: String) {
        val pembayaran = Pembayaran()
        pembayaran.idanakkos = anakKos
        pembayaran.bulan = bulan
        pembayaran.tahun = tahun
        Client().getPembayaranService().updatePembayaran(id, pembayaran).enqueue(
            object : Callback<Pembayaran> {
                override fun onResponse(
                    call: Call<Pembayaran>,
                    response: Response<Pembayaran>
                ) {
                    val intent = Intent(simpanButton.context, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                override fun onFailure(call: Call<Pembayaran>, t: Throwable) {
                    Toast.makeText(
                        applicationContext, "Error : " + t.message,
                        Toast.LENGTH_LONG
                    ).show()
                }

            })
    }
}