package com.example.cars_motors.ui.Tipos

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
import com.example.cars_motors.controladores.TiposAutomovilController
import com.example.cars_motors.modelos.TipoAutomovil
import com.example.cars_motors.databinding.ItemlistTiposCrudBinding

class TiposAdapter(private val mContext: Context, private val listaGrupos: MutableList<TipoAutomovil>, private val tiposAutomovilController: TiposAutomovilController ) : RecyclerView.Adapter<TiposAdapter.ViewHolder>() {
    fun eliminarItem(position: Int) {
        val automovil = listaGrupos[position]
        val deletedRows = tiposAutomovilController.deleteTipoAutomovilbyid(automovil.id)
        if (deletedRows > 0) {
            listaGrupos.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, listaGrupos.size - position)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistTiposCrudBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val grupo = listaGrupos[position]
        holder.itemView.findViewById<TextView>(R.id.Nombre).text = grupo.descripcion
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
        private val binding = ItemlistTiposCrudBinding.bind(itemView)
        val tiposController = TiposAutomovilController(itemView.context)

        fun bind(grupo: TipoAutomovil, position: Int) {
            binding.Nombre.text = grupo.descripcion
            binding.id.text = grupo.id.toString()


        }
    }
}