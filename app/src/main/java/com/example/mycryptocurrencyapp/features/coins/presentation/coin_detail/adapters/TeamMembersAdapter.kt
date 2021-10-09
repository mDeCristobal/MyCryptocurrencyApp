package com.example.mycryptocurrencyapp.features.coins.presentation.coin_detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mycryptocurrencyapp.databinding.ItemTeamMemberBinding
import com.example.mycryptocurrencyapp.features.coins.data.remote.dto.TeamMember

class TeamMembersAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class CoinDetailViewHolder(
        itemBinding: ItemTeamMemberBinding,
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        var tvName = itemBinding.tvName
        var tvPosition = itemBinding.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinDetailViewHolder {
        val binding = ItemTeamMemberBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CoinDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val teamMember = differ.currentList[position]
        val coinDetailHolder: CoinDetailViewHolder = holder as CoinDetailViewHolder
        coinDetailHolder.apply {
            tvName.text = teamMember.name
            tvPosition.text = teamMember.position
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<TeamMember>) {
        differ.submitList(list)
        notifyDataSetChanged()
    }


    val DiffCallback = object : DiffUtil.ItemCallback<TeamMember>() {

        override fun areItemsTheSame(oldItem: TeamMember, newItem: TeamMember): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TeamMember, newItem: TeamMember): Boolean {
            return oldItem.id == newItem.id
        }

    }

    private val differ = AsyncListDiffer(this, DiffCallback)


}