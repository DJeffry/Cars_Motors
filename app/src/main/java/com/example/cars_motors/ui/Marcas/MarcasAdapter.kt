package com.example.cars_motors.ui.Marca

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
import com.example.cars_motors.modelos.Usuario

class MarcasAdapter(private val mContext: Context, private val listaMarca: MutableList<Marca>, private val MarcaController: MarcasController) : RecyclerView.Adapter<MarcasAdapter.ViewHolder>() {

    fun eliminarItem(position: Int) {
        val color = listaMarca[position]
        val deletedRows = MarcaController.deleteMarcabyid(color.id)
        if (deletedRows > 0) {
            listaMarca.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, listaMarca.size - position)
        }
    }

    fun getMarcaByPosition(position: Int): Marca? {
        if (position < listaMarca.size) {
            return listaMarca[position]
        }
        return null
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistMarcasCrudBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val color = listaMarca[position]

        holder.binding.lblMarka.text = color.nombre.toString()
        holder.binding.lblModel.text = color.id.toString()

        holder.binding.btnModificar.setOnClickListener {
            val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
            val bundle = bundleOf("idMarca" to color.id)
            navController.navigate(R.id.formulario_marcas, bundle)
        }

        holder.binding.btnEliminar.setOnClickListener {
            eliminarItem(position)
        }

        holder.itemView.setOnClickListener {
            val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
            val idposition = getMarcaByPosition(position)?.id
            val bundle = bundleOf("idMarca" to idposition)
            navController.navigate(R.id.vista_marcas, bundle)
        }
    }

    override fun getItemCount(): Int {
        return listaMarca.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemlistMarcasCrudBinding.bind(itemView)

        fun bind(color: Marca, position: Int) {
            binding.lblMarka.text = color.nombre.toString()
            binding.lblModel.text = color.id.toString()

            // Set click listener on the item view
            itemView.setOnClickListener {
                val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
                val bundle = bundleOf("listaGrupos" to listaGrupos.toTypedArray())
                navController.navigate(R.id.nav_favoritos, bundle)
            }


            binding.btnEliminar.setOnClickListener {
                eliminarItem(position)
            }

            itemView.setOnClickListener {
                val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
                val bundle = bundleOf("idMarca" to color.id)
                navController.navigate(R.id.vista_colores, bundle)
            }

        }
    }
}
