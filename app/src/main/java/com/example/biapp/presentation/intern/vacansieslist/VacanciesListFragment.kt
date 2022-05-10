package com.example.biapp.presentation.intern.vacansieslist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_filter, menu)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        super.onCreateOptionsMenu(menu, inflater)

        searchView?.setOnClickListener {
            Toast.makeText(requireContext(), "${1}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuFilter -> {
                findNavController().navigate(R.id.action_vacanciesListFragment_to_filterFragment)
                true
            }
            android.R.id.home -> {
                findNavController().navigateUp()
                true
            }
            R.id.menu_search -> {
                if (binding.searchView.visibility == View.VISIBLE)
                    binding.searchView.visibility = View.GONE
                else binding.searchView.visibility = View.VISIBLE
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentVacanciesListBinding.bind(view)

        setupClickListeners()

        setUpSampleList()

        viewModel.vacanciesLiveData.observe(viewLifecycleOwner, ::handleVacancyList)
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        viewModel.searchDatabase(searchQuery)

    }

    private fun setupClickListeners() {

        binding.swipeContainer.setOnRefreshListener {
            viewModel.getSampleList()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    searchDatabase(query)
                }
                return true
            }
        })

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