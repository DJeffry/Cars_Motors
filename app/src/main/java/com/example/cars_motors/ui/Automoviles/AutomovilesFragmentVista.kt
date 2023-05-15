package com.example.cars_motors.ui.Colores

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cars_motors.controladores.AutomovilController
import com.example.cars_motors.controladores.AutomovilesController
import com.example.cars_motors.databinding.VistaAutomovilesBinding

class AutomovilesVistaFragment : Fragment() {
    private var _binding: VistaAutomovilesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VistaAutomovilesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val AutomovilesController = AutomovilController(requireContext())
        val idUsuario = arguments?.getInt("idUsuario") ?: 0
        Log.d("AutomovilesVistaFragment", "ID del usuario: $idUsuario")
        val usuario = AutomovilesController.getUsuarioById(idUsuario)


        if (usuario != null) {
            binding.lblNombre.text = usuario.nombre
            val nombre=usuario.nombre
            Log.d("AutomovilesVistaFragment", "ID del usuario: $nombre")
            binding.lblApellidos.text = usuario.apellido
            binding.lblEmail.text = usuario.email
            binding.lblUser.text = usuario.user
            binding.lblTipo.text = usuario.tipo
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}