package com.ifs21007.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21007.dinopedia.databinding.FamItemBinding


class ListFamAdapter(private val listFam: ArrayList<Fam>) : RecyclerView.Adapter<ListFamAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null // Inisialisasi properti onItemClickCallback

    // Setter untuk onItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk onItemClickCallback
    interface OnItemClickCallback {
        fun onItemClicked(data: Fam)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = FamItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val fam = listFam[position]
        holder.bind(fam)
    }

    override fun getItemCount(): Int = listFam.size

    inner class ListViewHolder(private val binding: FamItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fam: Fam) {
            with(binding) {
                tvItemFam.text = fam.title
                ivItemFam.setImageResource(fam.icon)
                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(fam) // Panggil onItemClickCallback saat item diklik
                }
            }
        }
    }
}
