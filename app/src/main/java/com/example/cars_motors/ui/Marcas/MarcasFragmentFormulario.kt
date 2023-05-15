package com.example.cars_motors.ui.Marcas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.cars_motors.R
import com.example.cars_motors.controladores.MarcasController
import com.example.cars_motors.databinding.FormularioMarcasBinding
import com.example.cars_motors.modelos.Marca
import com.example.cars_motors.modelos.Usuario

class MarcasFragmentFormulario : Fragment() {
    private var _binding: FormularioMarcasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FormularioMarcasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val MarcasController = MarcasController(requireContext())
        val idMarca = arguments?.getInt("idMarca") ?: 0
        val color=MarcasController.getMarcaById(idMarca)

        if (color != null) {
            binding.txtMarcasCrud.setText(color.nombre)
        }

        binding.btnGuardar.setOnClickListener {
            val txtMarca = binding.txtMarcasCrud.text.toString()

            if (txtMarca.isBlank()) {
                // Algunos campos no están completos
                Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nuevoMarca = Marca()
            nuevoMarca.nombre = binding.txtMarcasCrud.text.toString()

            if (color != null) {
                MarcasController.updateMarca(color)
                Toast.makeText(requireContext(), "Marca Modificado con exito", Toast.LENGTH_SHORT).show()
            } else {
                MarcasController.insertMarca(nuevoMarca)
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