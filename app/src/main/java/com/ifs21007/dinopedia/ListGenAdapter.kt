package com.ifs21007.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21007.dinopedia.databinding.GenItemBinding


class ListGenAdapter(private val listGen: ArrayList<Gen>) :
    RecyclerView.Adapter<ListGenAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(var binding: GenItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListGenAdapter.ListViewHolder {
        val binding = GenItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListGenAdapter.ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListGenAdapter.ListViewHolder, position: Int) {
        val gen = listGen[position]
        holder.binding.ivGen.setImageResource(gen.icon)
        holder.binding.tvGen.text = gen.dino

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(gen)
        }
    }

    override fun getItemCount(): Int = listGen.size



    interface OnItemClickCallback {
        fun onItemClicked(data: Gen)
    }
}