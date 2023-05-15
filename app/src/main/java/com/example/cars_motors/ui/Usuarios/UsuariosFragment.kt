package com.example.cars_motors.ui.Usuarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cars_motors.R
import com.example.cars_motors.controladores.UsuariosController
import com.example.cars_motors.databinding.FragmentUsuariosBinding

class UsuariosFragment : Fragment() {

    private var _binding: FragmentUsuariosBinding? = null
    private lateinit var homeViewModel: UsuariosViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var grupoAdapter: UsuariosAdapter

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUsuariosBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val usuariosController = UsuariosController(requireContext())
        val listaUsuarios = usuariosController.getAllUsuarios().toMutableList()
        val btnAdd = binding.btnAAdir


        recyclerView = binding.recyclerViewGroupList
        recyclerView.layoutManager = LinearLayoutManager(activity)
        grupoAdapter = UsuariosAdapter(requireContext(), listaUsuarios, usuariosController)
        recyclerView.adapter = grupoAdapter

        btnAdd.setOnClickListener {
            val navController = (requireActivity() as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
            navController.navigate(R.id.formulario_usuarios)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
