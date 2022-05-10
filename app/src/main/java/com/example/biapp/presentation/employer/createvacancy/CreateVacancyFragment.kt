package com.example.biapp.presentation.employer.createvacancy

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.biapp.R
import com.example.biapp.databinding.FragmentCreateVacancyBinding
import com.example.biapp.presentation.employer.vacancies.VacancyItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CreateVacancyFragment : Fragment(R.layout.fragment_create_vacancy) {

    private val viewModel: CreateVacancyViewModel by viewModels()

    private lateinit var binding: FragmentCreateVacancyBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var userLogin: String

    private var canMessage: Boolean = false

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
                && binding.inputSalary.text.toString().isEmpty()
                && binding.inputLocation.text.toString().isEmpty()
                && binding.inputSchedule.text.toString().isEmpty()
            ) {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                val vacancyItem = VacancyItem(
                    id = (0..10000).random(),
                    title = binding.inputName.text.toString(),
                    ref = binding.inputRef.text.toString(),
                    companyName = binding.inputCompany.text.toString(),
                    userId = userLogin,
                    salary = binding.inputSalary.text.toString(),
                    location = binding.inputLocation.text.toString(),
                    schedule = binding.inputSchedule.text.toString(),
                    canShowMessage = canMessage
                )
                viewModel.insertData(vacancyItem)
                findNavController().navigateUp()
            }
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            view?.findViewById<RadioButton>(checkedId)?.apply {
                canMessage = checkedId != binding.radioNo.id
            }
        }
    }

}