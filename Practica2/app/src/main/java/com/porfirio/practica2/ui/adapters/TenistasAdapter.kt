package com.porfirio.practica2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.porfirio.practica2.data.remote.model.TenistaDto
import com.porfirio.practica2.databinding.TenistaElementBinding

class TenistasAdapter (
    private val tenistas:List<TenistaDto>,
    private val onTenistaClicked: (TenistaDto) -> Unit
    ): RecyclerView.Adapter<TenistasAdapter.ViewHolder>(){
        class ViewHolder(private val binding: TenistaElementBinding): RecyclerView.ViewHolder(binding.root){
            val ivThumbnail = binding.ivThumbnail
            fun bind(tenista: TenistaDto){
                binding.tvTitle.text = "Tennis player"
                binding.tvName.text = tenista.nombre
                binding.tvSurname.text = tenista.apellido
                binding.tvEdad.text = tenista.edad.toString()

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = TenistaElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun getItemCount(): Int = tenistas.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val tenista = tenistas[position]

            holder.bind(tenista)

            //Con Glide
            Glide.with(holder.itemView.context)
                .load(tenista.thumbnail)
                .into(holder.ivThumbnail)

            //Procesamiento del clic al elemento
            holder.itemView.setOnClickListener {
                onTenistaClicked(tenista)
            }
        }
}