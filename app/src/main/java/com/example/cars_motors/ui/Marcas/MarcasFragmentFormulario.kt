package com.example.cars_motors.ui.Marcas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.cars_motors.R
import com.example.cars_motors.controladores.MarcasController
import com.example.cars_motors.databinding.FormularioMarcasBinding
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
        val idUsuario = arguments?.getInt("idUsuario") ?: 0
        Log.d("MarcasVistaFragment", "ID del usuario: $idUsuario")
        val usuario = MarcasController.getUsuarioById(idUsuario)


        if (usuario != null) {
            binding.txtNombresUsuario.setText(usuario.nombre)
            binding.txtApellidosMarcas.setText(usuario.apellido)
            binding.txtEmailMarcas.setText(usuario.email)
            binding.txtUsuario.setText(usuario.user)
            if (usuario.tipo == "cliente") {
                binding.radCliente.isChecked = true
                binding.radAdmin.isChecked = false
            } else {
                binding.radCliente.isChecked = false
                binding.radAdmin.isChecked = true
            }
        }

        binding.btnGuardar.setOnClickListener {
            val txtnombres = binding.txtNombresUsuario.text.toString()
            val txtapellidos = binding.txtApellidosMarcas.text.toString()
            val txtemail = binding.txtEmailMarcas.text.toString()
            val txtusuario = binding.txtUsuario.text.toString()
            val txtpassword = binding.txtContra.text.toString()

            if (txtnombres.isBlank() || txtapellidos.isBlank() || txtemail.isBlank() || txtusuario.isBlank() || txtpassword.isBlank()) {
                // Algunos campos no están completos
                Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (txtusuario.length < 6 || txtpassword.length < 8) {
                // El usuario y la contraseña deben tener al menos 6 y 8 caracteres respectivamente
                Toast.makeText(requireContext(), "El usuario y la contraseña deben tener al menos 6 y 8 caracteres respectivamente", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nuevoUsuario = Usuario()
            nuevoUsuario.nombre = txtnombres
            nuevoUsuario.apellido = txtapellidos
            nuevoUsuario.email = txtemail
            nuevoUsuario.user = txtusuario
            nuevoUsuario.password = txtpassword

            if (binding.radAdmin.isChecked) {
                nuevoUsuario.tipo = "Administrador"
            } else {
                nuevoUsuario.tipo = "Cliente"
            }

            if (usuario != null) {
                MarcasController.updateUsuario(usuario)
                Toast.makeText(requireContext(), "Usuario Modificado con exito", Toast.LENGTH_SHORT).show()
            } else {
                MarcasController.insertUsuario(nuevoUsuario)
                Toast.makeText(requireContext(), "Usuario Creado con exito", Toast.LENGTH_SHORT).show()
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