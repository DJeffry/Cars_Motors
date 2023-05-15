package com.example.cars_motors.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cars_motors.controladores.MarcasController
import com.example.cars_motors.modelos.Automovil
import com.example.cars_motors.databinding.ItemlistAutosBinding

class AutosAdapter(private val mContext: Context, private val listaGrupos: List<Automovil>) : RecyclerView.Adapter<AutosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistAutosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        private val binding = ItemlistAutosBinding.bind(itemView)
        val marcaController = MarcasController(itemView.context)

        fun bind(grupo: Automovil, position: Int) {
            binding.lblMarka.text = marcaController.getMarcaById(grupo.idMarca)?.nombre
            binding.lblModel.text = grupo.modelo
            binding.anio.text = grupo.anio.toString()


        }
    }
}