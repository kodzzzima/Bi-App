package com.example.biapp.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.biapp.R
import com.example.biapp.databinding.FragmentLoginBinding
import com.example.biapp.databinding.FragmentSampleBinding
import com.example.biapp.presentation.sample.SampleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        setupClickListeners()

    }

    private fun setupClickListeners() {

//        binding.btnEmployer.setOnClickListener {
//
//        }

        binding.btnIntern.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_myResumesFragment)
        }
    }
}