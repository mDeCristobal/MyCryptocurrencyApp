package com.example.mycryptocurrencyapp.features.coins.presentation.coin_list.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mycryptocurrencyapp.R
import com.example.mycryptocurrencyapp.databinding.ItemCoinBinding
import com.example.mycryptocurrencyapp.features.coins.domain.model.Coin

class CoinListAdapter(private val interaction: Interaction) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class CoinViewHolder(
        itemBinding: ItemCoinBinding,
        context: Context
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        var mContext = context
        var tvName = itemBinding.tvName
        var tvActive = itemBinding.tvActive
        var lytItem = itemBinding.coinLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ItemCoinBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CoinViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val coin = differ.currentList[position]
        val coinHolder: CoinViewHolder = holder as CoinViewHolder
        coinHolder.apply {
            tvName.text = mContext.resources.getString(
                R.string.coin_list_name,
                coin.rank.toString(),
                coin.name
            )
            tvActive.text = if(coin.isActive) {
                mContext.resources.getString(
                    R.string.coin_is_active,
                )
            } else {
                mContext.resources.getString(
                    R.string.coin_is_not_active,
                )
            }
            lytItem.setOnClickListener { interaction.navigateToCoinDetail(coin.id) }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Coin>) {
        differ.submitList(list)
        notifyDataSetChanged()
    }

    interface Interaction {
        fun navigateToCoinDetail(coinId: String)
    }

    val DiffCallback = object : DiffUtil.ItemCallback<Coin>() {

        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

    }

    private val differ = AsyncListDiffer(this, DiffCallback)


}