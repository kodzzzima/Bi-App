package com.example.biapp.presentation.employer.createvacancy

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biapp.R
import com.example.biapp.data.local.sample.SampleItem
import com.example.biapp.databinding.FragmentCreateVacancyBinding
import com.example.biapp.databinding.FragmentSampleBinding
import com.example.biapp.presentation.employer.vacancies.VacancyItem
import com.example.biapp.presentation.sample.SampleAdapter
import com.example.biapp.presentation.sample.SampleViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random


@AndroidEntryPoint
class CreateVacancyFragment : Fragment(R.layout.fragment_create_vacancy) {

    private val viewModel: CreateVacancyViewModel by viewModels()

    private lateinit var binding: FragmentCreateVacancyBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var userLogin :String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCreateVacancyBinding.bind(view)

        userLogin = sharedPreferences.getString("user_id", "") ?: ""

        setupClickListeners()


    }

    private fun setupClickListeners() {
        binding.btnOk.setOnClickListener {
            if (binding.inputName.text.toString().isEmpty()
                && binding.inputCompany.text.toString().isEmpty()
                && binding.inputRef.text.toString().isEmpty()
            ) {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                val vacancyItem = VacancyItem(
                    id = (0..10000).random(),
                    title = binding.inputName.text.toString(),
                    ref = binding.inputRef.text.toString(),
                    companyName = binding.inputCompany.text.toString(),
                    userId = userLogin
                )
                viewModel.insertData(vacancyItem)
                findNavController().navigateUp()
            }
        }
    }

}