package com.example.cars_motors.ui.Favoritos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cars_motors.R
import com.example.cars_motors.controladores.AutomovilController
import com.example.cars_motors.controladores.MarcasController
import com.example.cars_motors.databinding.ItemlistFavoritosCrudBinding
import com.example.cars_motors.modelos.FavoritoAutomovil

class FavoritosAdapter(private val mContext: Context, private val listaGrupos: List<FavoritoAutomovil>) : RecyclerView.Adapter<FavoritosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemlistFavoritosCrudBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        private val binding = ItemlistFavoritosCrudBinding.bind(itemView)
        val favoritosController = AutomovilController(itemView.context)
        val MarcaController = MarcasController(itemView.context)

        fun bind(grupo: FavoritoAutomovil, position: Int) {
            binding.Nombre.text = MarcaController.getMarcaById(favoritosController.getAutomovilById(grupo.idAutomovil)!!.idMarca )?.nombre.toString()
            binding.id.text = favoritosController.getAutomovilById(grupo.idAutomovil)!!.modelo
            binding.edad.text = favoritosController.getAutomovilById(grupo.idAutomovil)!!.anio.toString()

            // Set click listener on the item view
            itemView.setOnClickListener {
                val navController = (mContext as AppCompatActivity).findNavController(R.id.nav_host_fragment_content_main)
                val bundle = bundleOf("listaGrupos" to listaGrupos.toTypedArray())
                navController.navigate(R.id.nav_favoritos, bundle)
            }

        }
    }
}