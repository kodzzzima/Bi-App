package com.example.biapp.presentation.settings

import android.content.SharedPreferences
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
import com.example.biapp.databinding.FragmentSettingsBinding
import com.example.biapp.presentation.sample.SampleAdapter
import com.example.biapp.presentation.sample.SampleViewModel
import com.example.biapp.utils.Authorized
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val viewModel: SettingsViewModel by viewModels()

    private lateinit var binding: FragmentSettingsBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSettingsBinding.bind(view)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.buttonLogOut.setOnClickListener {
            sharedPreferences.edit().putString("authorized_user", Authorized.NOT.name).apply()
            findNavController().navigate(R.id.action_settingsFragment_to_loginFragment)
        }
    }
}