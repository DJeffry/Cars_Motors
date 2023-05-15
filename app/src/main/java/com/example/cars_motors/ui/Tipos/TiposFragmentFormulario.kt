package com.example.cars_motors.ui.Tipos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.cars_motors.R
import com.example.cars_motors.controladores.TiposAutomovilController
import com.example.cars_motors.databinding.FormularioTiposBinding
import com.example.cars_motors.modelos.Marca
import com.example.cars_motors.modelos.TipoAutomovil
import com.example.cars_motors.modelos.Usuario

class TiposFragmentFormulario : Fragment() {
    private var _binding: FormularioTiposBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FormularioTiposBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val TiposController = TiposAutomovilController(requireContext())
        val idTipo = arguments?.getInt("idTipo") ?: 0
        val tipo=TiposController.getTipoAutomovilById(idTipo)

        if (tipo != null) {
            binding.txtTiposCrud.setText(tipo.descripcion)
        }

        binding.btnGuardar.setOnClickListener {
            val txtTipo = binding.txtTiposCrud.text.toString()

            if (txtTipo.isBlank()) {
                // Algunos campos no están completos
                Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nuevoTipo = TipoAutomovil()
            nuevoTipo.descripcion = txtTipo

            if (tipo != null) {
                TiposController.updateTipoAutomovil(tipo)
                Toast.makeText(requireContext(), "Marca Modificado con exito", Toast.LENGTH_SHORT).show()
            } else {
                TiposController.insertTipoAutomovil(nuevoTipo)
                Toast.makeText(requireContext(), "Marca Creado con exito", Toast.LENGTH_SHORT).show()
            }
            val navController = requireActivity().findNavController(R.id.nav_host_fragment_content_main)
            navController.popBackStack()

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}