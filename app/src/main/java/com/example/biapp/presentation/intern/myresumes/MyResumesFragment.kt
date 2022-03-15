package com.example.biapp.presentation.intern.myresumes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biapp.R
import com.example.biapp.data.local.sample.SampleItem
import com.example.biapp.databinding.FragmentMyResumesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyResumesFragment : Fragment(R.layout.fragment_my_resumes) {

    private val viewModel: MyResumesViewModel by viewModels()

    private lateinit var binding: FragmentMyResumesBinding

    private val myResumesAdapter by lazy {
        MyResumesAdapter(onItemClick = ::onItemClicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMyResumesBinding.bind(view)

        setupClickListeners()

        setUpSampleList()

        viewModel.myResumesLiveData.observe(viewLifecycleOwner, ::handleMyResumesList)
    }

    private fun setupClickListeners() {
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }

    private fun setUpSampleList() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myResumesAdapter
        }
    }

    private fun handleMyResumesList(list: List<SampleItem>) {
        myResumesAdapter.submitList(list)
    }

    private fun onItemClicked(sampleItem: SampleItem) {

    }

}