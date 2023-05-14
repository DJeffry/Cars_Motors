package com.example.cars_motors.ui.Automoviles

import android.content.Context
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
import com.example.cars_motors.controladores.TiposAutomovilController
import com.example.cars_motors.modelos.Automovil
import com.example.cars_motors.databinding.ItemlistAutosCrudBinding

class AutomovilesAdapter(private val mContext: Context,private val listaAutomoviles: MutableList<Automovil>,private val automovilController: AutomovilController) : RecyclerView.Adapter<AutomovilesAdapter.ViewHolder>() {

    fun eliminarItem(position: Int) {
        val automovil = listaAutomoviles[position]
        val deletedRows = automovilController.deleteAutomovilById(automovil.id)
        if (deletedRows > 0) {
            listaAutomoviles.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, listaAutomoviles.size - position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistAutosCrudBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val automovil = listaAutomoviles[position]
        holder.itemView.findViewById<TextView>(R.id.Nombre).text = automovil.modelo
        holder.itemView.findViewById<TextView>(R.id.id).text = automovil.numeroVin

        holder.itemView.findViewById<Button>(R.id.btnModificar).setOnClickListener {
            // TODO: implementar acción para el botón "ver"
        }

        holder.itemView.findViewById<Button>(R.id.btnEliminar).setOnClickListener {
            eliminarItem(position)
        }
    }


    override fun getItemCount(): Int {
        return listaAutomoviles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemlistAutosCrudBinding.bind(itemView)
        val tiposController = TiposAutomovilController(itemView.context)

        fun bind(grupo: Automovil, position: Int) {
            binding.Nombre.text = grupo.descripcion
            binding.id.text = grupo.id.toString()

            // Set click listener on the item view
            itemView.setOnClickListener {
                val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
                val bundle = bundleOf("listaAutomoviles" to listaAutomoviles.toTypedArray())
                navController.navigate(R.id.nav_favoritos, bundle)
            }
        }
    }
}