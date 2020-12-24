package com.mirfanrafif.koskuv2.ui.pembayaran

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mirfanrafif.koskuv2.R
import com.mirfanrafif.koskuv2.databinding.ItemPembayaranBinding
import com.mirfanrafif.koskuv2.models.Pembayaran
import com.mirfanrafif.koskuv2.viewmodel.PembayaranItemViewModel

class PembayaranAdapter(val listPembayaran:  List<Pembayaran>, val context: Context) :
    RecyclerView.Adapter<PembayaranAdapter.PembayaranViewHolder>() {

    class PembayaranViewHolder(binding: ItemPembayaranBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PembayaranViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: ItemPembayaranBinding = DataBindingUtil.inflate(
            view, R.layout.item_pembayaran, parent, false)
        return PembayaranViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PembayaranViewHolder, position: Int) {
        val pembayaranItemViewModel = PembayaranItemViewModel(listPembayaran.get(position))
        holder.binding.viewModel = pembayaranItemViewModel
        holder.binding.pembayaranContainer.setOnClickListener {
            val intent = Intent(context, DetailPembayaran::class.java)
            intent.putExtra("EXTRA_ID", listPembayaran.get(position)._id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listPembayaran.size
    }
}