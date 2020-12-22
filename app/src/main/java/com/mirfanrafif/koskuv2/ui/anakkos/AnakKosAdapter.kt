package com.mirfanrafif.koskuv2.ui.anakkos

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mirfanrafif.koskuv2.R
import com.mirfanrafif.koskuv2.models.AnakKos

class AnakKosAdapter(val listAnakKos : List<AnakKos>, val context: Context)
    : RecyclerView.Adapter<AnakKosAdapter.AnakKosViewHolder>() {

    class AnakKosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var namaAnakKosText : TextView = itemView.findViewById(R.id.AnakKosItem_Nama)
        var asalAnakKosText: TextView = itemView.findViewById(R.id.AnakKosItem_Asal)
        var anakKosContainer: LinearLayout = itemView.findViewById(R.id.anakKosItemContainer)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnakKosViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_anak_kos, parent, false)
        return AnakKosViewHolder(v)
    }

    override fun onBindViewHolder(holder: AnakKosViewHolder, position: Int) {
        holder.namaAnakKosText.text = listAnakKos.get(position).nama
        holder.asalAnakKosText.text = listAnakKos.get(position).asal
        holder.anakKosContainer.setOnClickListener {
            val intent = Intent(context, DetailAnakKosActivity::class.java).apply {
                putExtra("EXTRA_ID", listAnakKos.get(position)._id)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listAnakKos.size
}