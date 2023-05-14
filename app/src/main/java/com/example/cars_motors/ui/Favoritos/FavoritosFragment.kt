package com.example.cars_motors.ui.Favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cars_motors.controladores.FavoritosAutomovilController
import com.example.cars_motors.databinding.FragmentFavoritosBinding

class FavoritosFragment : Fragment() {

    private var _binding: FragmentFavoritosBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var grupoAdapter: FavoritosAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(FavoritosViewModel::class.java)

        _binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val automvilController = FavoritosAutomovilController(requireContext())
        val listaAutos = automvilController.getAllFavoritosAutomovil().toMutableList()

        recyclerView = binding.recyclerViewGroupList
        recyclerView.layoutManager = LinearLayoutManager(activity)
        grupoAdapter = FavoritosAdapter(requireContext(), listaAutos, automvilController)
        recyclerView.adapter = grupoAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}