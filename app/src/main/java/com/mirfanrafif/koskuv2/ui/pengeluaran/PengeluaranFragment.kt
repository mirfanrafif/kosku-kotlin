package com.mirfanrafif.koskuv2.ui.pengeluaran

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mirfanrafif.koskuv2.R

class PengeluaranFragment : Fragment() {

    private lateinit var notificationsViewModel: PengeluaranViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(PengeluaranViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val listPengeluaranRv : RecyclerView= root.findViewById(R.id.listPengeluaranRv)

        notificationsViewModel.getAllPengeluaran().observe(this.viewLifecycleOwner, {
            val pengeluaranAdapter = PengeluaranAdapter(it, root.context)
            listPengeluaranRv.adapter = pengeluaranAdapter
            listPengeluaranRv.layoutManager = LinearLayoutManager(root.context)
        })

        val tambahPengeluaranFab : FloatingActionButton = root.findViewById(R.id.tambahPengeluaranFab)
        tambahPengeluaranFab.setOnClickListener {
            val intent = Intent(root.context, TambahPengeluaran::class.java)
            startActivity(intent)
        }
        return root
    }
}