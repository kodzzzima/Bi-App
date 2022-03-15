package com.example.biapp.presentation.intern.myresumes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.biapp.data.local.sample.SampleItem
import com.example.biapp.databinding.FragmentMyResumesBinding
import com.example.biapp.databinding.ItemMyResumeBinding
import javax.inject.Inject

class MyResumesAdapter @Inject constructor(
    private val onItemClick: (SampleItem) -> Unit,
) : ListAdapter<SampleItem, MyResumesViewHolder>(DetectionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyResumesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMyResumeBinding.inflate(layoutInflater, parent, false)

        return MyResumesViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MyResumesViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class MyResumesViewHolder(
    private val binding: ItemMyResumeBinding,
    private val onItemClick: (SampleItem) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(sampleItem: SampleItem) {
        with(binding) {
            binding.title.text = sampleItem.title
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