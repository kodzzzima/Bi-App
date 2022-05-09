package com.example.biapp.presentation.login

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.biapp.R
import com.example.biapp.databinding.DialogLoginEmployerBinding
import com.example.biapp.databinding.DialogLoginInternBinding
import com.example.biapp.utils.Authorized
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DialogLoginEmployer() : BottomSheetDialogFragment() {
    private lateinit var binding: DialogLoginEmployerBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DialogLoginEmployerBinding.bind(inflater.inflate(R.layout.dialog_login_employer, container))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGo.setOnClickListener {
            if (binding.editLogin.text.toString().isEmpty() &&
                binding.editPassword.text.toString().isEmpty()
            ) {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.getUser(
                    binding.editLogin.text.toString(),
                    binding.editPassword.text.toString(),
                )
            }
        }
            viewModel.userLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    findNavController().navigate(R.id.action_loginFragment_to_vacanciesFragment3)
                    sharedPreferences.edit().putString("authorized_user", Authorized.Intern.name)
                        .apply()
                    sharedPreferences.edit().putString("user_id", binding.editLogin.text.toString())
                        .apply()
                    dismiss()

                    setNavView()
                } else {
//                    Toast.makeText(requireContext(), "Неправильный пароль", Toast.LENGTH_SHORT).show()
                }
            }


    }
    private fun setNavView() {

        (activity as AppCompatActivity).let {


            val navHostFragment =
                it.supportFragmentManager.findFragmentById(R.id.container_fragment) as NavHostFragment
            val navController = navHostFragment.navController

            val employerAppBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.resumeListFragment,
                    R.id.vacanciesFragment3,
                    R.id.settingsFragment,
                )
            )

            it.setupActionBarWithNavController(navController, employerAppBarConfiguration)

            val bottomNav = it.findViewById<BottomNavigationView>(R.id.bottom_nav)
            bottomNav.menu.clear()
            bottomNav.inflateMenu(R.menu.bottom_menu_employer)
            bottomNav.setupWithNavController(navController)
        }
    }
}