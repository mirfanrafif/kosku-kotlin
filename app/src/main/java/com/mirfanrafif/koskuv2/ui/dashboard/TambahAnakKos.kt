package com.mirfanrafif.koskuv2.ui.dashboard

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

class TambahAnakKos : AppCompatActivity() {
    private lateinit var tambahButton: Button
    private lateinit var namaEditText: EditText
    private lateinit var asalEditText: EditText
    private lateinit var noHpEditText: EditText
    private lateinit var client: Client;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_anak_kos)
        tambahButton = findViewById(R.id.TambahAnakKos_simpanButton)
        namaEditText = findViewById(R.id.TambahAnakKos_Nama)
        asalEditText = findViewById(R.id.TambahAnakKos_Asal)
        noHpEditText = findViewById(R.id.TambahAnakKos_NoHp)
        client = Client()

        tambahButton.setOnClickListener {
            val nama = namaEditText.text.toString()
            val asal = asalEditText.text.toString()
            val nohp = noHpEditText.text.toString()
            if (!nama.isEmpty() && !asal.isEmpty() && !nohp.isEmpty()) {
                val anakKos = AnakKos()
                anakKos.nama = nama
                anakKos.asal = asal
                anakKos.nohp = nohp

                client.getAnakKosService().saveAnakKos(anakKos).enqueue(object : Callback<AnakKos> {
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
                            .show()
                    }

                })
            }

        }
    }
}