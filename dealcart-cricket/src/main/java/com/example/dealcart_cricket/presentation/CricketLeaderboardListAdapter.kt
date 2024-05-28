package com.example.dealcart_cricket.presentation


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.dealcart_cricket.R
import com.example.dealcart_cricket.databinding.CricketGameLeaderboardItemBinding
import com.example.dealcart_cricket.data.LeaderboardListUiData

class CricketLeaderboardListAdapter(private val filterList: List<LeaderboardListUiData>) :
    RecyclerView.Adapter<CricketLeaderboardListAdapter.LeaderboardListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): LeaderboardListViewHolder {
        return LeaderboardListViewHolder(
            CricketGameLeaderboardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun onBindViewHolder(holder: LeaderboardListViewHolder, position: Int) {
        holder.onBind(filterList[position], position)
    }

    inner class LeaderboardListViewHolder(
        private val binding: CricketGameLeaderboardItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: LeaderboardListUiData, position: Int) {
            binding.model = item
            binding.tvName.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.secondary_color
                )
            )
        }
    }
}