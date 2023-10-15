package com.example.myinterviewapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myinterviewapp.R
import com.example.myinterviewapp.model.DataObject

class GifsAdapter(
    private val context: Context
) : RecyclerView.Adapter<GifsViewHolder>() {

    interface OnItemClickListener {

        fun onItemClick(position: Int)
    }

    private lateinit var onItemClick: OnItemClickListener

    private var gifs: List<DataObject> = listOf()

    fun setOnCLickListener(listener: OnItemClickListener) {
        onItemClick = listener
    }

    fun setGifs(values: List<DataObject>) {
        gifs = values
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifsViewHolder {
        return GifsViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item,
                parent,
                false
            ),
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: GifsViewHolder, position: Int) {
        val data = gifs[position]

        Glide.with(context)
            .load(data.images.originalImage.url)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return gifs.size
    }
}