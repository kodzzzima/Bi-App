package com.example.biapp.presentation.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.biapp.data.local.sample.SampleItem
import com.example.biapp.databinding.FragmentSampleBinding
import javax.inject.Inject

class SampleAdapter @Inject constructor(
    private val onItemClick: (SampleItem) -> Unit,
) : ListAdapter<SampleItem, SampleViewHolder>(DetectionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FragmentSampleBinding.inflate(layoutInflater, parent, false)

        return SampleViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class SampleViewHolder(
    private val binding: FragmentSampleBinding,
    private val onItemClick: (SampleItem) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(sampleItem: SampleItem) {
        with(binding) {
            //TODO
        }

        itemView.setOnClickListener { onItemClick(sampleItem) }
    }
}

private class DetectionDiffCallback : DiffUtil.ItemCallback<SampleItem>() {

    override fun areItemsTheSame(
        oldItem: SampleItem,
        newItem: SampleItem,
    ): Boolean =
        (oldItem.id == newItem.id)

    override fun areContentsTheSame(
        oldItem: SampleItem,
        newItem: SampleItem,
    ): Boolean =
        (oldItem == newItem)
}