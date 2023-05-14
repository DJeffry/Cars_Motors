package com.example.cars_motors.ui.Colores

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
import com.example.cars_motors.controladores.ColoresController
import com.example.cars_motors.modelos.ColorModel
import com.example.cars_motors.databinding.ItemlistColoresCrudBinding

class ColoresAdapter(private val mContext: Context, private val listaGrupos: MutableList<ColorModel>,private val coloresController: ColoresController ) : RecyclerView.Adapter<ColoresAdapter.ViewHolder>() {

    fun eliminarItem(position: Int) {
        val automovil = listaGrupos[position]
        val deletedRows = coloresController.deleteColorbyid(automovil.id)
        if (deletedRows > 0) {
            listaGrupos.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, listaGrupos.size - position)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistColoresCrudBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val grupo = listaGrupos[position]
        holder.itemView.findViewById<TextView>(R.id.Nombre).text = grupo.nombre
        holder.itemView.findViewById<TextView>(R.id.id).text = grupo.id.toString()

        holder.itemView.findViewById<Button>(R.id.btnModificar).setOnClickListener {
            // TODO: implementar acción para el botón "ver"
        }

        holder.itemView.findViewById<Button>(R.id.btnEliminar).setOnClickListener {
            eliminarItem(position)
        }
    }

    override fun getItemCount(): Int {
        return listaGrupos.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemlistColoresCrudBinding.bind(itemView)
        val coloresController = ColoresController(itemView.context)

        fun bind(grupo: ColorModel, position: Int) {
            binding.Nombre.text = grupo.nombre
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