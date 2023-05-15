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

class UsuariosAdapter(private val mContext: Context, private val listaUsuarios: MutableList<Usuario>, private val usuariosController: UsuariosController) : RecyclerView.Adapter<UsuariosAdapter.ViewHolder>() {

    fun eliminarItem(position: Int) {
        val usuario = listaUsuarios[position]
        val deletedRows = usuariosController.deleteUsuariobyid(usuario.id)
        if (deletedRows > 0) {
            listaUsuarios.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, listaUsuarios.size - position)
        }
    }

    fun getUsuarioByPosition(position: Int): Usuario? {
        if (position < listaUsuarios.size) {
            return listaUsuarios[position]
        }
        return null
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistUsuariosCrudBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario = listaUsuarios[position]

        holder.binding.Nombre.text = usuario.nombre
        holder.binding.tipo.text = usuario.tipo
        holder.binding.id.text = usuario.id.toString()

        holder.binding.btnModificar.setOnClickListener {
            val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
            val bundle = bundleOf("idUsuario" to usuario.id)
            navController.navigate(R.id.formulario_usuarios, bundle)
        }

        holder.binding.btnEliminar.setOnClickListener {
            eliminarItem(position)
        }

        holder.itemView.setOnClickListener {
            val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
            val idposition = getUsuarioByPosition(position)?.id
            val bundle = bundleOf("idUsuario" to idposition)
            navController.navigate(R.id.vista_usuarios, bundle)
        }
    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemlistUsuariosCrudBinding.bind(itemView)

        fun bind(usuario: Usuario, position: Int) {
            binding.Nombre.text = usuario.nombre
            binding.tipo.text = usuario.tipo
            binding.id.text = usuario.id.toString()




            binding.btnEliminar.setOnClickListener {
                eliminarItem(position)
            }

            itemView.setOnClickListener {
                val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
                val bundle = bundleOf("idUsuario" to usuario.id)
                navController.navigate(R.id.vista_usuarios, bundle)
            }

        }
    }
}
