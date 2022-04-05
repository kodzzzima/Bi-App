package com.example.biapp.presentation.intern.createresume

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
import com.example.biapp.databinding.FragmentCreateResumeBinding
import com.example.biapp.databinding.FragmentCreateVacancyBinding
import com.example.biapp.databinding.FragmentSampleBinding
import com.example.biapp.presentation.employer.resumelist.ResumeItem
import com.example.biapp.presentation.employer.vacancies.VacancyItem
import com.example.biapp.presentation.sample.SampleAdapter
import com.example.biapp.presentation.sample.SampleViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random


@AndroidEntryPoint
class CreateResumeFragment : Fragment(R.layout.fragment_create_resume) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var userLogin :String

    private val viewModel: CreateResumeViewModel by viewModels()

    private lateinit var binding: FragmentCreateResumeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCreateResumeBinding.bind(view)

        userLogin = sharedPreferences.getString("user_id", "") ?: ""
        setupClickListeners()

    }

    private fun setupClickListeners() {
        binding.btnOk.setOnClickListener {
            if (binding.inputTitle.text.toString().isEmpty()
                && binding.inputRef.text.toString().isEmpty()
                && binding.inputContact.text.toString().isEmpty()
            ) {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                val resumeItem = ResumeItem(
                    id = (0..10000).random(),
                    title = binding.inputTitle.text.toString(),
                    skills = binding.inputRef.text.toString(),
                    name = binding.inputTitle.text.toString(),
                    contact = binding.inputContact.text.toString(),
                    userId = userLogin
                )
                viewModel.insertData(resumeItem)
                findNavController().navigateUp()
            }
        }
    }

}