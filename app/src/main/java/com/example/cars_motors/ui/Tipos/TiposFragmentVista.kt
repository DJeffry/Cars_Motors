package com.example.cars_motors.ui.Tipos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cars_motors.controladores.TiposAutomovilController
import com.example.cars_motors.databinding.VistaTiposBinding

class TiposFragmentVista : Fragment() {
    private var _binding: VistaTiposBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VistaTiposBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val tipoController = TiposAutomovilController(requireContext())
        val idTipo = arguments?.getInt("idTipo") ?: 0
        val tipo = tipoController.getTipoAutomovilById(idTipo)


        if (tipo != null) {
            binding.lblNombre.text = tipo.descripcion
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}