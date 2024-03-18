package com.ifs21007.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21007.dinopedia.databinding.FamBinding
class ListPoseAdapter(private val listDino: ArrayList<Fam>) :
    RecyclerView.Adapter<ListPoseAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback:
                               OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
    Int): ListViewHolder {
        val binding =
            FamBinding.inflate(LayoutInflater.from(viewGroup.context),
                viewGroup, false)
        return ListViewHolder(binding)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position:
    Int) {
        val dino = listDino[position]
        holder.binding.ivItemPose.setImageResource(dino.icon)
        holder.binding.tvItemPose.text =dino.dino
        holder.itemView.setOnClickListener {
            onItemClickCallback
                .onItemClicked(listDino[holder.adapterPosition])
        }
    }
    override fun getItemCount(): Int = listDino.size
    class ListViewHolder(var binding: FamBinding) :
        RecyclerView.ViewHolder(binding.root)
    interface OnItemClickCallback {
        fun onItemClicked(data: Fam)
    }
}
