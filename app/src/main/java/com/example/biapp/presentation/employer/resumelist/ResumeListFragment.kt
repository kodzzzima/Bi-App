package com.example.biapp.presentation.employer.resumelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biapp.R
import com.example.biapp.databinding.FragmentResumeListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResumeListFragment : Fragment(R.layout.fragment_resume_list) {

    private val viewModel: ResumeListViewModel by viewModels()

    private lateinit var binding: FragmentResumeListBinding

    private val resumeListAdapter by lazy {
        ResumeListAdapter(onItemClick = ::onItemClicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentResumeListBinding.bind(view)

        setupClickListeners()

        setUpSampleList()

        viewModel.resumeListLiveData.observe(viewLifecycleOwner, ::handleResumeList)
    }

    private fun setupClickListeners() {

//        binding.btnCreate.setOnClickListener {
//            findNavController().navigate(R.id.action_vacanciesFragment3_to_createVacancyFragment)
//        }

        binding.swipeContainer.setOnRefreshListener {
            viewModel.getSampleList()
        }
    }

    private fun setUpSampleList() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = resumeListAdapter
        }
    }

    private fun handleResumeList(list: List<ResumeItem>) {

        binding.swipeContainer.isRefreshing = false
        resumeListAdapter.submitList(list)
        if (list.isEmpty()) binding.listIsEmpty.visibility = View.VISIBLE
        else binding.listIsEmpty.visibility = View.GONE
    }

    private fun onItemClicked(resumeItem: ResumeItem) {}

}

