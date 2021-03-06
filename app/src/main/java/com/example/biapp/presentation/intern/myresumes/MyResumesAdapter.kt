package com.example.biapp.presentation.intern.myresumes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.biapp.data.local.sample.SampleItem
import com.example.biapp.databinding.FragmentMyResumesBinding
import com.example.biapp.databinding.ItemMyResumeBinding
import com.example.biapp.presentation.employer.resumelist.ResumeItem
import javax.inject.Inject

class MyResumesAdapter @Inject constructor(
    private val onItemClick: (ResumeItem) -> Unit,
) : ListAdapter<ResumeItem, MyResumesViewHolder>(DetectionDiffCallback()) {

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
    private val onItemClick: (ResumeItem) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(resumeItem: ResumeItem) {
        with(binding) {
            binding.title.text = resumeItem.title
            binding.company.text = resumeItem.contact
            binding.ref.text = resumeItem.skills
        }

        itemView.setOnClickListener { onItemClick(resumeItem) }
    }
}

private class DetectionDiffCallback : DiffUtil.ItemCallback<ResumeItem>() {

    override fun areItemsTheSame(
        oldItem: ResumeItem,
        newItem: ResumeItem,
    ): Boolean =
        (oldItem.id == newItem.id)

    override fun areContentsTheSame(
        oldItem: ResumeItem,
        newItem: ResumeItem,
    ): Boolean =
        (oldItem == newItem)
}