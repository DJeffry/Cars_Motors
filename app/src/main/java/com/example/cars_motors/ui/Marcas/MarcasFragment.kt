package com.example.cars_motors.ui.Marcas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cars_motors.controladores.MarcasController
import com.example.cars_motors.databinding.FragmentMarcasBinding
import com.example.cars_motors.ui.Favoritos.FavoritosAdapter

class MarcasFragment : Fragment() {

    private var _binding: FragmentMarcasBinding? = null
    private lateinit var homeViewModel: MarcasViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var grupoAdapter: MarcasAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(MarcasViewModel::class.java)

        _binding = FragmentMarcasBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val marcasController = MarcasController(requireContext())
        val listaAutos = marcasController.getAllMarcas().toMutableList()

        recyclerView = binding.recyclerViewGroupList
        recyclerView.layoutManager = LinearLayoutManager(activity)
        grupoAdapter = MarcasAdapter(requireContext(), listaAutos, marcasController)
        recyclerView.adapter = grupoAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}