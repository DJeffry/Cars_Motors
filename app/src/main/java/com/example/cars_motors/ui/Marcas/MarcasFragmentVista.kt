package com.example.cars_motors.ui.Marcas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cars_motors.controladores.MarcasController
import com.example.cars_motors.databinding.VistaMarcasBinding

class MarcasVistaFragment : Fragment() {
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
        val idUsuario = arguments?.getInt("idUsuario") ?: 0
        Log.d("MarcasVistaFragment", "ID del usuario: $idUsuario")
        val usuario = MarcasController.getUsuarioById(idUsuario)


        if (usuario != null) {
            binding.lblNombre.text = usuario.nombre
            val nombre=usuario.nombre
            Log.d("MarcasVistaFragment", "ID del usuario: $nombre")
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