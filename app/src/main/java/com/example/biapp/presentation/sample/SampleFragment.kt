package com.example.biapp.presentation.sample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biapp.R
import com.example.biapp.data.local.sample.SampleItem
import com.example.biapp.databinding.FragmentSampleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleFragment : Fragment(R.layout.fragment_sample) {

    private val viewModel: SampleViewModel by viewModels()

    private lateinit var binding: FragmentSampleBinding

    private val sampleAdapter by lazy {
        SampleAdapter(onItemClick = ::onItemClicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSampleBinding.bind(view)

        setupClickListeners()

        setUpSampleList()

        viewModel.sampleLiveData.observe(viewLifecycleOwner, ::handleSampleList)
    }

    private fun setupClickListeners() {

    }

    private fun setUpSampleList() {
        binding.sampleRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = sampleAdapter
        }
    }

    private fun handleSampleList(list: List<SampleItem>) {
        sampleAdapter.submitList(list)
    }

    private fun onItemClicked(sampleItem: SampleItem) {

    }

}