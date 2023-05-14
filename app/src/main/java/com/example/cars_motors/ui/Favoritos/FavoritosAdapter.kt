package com.example.cars_motors.ui.Favoritos

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
import com.example.cars_motors.controladores.FavoritosAutomovilController
import com.example.cars_motors.controladores.MarcasController
import com.example.cars_motors.databinding.ItemlistFavoritosCrudBinding
import com.example.cars_motors.modelos.FavoritoAutomovil

class FavoritosAdapter(private val mContext: Context, private val listaGrupos: MutableList<FavoritoAutomovil>,private val favoritosAutomovilController: FavoritosAutomovilController) : RecyclerView.Adapter<FavoritosAdapter.ViewHolder>() {

    fun eliminarItem(position: Int) {
        val automovil = listaGrupos[position]
        val deletedRows = favoritosAutomovilController.deleteFavoritoAutomovilbyid(automovil.id)
        if (deletedRows > 0) {
            listaGrupos.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, listaGrupos.size - position)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistFavoritosCrudBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val grupo = listaGrupos[position]
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
        private val binding = ItemlistFavoritosCrudBinding.bind(itemView)
        val favoritosController = AutomovilController(itemView.context)
        val MarcaController = MarcasController(itemView.context)

        fun bind(grupo: FavoritoAutomovil, position: Int) {
            binding.Nombre.text = MarcaController.getMarcaById(favoritosController.getAutomovilById(grupo.idAutomovil)!!.idMarca )?.nombre.toString()
            binding.id.text = favoritosController.getAutomovilById(grupo.idAutomovil)!!.modelo
            binding.edad.text = favoritosController.getAutomovilById(grupo.idAutomovil)!!.anio.toString()


        }
    }
}