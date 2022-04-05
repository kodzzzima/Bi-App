package com.example.biapp.presentation.intern.vacansieslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biapp.R
import com.example.biapp.databinding.FragmentVacanciesListBinding
import com.example.biapp.presentation.employer.vacancies.VacancyItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VacanciesListFragment : Fragment(R.layout.fragment_vacancies_list) {

    private val viewModel: VacanciesListViewModel by viewModels()

    private lateinit var binding: FragmentVacanciesListBinding

    private val vacanciesAdapter by lazy {
        VacanciesListAdapter(onItemClick = ::onItemClicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentVacanciesListBinding.bind(view)

        setupClickListeners()

        setUpSampleList()

        viewModel.vacanciesLiveData.observe(viewLifecycleOwner, ::handleVacancyList)
    }

    private fun setupClickListeners() {

        binding.swipeContainer.setOnRefreshListener {
            viewModel.getSampleList()
        }
    }

    private fun setUpSampleList() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = vacanciesAdapter
        }
    }

    private fun handleVacancyList(list: List<VacancyItem>) {
        binding.swipeContainer.isRefreshing = false
        vacanciesAdapter.submitList(list)
        if (list.isEmpty()) binding.listIsEmpty.visibility = View.VISIBLE
        else binding.listIsEmpty.visibility = View.GONE
    }

    private fun onItemClicked(vacancyItem: VacancyItem) {

    }

}