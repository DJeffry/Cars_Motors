package com.example.cars_motors.ui.Colores

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.cars_motors.R
import com.example.cars_motors.controladores.ColoresController
import com.example.cars_motors.databinding.FormularioColoresBinding
import com.example.cars_motors.modelos.ColorModel
import com.example.cars_motors.modelos.Usuario

class ColoresFragmentFormulario : Fragment() {
    private var _binding: FormularioColoresBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FormularioColoresBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val ColoresController = ColoresController(requireContext())
        val idColor = arguments?.getInt("idColor") ?: 0
        val color=ColoresController.getColorById(idColor)

        if (color != null) {
            binding.txtColoresCrud.setText(color.nombre)
        }

        binding.btnGuardar.setOnClickListener {
            val txtColor = binding.txtColoresCrud.text.toString()

            if (txtColor.isBlank()) {
                // Algunos campos no están completos
                Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nuevoColor = ColorModel()
            nuevoColor.nombre = txtColor

            if (color != null) {
                ColoresController.updateColor(color)
                Toast.makeText(requireContext(), "Color Modificado con exito", Toast.LENGTH_SHORT).show()
            } else {
                ColoresController.insertColor(nuevoColor)
                Toast.makeText(requireContext(), "Color Creado con exito", Toast.LENGTH_SHORT).show()
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