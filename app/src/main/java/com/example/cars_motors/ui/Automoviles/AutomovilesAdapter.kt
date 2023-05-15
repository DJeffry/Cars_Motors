package com.example.cars_motors.ui.Automoviles

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cars_motors.R
import com.example.cars_motors.controladores.AutomovilController
import com.example.cars_motors.controladores.MarcasController
import com.example.cars_motors.databinding.ItemlistAutosCrudBinding
import com.example.cars_motors.modelos.Automovil
import com.example.cars_motors.modelos.Usuario

class AutomovilesAdapter(private val mContext: Context, private val listaAutomoviles: MutableList<Automovil>, private val AutomovilesController: AutomovilController) : RecyclerView.Adapter<AutomovilesAdapter.ViewHolder>() {

    fun eliminarItem(position: Int) {
        val automovil = listaAutomoviles[position]
        val deletedRows = AutomovilesController.deleteAutomovilById(automovil.id)
        if (deletedRows > 0) {
            listaAutomoviles.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, listaAutomoviles.size - position)
        }
    }

    fun getAutomovilByPosition(position: Int): Automovil? {
        if (position < listaAutomoviles.size) {
            return listaAutomoviles[position]
        }
        return null
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistAutosCrudBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val atomovil = listaAutomoviles[position]

        holder.binding.lblMarka.text = atomovil.idMarca.toString()
        holder.binding.lblModel.text = atomovil.modelo.toString()
        holder.binding.lblAnyo.text = atomovil.anio.toString()

        holder.binding.btnModificar.setOnClickListener {
            val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
            val bundle = bundleOf("idAuto" to atomovil.id)
            navController.navigate(R.id.formulario_autos, bundle)
        }

        holder.binding.btnEliminar.setOnClickListener {
            eliminarItem(position)
        }

        holder.itemView.setOnClickListener {
            val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
            val idposition = getAutomovilByPosition(position)?.id
            val bundle = bundleOf("idAuto" to idposition)
            navController.navigate(R.id.vista_autos, bundle)
        }
    }

    override fun getItemCount(): Int {
        return listaAutomoviles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemlistAutosCrudBinding.bind(itemView)
        val favoritosController = AutomovilController(itemView.context)
        val MarcaController = MarcasController(itemView.context)

        fun bind(automovil: Automovil, position: Int) {
            binding.lblMarka.text = MarcaController.getMarcaById(favoritosController.getAutomovilById(automovil.id)!!.idMarca )?.nombre.toString()
            binding.lblModel.text = automovil.modelo.toString()
            binding.lblAnyo.text = automovil.anio.toString()

            binding.btnEliminar.setOnClickListener {
                eliminarItem(position)
            }

            itemView.setOnClickListener {
                val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
                val bundle = bundleOf("idAutomovil" to automovil.id)
                navController.navigate(R.id.vista_autos, bundle)
            }

        }
    }
}
