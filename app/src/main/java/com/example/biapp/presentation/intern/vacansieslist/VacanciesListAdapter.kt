package com.example.biapp.presentation.intern.vacansieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.biapp.databinding.ItemVacancyBinding
import com.example.biapp.presentation.employer.vacancies.VacancyItem
import javax.inject.Inject

class VacanciesListAdapter @Inject constructor(
    private val onItemClick: (VacancyItem) -> Unit,
) : ListAdapter<VacancyItem, VacanciesListViewHolder>(DetectionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanciesListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemVacancyBinding.inflate(layoutInflater, parent, false)

        return VacanciesListViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holderMy: VacanciesListViewHolder, position: Int) =
        holderMy.bind(getItem(position))
}

class VacanciesListViewHolder(
    private val binding: ItemVacancyBinding,
    private val onItemClick: (VacancyItem) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(vacancyItem: VacancyItem) {
        with(binding) {
            message.isVisible = vacancyItem.canShowMessage
            title.text = vacancyItem.title
            ref.text = vacancyItem.ref
            company.text = vacancyItem.companyName
            salary.text = vacancyItem.salary
            location.text = vacancyItem.location
        }

//        itemView.setOnClickListener { onItemClick(vacancyItem) }
        binding.message.setOnClickListener {
            onItemClick(vacancyItem)
        }
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