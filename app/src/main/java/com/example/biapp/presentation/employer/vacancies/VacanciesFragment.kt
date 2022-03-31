package com.example.biapp.presentation.employer.vacancies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biapp.R
import com.example.biapp.databinding.FragmentVacanciesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VacanciesFragment : Fragment(R.layout.fragment_vacancies) {

    private val viewModel: VacanciesViewModel by viewModels()

    private lateinit var binding: FragmentVacanciesBinding

    private val vacanciesAdapter by lazy {
        VacanciesAdapter(onItemClick = ::onItemClicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentVacanciesBinding.bind(view)

        setupClickListeners()

        setUpSampleList()

        viewModel.vacanciesLiveData.observe(viewLifecycleOwner, ::handleVacancyList)
    }

    private fun setupClickListeners() {

        binding.btnCreate.setOnClickListener {
            findNavController().navigate(R.id.action_vacanciesFragment3_to_createVacancyFragment)
        }

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