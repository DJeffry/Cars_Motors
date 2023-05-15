package com.example.cars_motors.ui.Marcas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cars_motors.controladores.MarcasController
import com.example.cars_motors.databinding.VistaMarcasBinding

class MarcasFragmentVista : Fragment() {
    private var _binding: VistaMarcasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VistaMarcasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val MarcasController = MarcasController(requireContext())
        val idMarca = arguments?.getInt("idMarca") ?: 0
        val color= MarcasController.getMarcaById(idMarca)


        if (color != null) {
            binding.lblNombre.text = color.nombre
            val nombre=color.nombre
            Log.d("MarcasVistaFragment", "ID del color: $nombre")
            binding.lblNombre.text = color.nombre.toString()
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}