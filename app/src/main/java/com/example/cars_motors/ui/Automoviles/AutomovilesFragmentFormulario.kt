package com.example.cars_motors.ui.Automoviles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.cars_motors.R
import com.example.cars_motors.controladores.AutomovilController
import com.example.cars_motors.controladores.ColoresController
import com.example.cars_motors.controladores.MarcasController
import com.example.cars_motors.controladores.TiposAutomovilController
import com.example.cars_motors.databinding.FormularioAutomovilesBinding
import com.example.cars_motors.modelos.Automovil
import com.example.cars_motors.modelos.TipoAutomovil

class AutomovilesFragmentFormulario : Fragment() {
    private var _binding: FormularioAutomovilesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FormularioAutomovilesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val automovilController = AutomovilController(requireContext())
        val automovilesColorController = ColoresController(requireContext())
        val automovilesMarcasController = MarcasController(requireContext())
        val automovilesTiposController = TiposAutomovilController(requireContext())
        val colores = automovilesColorController.getAllColores()
        val marcas = automovilesMarcasController.getAllMarcas()
        val tipos = automovilesTiposController.getAllTiposAutomovil()
        val listaColores = colores.map { it.nombre }
        val listaMarcas = marcas.map { it.nombre }
        val listaTipos = tipos.map { it.descripcion }
        val colorAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, listaColores)
        val marcaAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, listaMarcas)
        val tipoAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, listaTipos)


        val idauto = arguments?.getInt("idAuto") ?: 0
        val auto = automovilController.getAutomovilById(idauto)
        val marca = automovilesMarcasController.getMarcaById(auto?.idMarca ?: 0)
        val color = automovilesColorController.getColorById(auto?.idColor ?: 0)
        val tipo = automovilesTiposController.getTipoAutomovilById(auto?.idTipoAutomovil ?: 0)

        if (auto != null) {
            binding.txtModeloAutomovil.setText(auto.modelo)
            binding.txtNumeroVin.setText(auto.numeroVin)
            binding.txtChasis.setText(auto.numeroChasis)
            binding.txtNumeroMotor.setText(auto.numeroMotor)
            binding.txtNumeroAsientos.setText(auto.numeroAsientos.toString())
            binding.txtAnioVehiculo.setText(auto.anio.toString())
            binding.txtCapacidadAsientos.setText(auto.capacidadAsientos.toString())
            binding.txtPrecio.setText(auto.precio.toString())
            binding.txtDescripcion.setText(auto.descripcion)
            binding.toolColor.adapter = colorAdapter
            binding.toolMarca.adapter = marcaAdapter
            binding.toolTipoAuto.adapter = tipoAdapter

            // Setear color seleccionado
            val colores = automovilesColorController.getAllColores().map { it.nombre }.toTypedArray()
            val colorAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, colores)
            binding.toolColor.adapter = colorAdapter
            val indexColor = colores.indexOf(color?.nombre)
            if (indexColor != -1) {
                binding.toolColor.setSelection(indexColor)
            }

            // Setear marca seleccionada
            val marcas = automovilesMarcasController.getAllMarcas().map { it.nombre }.toTypedArray()
            val marcaAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, marcas)
            binding.toolMarca.adapter = marcaAdapter
            val indexMarca = marcas.indexOf(marca?.nombre)
            if (indexMarca != -1) {
                binding.toolMarca.setSelection(indexMarca)
            }

            // Setear tipo seleccionado
            val tipos = automovilesTiposController.getAllTiposAutomovil().map<TipoAutomovil, String> { it.descripcion }.toTypedArray()
            val tipoAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, tipos)
            binding.toolTipoAuto.adapter = tipoAdapter
            val indexTipo = tipos.indexOf(tipo?.descripcion)// ayuda aqui
            if (indexTipo != -1) {
                binding.toolTipoAuto.setSelection(indexTipo)
            }


        }

        binding.btnGuardar.setOnClickListener {
            val modelo = binding.txtModeloAutomovil.text.toString()
            val numeroVin = binding.txtNumeroVin.text.toString()
            val numeroChasis = binding.txtChasis.text.toString()
            val numeroMotor = binding.txtNumeroMotor.text.toString()
            val numeroAsientos = binding.txtNumeroAsientos.text.toString()
            val anio = binding.txtAnioVehiculo.text.toString()
            val capacidadAsientos = binding.txtCapacidadAsientos.text.toString()
            val precio = binding.txtPrecio.text.toString().toDoubleOrNull()
            val descripcion = binding.txtDescripcion.text.toString()
            val marcaId = binding.toolMarca.selectedItemId
            val colorId = binding.toolColor.selectedItemId
            val tipoId = binding.toolTipoAuto.selectedItemId

            if (modelo.isBlank() || numeroVin.isBlank() || numeroChasis.isBlank() || numeroMotor.isBlank() || numeroAsientos.isBlank() || anio.isBlank() || capacidadAsientos.isBlank() || precio == null || descripcion.isBlank() || marcaId == -1L || colorId == -1L || tipoId == -1L) {
                // Algunos campos no están completos
                Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val automovil = Automovil()
            automovil.modelo = modelo
            automovil.numeroVin = numeroVin
            automovil.numeroChasis = numeroChasis
            automovil.numeroMotor = numeroMotor
            automovil.numeroAsientos = numeroAsientos.toInt()
            automovil.anio = anio.toInt()
            automovil.capacidadAsientos = capacidadAsientos.toInt()
            automovil.precio = precio
            automovil.descripcion = descripcion
            automovil.idMarca = marcaId.toInt()
            automovil.idColor = colorId.toInt()
            automovil.idTipoAutomovil = tipoId.toInt()

            if (idauto == 0) {
                automovilController.insertAutomovil(automovil)
                Toast.makeText(requireContext(), "Automóvil Creado con éxito", Toast.LENGTH_SHORT).show()
            } else {
                automovil.id = idauto
                automovilController.updateAutomovil(automovil)
                Toast.makeText(requireContext(), "Automóvil Modificado con éxito", Toast.LENGTH_SHORT).show()
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