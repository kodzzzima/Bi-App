package com.example.biapp.presentation.employer.vacancies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biapp.R
import com.example.biapp.databinding.FragmentMyVacanciesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyVacanciesFragment : Fragment(R.layout.fragment_my_vacancies) {

    private val viewModelMy: MyVacanciesViewModel by viewModels()

    private lateinit var binding: FragmentMyVacanciesBinding

    private val vacanciesAdapter by lazy {
        MyVacanciesAdapter(onItemClick = ::onItemClicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMyVacanciesBinding.bind(view)

        setupClickListeners()

        setUpSampleList()

        viewModelMy.vacanciesLiveData.observe(viewLifecycleOwner, ::handleVacancyList)
    }

    private fun setupClickListeners() {

        binding.btnCreate.setOnClickListener {
            findNavController().navigate(R.id.action_vacanciesFragment3_to_createVacancyFragment)
        }

        binding.swipeContainer.setOnRefreshListener {
            viewModelMy.getSampleList()
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