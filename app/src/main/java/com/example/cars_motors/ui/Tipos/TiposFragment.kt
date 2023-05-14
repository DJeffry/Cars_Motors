package com.example.cars_motors.ui.Tipos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cars_motors.controladores.TiposAutomovilController
import com.example.cars_motors.databinding.FragmentTiposBinding

class TiposFragment : Fragment() {

    private var _binding: FragmentTiposBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var grupoAdapter: TiposAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(TiposViewModel::class.java)

        _binding = FragmentTiposBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val tiposController = TiposAutomovilController(requireContext())
        val listaAutos = tiposController.getAllTiposAutomovil()

        recyclerView = binding.recyclerViewGroupList
        recyclerView.layoutManager = LinearLayoutManager(activity)
        grupoAdapter = TiposAdapter(requireContext(), listaAutos)
        recyclerView.adapter = grupoAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}