package com.mirfanrafif.koskuv2.ui.anakkos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mirfanrafif.koskuv2.MainActivity
import com.mirfanrafif.koskuv2.R
import com.mirfanrafif.koskuv2.models.AnakKos
import com.mirfanrafif.koskuv2.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UbahAnakKosActivity : AppCompatActivity() {
    private lateinit var namaEditText: EditText
    private lateinit var asalEditText: EditText
    private lateinit var nohpEditText: EditText
    private lateinit var simpanButton: Button
    private val client = Client()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_anak_kos)
        namaEditText = findViewById(R.id.UbahAnakKos_namaAnakKos)
        asalEditText = findViewById(R.id.UbahAnakKos_asalAnakKos)
        nohpEditText = findViewById(R.id.UbahAnakKos_NoHP)
        simpanButton = findViewById(R.id.UbahAnakKos_simpanButton)

        namaEditText.isEnabled = false
        asalEditText.isEnabled = false
        nohpEditText.isEnabled = false

        val intent = intent
        val id = intent.getStringExtra("EXTRA_ID")
        getData(id!!)

        simpanButton.setOnClickListener {
            updateData(id!!)
        }

    }

    private fun getData(id: String) {
        client.getAnakKosService().getAnakKosById(id).enqueue(object : Callback<AnakKos> {
            override fun onResponse(call: Call<AnakKos>, response: Response<AnakKos>) {
                var anakKos = response.body()!!
                namaEditText.setText(anakKos.nama)
                asalEditText.setText(anakKos.asal)
                nohpEditText.setText(anakKos.nohp)
                namaEditText.isEnabled = true
                asalEditText.isEnabled = true
                nohpEditText.isEnabled = true
            }

            override fun onFailure(call: Call<AnakKos>, t: Throwable) {
                Toast.makeText(applicationContext, "Error : " + t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun updateData(id: String) {
        val nama = namaEditText.text.toString()
        val asal = asalEditText.text.toString()
        val nohp = nohpEditText.text.toString()
        if (!nama.isEmpty() && !asal.isEmpty() && !nohp.isEmpty()) {
            val anakKos = AnakKos()
            anakKos.nama = nama
            anakKos.asal = asal
            anakKos.nohp = nohp
            client.getAnakKosService().updateAnakKos(id, anakKos)
                .enqueue(object : Callback<AnakKos> {
                    override fun onResponse(call: Call<AnakKos>, response: Response<AnakKos>) {
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    override fun onFailure(call: Call<AnakKos>, t: Throwable) {
                        Toast.makeText(
                            applicationContext,
                            "Error : " + t.message,
                            Toast.LENGTH_LONG
                        )
                    }

                })
        }
    }
}