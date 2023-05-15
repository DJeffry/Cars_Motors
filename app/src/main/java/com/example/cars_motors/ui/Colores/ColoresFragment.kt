package com.example.cars_motors.ui.Colores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cars_motors.controladores.ColoresController
import androidx.recyclerview.widget.RecyclerView
import com.example.cars_motors.databinding.FragmentColoresBinding
import com.example.cars_motors.ui.ColorModel.ColoresAdapter

class ColoresFragment : Fragment() {

    private var _binding: FragmentColoresBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var grupoAdapter: ColoresAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(ColoresViewModel::class.java)

        _binding = FragmentColoresBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val colorController = ColoresController(requireContext())
        val listaAutos = colorController.getAllColores().toMutableList()

        recyclerView = binding.recyclerViewGroupList
        recyclerView.layoutManager = LinearLayoutManager(activity)
        grupoAdapter = ColoresAdapter(requireContext(), listaAutos, colorController)
        recyclerView.adapter = grupoAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}