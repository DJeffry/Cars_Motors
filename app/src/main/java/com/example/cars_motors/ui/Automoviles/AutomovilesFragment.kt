package com.example.cars_motors.ui.Automoviles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cars_motors.controladores.AutomovilController
import com.example.cars_motors.controladores.TiposAutomovilController
import com.example.cars_motors.databinding.FragmentAutosBinding

class AutomovilesFragment : Fragment() {

    private var _binding: FragmentAutosBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var grupoAdapter: AutomovilesAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(AutomovilesViewModel::class.java)

        _binding = FragmentAutosBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val autoController = AutomovilController(requireContext())
        val listaAutos = autoController.getAllAutomoviles().toMutableList()


        recyclerView = binding.recyclerViewGroupList
        recyclerView.layoutManager = LinearLayoutManager(activity)
        grupoAdapter = AutomovilesAdapter(requireContext(), listaAutos, autoController)

        recyclerView.adapter = grupoAdapter

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}