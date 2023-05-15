package com.example.cars_motors.ui.Usuarios

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
import com.example.cars_motors.controladores.UsuariosController
import com.example.cars_motors.databinding.ItemlistUsuariosCrudBinding
import com.example.cars_motors.modelos.Usuario

class UsuariosAdapter(private val mContext: Context, private val listaGrupos: MutableList<Usuario>,private val usuariosController: UsuariosController) : RecyclerView.Adapter<UsuariosAdapter.ViewHolder>() {

    fun eliminarItem(position: Int) {
        val automovil = listaGrupos[position]
        val deletedRows = usuariosController.deleteUsuariobyid(automovil.id)
        if (deletedRows > 0) {
            listaGrupos.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, listaGrupos.size - position)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistUsuariosCrudBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        private val binding = ItemlistUsuariosCrudBinding.bind(itemView)
        val usuariosController = UsuariosController(itemView.context)

        fun bind(grupo: Usuario, position: Int) {
            binding.Nombre.text = grupo.nombre
            binding.tipo.text = grupo.tipo
            binding.id.text = grupo.apellido

            // Set click listener on the item view
            itemView.setOnClickListener {
                val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
                val bundle = bundleOf("listaGrupos" to listaGrupos.toTypedArray())
                navController.navigate(R.id.nav_usuarios, bundle)
            }

        }
    }
}