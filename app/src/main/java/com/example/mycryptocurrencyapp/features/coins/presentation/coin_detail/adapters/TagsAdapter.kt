package com.example.mycryptocurrencyapp.features.coins.presentation.coin_detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mycryptocurrencyapp.databinding.ItemTagBinding

class TagsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class TagsViewHolder(
        itemBinding: ItemTagBinding,
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        var tvName = itemBinding.tvTagText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        val binding = ItemTagBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return TagsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tag = differ.currentList[position]
        val tagsHolder: TagsViewHolder = holder as TagsViewHolder
        tagsHolder.apply {
            tvName.text = tag.toString()
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<String>) {
        differ.submitList(list)
        notifyDataSetChanged()
    }


    val DiffCallback = object : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, DiffCallback)
}
