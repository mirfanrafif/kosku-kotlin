package com.mirfanrafif.koskuv2.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mirfanrafif.koskuv2.R
import com.mirfanrafif.koskuv2.models.Pembayaran

class PembayaranAdapter(val listPembayaran:  List<Pembayaran>, val context: Context) :
    RecyclerView.Adapter<PembayaranAdapter.PembayaranViewHolder>() {

    class PembayaranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaAnakKosText: TextView = itemView.findViewById(R.id.PembayaranItem_Nama)
        val bulanText: TextView = itemView.findViewById(R.id.PembayaranItem_Bulan)
        val tahun: TextView = itemView.findViewById(R.id.PembayaranItem_Tahun)
        val container : ConstraintLayout = itemView.findViewById(R.id.pembayaranContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PembayaranViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pembayaran, parent, false)
        return PembayaranViewHolder(view)
    }

    override fun onBindViewHolder(holder: PembayaranViewHolder, position: Int) {
        holder.namaAnakKosText.setText(listPembayaran.get(position).idanakkos!!.nama)
        holder.bulanText.setText(listPembayaran.get(position).bulan!!)
        holder.tahun.setText(listPembayaran.get(position).tahun!!.toString())
        holder.container.setOnClickListener {
            val intent = Intent(holder.container.context, DetailPembayaran::class.java)
            intent.putExtra("EXTRA_ID", listPembayaran.get(position)._id!!)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listPembayaran.size
    }
}