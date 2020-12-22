package com.mirfanrafif.koskuv2.ui.pengeluaran

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mirfanrafif.koskuv2.R
import com.mirfanrafif.koskuv2.models.Pengeluaran

class PengeluaranAdapter(val listPengeluaran: List<Pengeluaran>, val context: Context)
    : RecyclerView.Adapter<PengeluaranAdapter.PengeluaranviewHolder>() {
    class PengeluaranviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaPengeluaran: TextView = itemView.findViewById(R.id.PengeluaranItem_Nama)
        val jenisPengeluaran: TextView = itemView.findViewById(R.id.PengeluaranItem_Jenis)
        val nominal: TextView = itemView.findViewById(R.id.PengeluaranItem_Nominal)
        val pengeluaranContainer: LinearLayout = itemView.findViewById(R.id.pengeluaranContainer)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PengeluaranviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pengeluaran, parent, false)
        return PengeluaranviewHolder(view)
    }

    override fun onBindViewHolder(holder: PengeluaranviewHolder, position: Int) {
        holder.namaPengeluaran.text = listPengeluaran.get(position).namaPengeluaran
        holder.jenisPengeluaran.text = listPengeluaran.get(position).jenisPengeluaran
        holder.nominal.text = listPengeluaran.get(position).nominal.toString()
        holder.pengeluaranContainer.setOnClickListener {
            val intent = Intent(context, DetailPengeluaran::class.java)
            intent.putExtra("EXTRA_ID", listPengeluaran.get(position).id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listPengeluaran.size
}