package com.example.viewpager2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PagerAdapter(private val bgColor: List<Int>) : RecyclerView.Adapter<PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pager, parent, false))

    override fun getItemCount(): Int = bgColor.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(bgColor[position], position)
    }

}
