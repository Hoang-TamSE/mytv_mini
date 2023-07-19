package com.tamse.mytv_small.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tamse.mytv_small.data.model.Channel
import com.tamse.mytv_small.databinding.GridViewItemBinding

class ChannelAdapter(val clickListener: ChannelListener): ListAdapter
<Channel, ChannelAdapter.ChannelViewHolder>(DiffCallback) {
    class ChannelViewHolder(private var binding:
                            GridViewItemBinding)
        :
        RecyclerView.ViewHolder(binding.root){
            fun bind(clickListener: ChannelListener, channel: Channel) {
                binding.channel = channel
                binding.clickListener = clickListener
                binding.executePendingBindings()
            }

    }
    companion object DiffCallback: DiffUtil.ItemCallback<Channel>() {
        override fun areItemsTheSame(oldItem: Channel, newItem: Channel): Boolean {
            return oldItem.contentID == newItem.contentID
        }

        override fun areContentsTheSame(oldItem: Channel, newItem: Channel): Boolean {
            return oldItem.contentImageUrl == newItem.contentImageUrl
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelAdapter.ChannelViewHolder {
        return ChannelViewHolder((GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)
        )))
    }

    override fun onBindViewHolder(holder: ChannelAdapter.ChannelViewHolder, position: Int) {
        val channel = getItem(position)
        holder.bind(clickListener, channel)
    }

    override fun submitList(list: MutableList<Channel>?) {
        super.submitList(list)
    }
}

class ChannelListener(val clickListener: (channel: Channel) -> Unit) {
    fun onClick(channel: Channel) = clickListener(channel)
}
