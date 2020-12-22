package com.mirfanrafif.koskuv2.ui.anakkos

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

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        var recyclerView: RecyclerView = root.findViewById(R.id.listAnakKosRv)
        dashboardViewModel.getAllAnakKos().observe(this.viewLifecycleOwner, {
            val anakKosAdapter = AnakKosAdapter(it, root.context)
            recyclerView.adapter = anakKosAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        })
        val tambahDataFab: FloatingActionButton = root.findViewById(R.id.tambahDataAnakKosFab)
        tambahDataFab.setOnClickListener {
            val intent = Intent(context, TambahAnakKos::class.java)
            startActivity(intent)
        }

        return root
    }
}