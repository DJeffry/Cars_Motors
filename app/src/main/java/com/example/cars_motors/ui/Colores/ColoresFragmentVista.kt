package com.example.cars_motors.ui.Colores

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cars_motors.controladores.ColoresController
import com.example.cars_motors.databinding.VistaColoresBinding

class ColoresFragmentVista : Fragment() {
    private var _binding: VistaColoresBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VistaColoresBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val ColoresController = ColoresController(requireContext())
        val idColor = arguments?.getInt("idColor") ?: 0
        val color= ColoresController.getColorById(idColor)


        if (color != null) {
            binding.lblNombre.text = color.nombre
            val nombre=color.nombre
            binding.lblNombre.text = color.nombre.toString()
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}