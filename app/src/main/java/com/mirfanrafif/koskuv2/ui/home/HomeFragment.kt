package com.mirfanrafif.koskuv2.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mirfanrafif.koskuv2.R
import com.mirfanrafif.koskuv2.models.Pembayaran
import com.mirfanrafif.koskuv2.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val pembayaranRv : RecyclerView = root.findViewById(R.id.listPembayaranRv)

        val tambahPembayaranFab : FloatingActionButton= root.findViewById(R.id.tambahPembayaranFab)

        tambahPembayaranFab.setOnClickListener {
            val intent = Intent(context, TambahPembayaranActivity::class.java)
            startActivity(intent)
        }

        homeViewModel.getAllPembayaran().observe(this.viewLifecycleOwner, Observer {
            val pembayaranAdapter = PembayaranAdapter(it, root.context)
            pembayaranRv.adapter = pembayaranAdapter
            pembayaranRv.layoutManager = LinearLayoutManager(context)
        })

        return root
    }
}