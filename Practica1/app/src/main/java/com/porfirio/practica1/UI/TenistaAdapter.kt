package com.porfirio.practica1.UI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.porfirio.practica1.Model.TenistaEntity
import com.porfirio.practica1.databinding.TenistaElementBinding

class TenistaAdapter(private val onTenistaClick:(TenistaEntity)-> Unit): RecyclerView.Adapter<TenistaAdapter.ViewHolder>() {
    private var tenistas: List<TenistaEntity> = emptyList()

    class ViewHolder(private val binding: TenistaElementBinding): RecyclerView.ViewHolder(binding.root){
        val ivIcon = binding.ivIcon

        fun bind(tenista: TenistaEntity){
            binding.apply{
                tvNombre.text = tenista.nombre
                tvRanking.text = tenista.ranking
                tvPuntos.text = tenista.puntos
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TenistaElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = tenistas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(tenistas[position])

        holder.itemView.setOnClickListener{
            //Aquí va el click del elemento
            onTenistaClick(tenistas[position])
        }

        holder.ivIcon.setOnClickListener{
            //Se escucha cuando se da click en el icono del imageView
        }
    }

    fun updateList(list: List<TenistaEntity>){
        tenistas= list
        notifyDataSetChanged()
    }
}