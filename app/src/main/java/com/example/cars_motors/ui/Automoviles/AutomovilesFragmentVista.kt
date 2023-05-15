package com.example.cars_motors.ui.Automoviles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cars_motors.controladores.AutomovilController
import com.example.cars_motors.controladores.ColoresController
import com.example.cars_motors.controladores.MarcasController
import com.example.cars_motors.controladores.TiposAutomovilController
import com.example.cars_motors.databinding.VistaAutomovilesBinding

class AutomovilesFragmentVista : Fragment() {
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
        val AutomovilesColorController = ColoresController(requireContext())
        val AutomovilesMarcasControler = MarcasController(requireContext())
        val AutomovilesTiposControler = TiposAutomovilController(requireContext())
        val idauto = arguments?.getInt("idAuto") ?: 0
        val auto = AutomovilesController.getAutomovilById(idauto)
        val marca= AutomovilesMarcasControler.getMarcaById(auto?.idMarca)
        val color = AutomovilesColorController.getColorById(auto?.idColor)
        val tipo = AutomovilesTiposControler.getTipoAutomovilById(auto?.idTipoAutomovil)

        if (auto != null) {
            binding.lblAnio.text = auto.anio.toString()
            binding.lblChasis.text = auto?.numeroChasis.toString()
            binding.lblColor.text = color?.nombre.toString()
            binding.lblCapacidadAsientos.text = auto.capacidadAsientos.toString()
            binding.lblMarca.text=auto.capacidadAsientos.toString()
            binding.lblDescripcion.text=auto.descripcion
            binding.lblTipoAuto.text=tipo?.descripcion
            binding.lblPrecio.text=auto.precio.toString()
            binding.lblNumeroVin.text=auto.numeroVin.toString()
            binding.lblNumeroMotor.text=auto.numeroMotor
            binding.lblNumeroAsientos.text=auto.numeroAsientos.toString()
            binding.lblModelo.text=auto.modelo.toString()

        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}