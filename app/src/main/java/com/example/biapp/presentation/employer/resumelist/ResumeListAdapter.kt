package com.example.biapp.presentation.employer.resumelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.biapp.databinding.ItemResumeListBinding
import com.example.biapp.databinding.ItemVacancyBinding
import javax.inject.Inject

class ResumeListAdapter @Inject constructor(
    private val onItemClick: (ResumeItem) -> Unit,
) : ListAdapter<ResumeItem, ResumeListViewHolder>(DetectionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResumeListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemResumeListBinding.inflate(layoutInflater, parent, false)

        return ResumeListViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ResumeListViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class ResumeListViewHolder(
    private val binding: ItemResumeListBinding,
    private val onItemClick: (ResumeItem) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(resumeItem: ResumeItem) {
        with(binding) {
            title.text = resumeItem.title
            ref.text = resumeItem.skills
            company.text = resumeItem.contact
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