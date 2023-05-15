package com.example.cars_motors.ui.Marcas

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
import com.example.cars_motors.controladores.MarcasController
import com.example.cars_motors.databinding.ItemlistMarcasCrudBinding
import com.example.cars_motors.modelos.Marca

class MarcasAdapter(private val mContext: Context, private val listaGrupos: MutableList<Marca>, private val marcasController: MarcasController) : RecyclerView.Adapter<MarcasAdapter.ViewHolder>() {

    fun eliminarItem(position: Int) {
        val automovil = listaGrupos[position]
        val deletedRows = marcasController.deleteMarcabyid(automovil.id)
        if (deletedRows > 0) {
            listaGrupos.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, listaGrupos.size - position)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistMarcasCrudBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        private val binding = ItemlistMarcasCrudBinding.bind(itemView)

        fun bind(grupo: Marca, position: Int) {
            binding.Nombre.text = grupo.nombre
            binding.id.text = grupo.id.toString()



        }
    }
}