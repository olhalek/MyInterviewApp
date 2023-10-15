package com.example.myinterviewapp.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myinterviewapp.R

class GifsViewHolder(
    itemView: View,
    listener: GifsAdapter.OnItemClickListener
) : RecyclerView.ViewHolder(itemView) {
    val imageView = itemView.findViewById<ImageView>(R.id.imGif)

    init {
        imageView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }
}