package com.example.biapp.presentation.employer.vacancies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.biapp.databinding.ItemVacancyBinding
import javax.inject.Inject

class VacanciesAdapter @Inject constructor(
    private val onItemClick: (VacancyItem) -> Unit,
) : ListAdapter<VacancyItem, VacanciesViewHolder>(DetectionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanciesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemVacancyBinding.inflate(layoutInflater, parent, false)

        return VacanciesViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: VacanciesViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class VacanciesViewHolder(
    private val binding: ItemVacancyBinding,
    private val onItemClick: (VacancyItem) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(vacancyItem: VacancyItem) {
        with(binding) {
            title.text = vacancyItem.title
            ref.text = vacancyItem.ref
            company.text = vacancyItem.companyName
        }

        itemView.setOnClickListener { onItemClick(vacancyItem) }
    }
}

private class DetectionDiffCallback : DiffUtil.ItemCallback<VacancyItem>() {

    override fun areItemsTheSame(
        oldItem: VacancyItem,
        newItem: VacancyItem,
    ): Boolean =
        (oldItem.id == newItem.id)

    override fun areContentsTheSame(
        oldItem: VacancyItem,
        newItem: VacancyItem,
    ): Boolean =
        (oldItem == newItem)
}