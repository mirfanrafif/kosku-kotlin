package com.mirfanrafif.koskuv2.ui.pembayaran

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mirfanrafif.koskuv2.R

class PembayaranFragment : Fragment() {

    private lateinit var homeViewModel: DetailPembayaranViewModel
    private lateinit var loading: ProgressBar

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(DetailPembayaranViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val pembayaranRv : RecyclerView = root.findViewById(R.id.listPembayaranRv)
        loading = root.findViewById(R.id.Pembayaran_Loading)

        val tambahPembayaranFab : FloatingActionButton= root.findViewById(R.id.tambahPembayaranFab)

        tambahPembayaranFab.setOnClickListener {
            val intent = Intent(context, TambahPembayaranActivity::class.java)
            startActivity(intent)
        }

        homeViewModel.getAllPembayaran().observe(this.viewLifecycleOwner, Observer {
            val pembayaranAdapter = PembayaranAdapter(it, root.context)
            loading.visibility = View.INVISIBLE
            pembayaranRv.adapter = pembayaranAdapter
            pembayaranRv.layoutManager = LinearLayoutManager(context)
        })

        return root
    }
}