package com.mirfanrafif.koskuv2.ui.anakkos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mirfanrafif.koskuv2.MainActivity
import com.mirfanrafif.koskuv2.R
import com.mirfanrafif.koskuv2.models.AnakKos
import com.mirfanrafif.koskuv2.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailAnakKosActivity : AppCompatActivity() {
    private lateinit var namaTextView: TextView
    private lateinit var asalTextView: TextView
    private lateinit var nohpTextView: TextView
    private lateinit var ubahButton: Button
    private lateinit var hapusButton: Button
    private lateinit var alertDialog: AlertDialog
    private val client = Client()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_anak_kos)
        val intent = intent
        val id = intent.getStringExtra("EXTRA_ID")

        namaTextView = findViewById(R.id.DetailAnakKos_Nama)
        asalTextView = findViewById(R.id.DetailAnakKos_Asal)
        nohpTextView = findViewById(R.id.DetailAnakKos_NoHP)
        ubahButton = findViewById(R.id.DetailAnakKos_ubahButton)
        hapusButton = findViewById(R.id.DetailAnakKos_hapusButton)

        ambilData(id!!)
        ubahButton.setOnClickListener {
            val intent = Intent(applicationContext, UbahAnakKosActivity::class.java).apply {
                putExtra("EXTRA_ID", id)
            }

            startActivity(intent)
            finish()
        }

        hapusButton.setOnClickListener {
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
    }

    private fun hapusData(id: String) {
        val client = Client()
        client.getAnakKosService().deleteAnakKos(id).enqueue(object : Callback<AnakKos> {
            override fun onResponse(call: Call<AnakKos>, response: Response<AnakKos>) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onFailure(call: Call<AnakKos>, t: Throwable) {
                Toast.makeText(applicationContext, "Error : " + t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun ambilData(id: String) {
        client.getAnakKosService().getAnakKosById(id).enqueue(object : Callback<AnakKos> {
            override fun onResponse(call: Call<AnakKos>, response: Response<AnakKos>) {
                var anakKos = response.body()!!
                namaTextView.text = anakKos.nama
                asalTextView.text = anakKos.asal
                nohpTextView.text = anakKos.nohp
            }

            override fun onFailure(call: Call<AnakKos>, t: Throwable) {
                Toast.makeText(applicationContext, "Error : " + t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}