package com.example.cars_motors.ui.ColorModel

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
import com.example.cars_motors.controladores.ColoresController
import com.example.cars_motors.databinding.ItemlistColoresCrudBinding
import com.example.cars_motors.modelos.ColorModel
import com.example.cars_motors.modelos.Usuario

class ColoresAdapter(private val mContext: Context, private val listaColor: MutableList<ColorModel>, private val ColorController: ColoresController) : RecyclerView.Adapter<ColoresAdapter.ViewHolder>() {

    fun eliminarItem(position: Int) {
        val color = listaColor[position]
        val deletedRows = ColorController.deleteColorbyid(color.id)
        if (deletedRows > 0) {
            listaColor.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, listaColor.size - position)
        }
    }

    fun getColorByPosition(position: Int): ColorModel? {
        if (position < listaColor.size) {
            return listaColor[position]
        }
        return null
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistColoresCrudBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val color = listaColor[position]

        holder.binding.lblMarka.text = color.nombre.toString()
        holder.binding.lblModel.text = color.id.toString()

        holder.binding.btnModificar.setOnClickListener {
            val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
            val bundle = bundleOf("idColor" to color.id)
            navController.navigate(R.id.formulario_colores, bundle)
        }

        holder.binding.btnEliminar.setOnClickListener {
            eliminarItem(position)
        }

        holder.itemView.setOnClickListener {
            val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
            val idposition = getColorByPosition(position)?.id
            val bundle = bundleOf("idColor" to idposition)
            navController.navigate(R.id.vista_colores, bundle)
        }
    }

    override fun getItemCount(): Int {
        return listaColor.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemlistColoresCrudBinding.bind(itemView)

        fun bind(color: ColorModel, position: Int) {
            binding.lblMarka.text = color.nombre.toString()
            binding.lblModel.text = color.id.toString()




            binding.btnEliminar.setOnClickListener {
                eliminarItem(position)
            }

            itemView.setOnClickListener {
                val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
                val bundle = bundleOf("idColor" to color.id)
                navController.navigate(R.id.vista_colores, bundle)
            }

        }
    }
}
