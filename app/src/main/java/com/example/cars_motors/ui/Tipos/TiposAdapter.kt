package com.example.cars_motors.ui.Tipos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cars_motors.R
import com.example.cars_motors.controladores.TiposAutomovilController
import com.example.cars_motors.modelos.TipoAutomovil
import com.example.cars_motors.databinding.ItemlistTiposCrudBinding

class TiposAdapter(private val mContext: Context, private val listaGrupos: List<TipoAutomovil>) : RecyclerView.Adapter<TiposAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistTiposCrudBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val grupo = listaGrupos[position]
        holder.bind(grupo, position)
    }

    override fun getItemCount(): Int {
        return listaGrupos.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemlistTiposCrudBinding.bind(itemView)
        val tiposController = TiposAutomovilController(itemView.context)

        fun bind(grupo: TipoAutomovil, position: Int) {
            binding.Nombre.text = grupo.descripcion
            binding.id.text = grupo.id.toString()

            // Set click listener on the item view
            itemView.setOnClickListener {
                val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
                val bundle = bundleOf("listaGrupos" to listaGrupos.toTypedArray())
                navController.navigate(R.id.nav_favoritos, bundle)
            }
        }
    }
}