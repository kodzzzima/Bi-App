package com.example.biapp.presentation.messageList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biapp.R
import com.example.biapp.databinding.FragmentMessageListBinding
import com.example.biapp.presentation.employer.resumelist.ResumeItem
import com.example.biapp.presentation.employer.resumelist.ResumeListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MessageListFragment : Fragment(R.layout.fragment_message_list) {

    private val viewModel: MessageListViewModel by viewModels()

    private lateinit var binding: FragmentMessageListBinding

    private val resumeListAdapter by lazy {
        ResumeListAdapter(onItemClick = ::onItemClicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMessageListBinding.bind(view)

        setupClickListeners()

        setUpSampleList()

        viewModel.liveData.observe(viewLifecycleOwner, ::handleResumeList)
    }

    private fun setupClickListeners() {

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