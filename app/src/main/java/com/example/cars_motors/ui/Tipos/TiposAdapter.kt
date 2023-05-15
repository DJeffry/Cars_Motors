package com.example.cars_motors.ui.Tipo

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
import com.example.cars_motors.databinding.ItemlistTiposCrudBinding
import com.example.cars_motors.modelos.TipoAutomovil
import com.example.cars_motors.modelos.Usuario

class TiposAdapter(private val mContext: Context, private val listaTipo: MutableList<TipoAutomovil>, private val TipoController: TiposAutomovilController) : RecyclerView.Adapter<TiposAdapter.ViewHolder>() {

    fun eliminarItem(position: Int) {
        val tipo = listaTipo[position]
        val deletedRows = TipoController.deleteTipoAutomovilbyid(tipo.id)
        if (deletedRows > 0) {
            listaTipo.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, listaTipo.size - position)
        }
    }

    fun getTipoByPosition(position: Int): TipoAutomovil? {
        if (position < listaTipo.size) {
            return listaTipo[position]
        }
        return null
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistTiposCrudBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tipo = listaTipo[position]

        holder.binding.lblMarka.text = tipo.descripcion.toString()
        holder.binding.lblModel.text = tipo.id.toString()

        holder.binding.btnModificar.setOnClickListener {
            val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
            val bundle = bundleOf("idTipo" to tipo.id)
            navController.navigate(R.id.formulario_tipos, bundle)
        }

        holder.binding.btnEliminar.setOnClickListener {
            eliminarItem(position)
        }

        holder.itemView.setOnClickListener {
            val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
            val idposition = getTipoByPosition(position)?.id
            val bundle = bundleOf("idTipo" to idposition)
            navController.navigate(R.id.vista_tipos, bundle)
        }
    }

    override fun getItemCount(): Int {
        return listaTipo.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemlistTiposCrudBinding.bind(itemView)

        fun bind(tipo: TipoAutomovil, position: Int) {
            binding.lblMarka.text = tipo.descripcion.toString()
            binding.lblModel.text = tipo.id.toString()




            binding.btnEliminar.setOnClickListener {
                eliminarItem(position)
            }

            itemView.setOnClickListener {
                val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
                val bundle = bundleOf("idTipo" to tipo.id)
                navController.navigate(R.id.vista_tipos, bundle)
            }

        }
    }
}
