package com.example.biapp.presentation.login

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.biapp.R
import com.example.biapp.databinding.FragmentLoginBinding
import com.example.biapp.utils.Authorized
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        val authorizedUser = sharedPreferences.getString("authorized_user", "NOT")

        when (authorizedUser){
            Authorized.Employer.name -> setNavViewEmployer()
            Authorized.Intern.name -> setNavViewIntern()
            else -> Unit
        }
        setupClickListeners()

    }

    private fun setupClickListeners() {

//        binding.btnEmployer.setOnClickListener {
//
//        }

        binding.btnIntern.setOnClickListener {
            showDialogIntern()
        }
        binding.btnEmployer.setOnClickListener {
            showDialogEmployer()
        }

    }

    private fun showDialogIntern() {
        val dialog = DialogLoginIntern()
        dialog.show(childFragmentManager, DialogLoginIntern::class.java.simpleName)
    }

    private fun showDialogEmployer() {
        val dialog = DialogLoginEmployer()
        dialog.show(childFragmentManager, DialogLoginEmployer::class.java.simpleName)
    }

    private fun setNavViewEmployer() {
        findNavController().navigate(R.id.action_loginFragment_to_vacanciesFragment3)
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

    private fun setNavViewIntern() {
        findNavController().navigate(R.id.action_loginFragment_to_myResumesFragment)
        (activity as AppCompatActivity).let {


            val navHostFragment =
                it.supportFragmentManager.findFragmentById(R.id.container_fragment) as NavHostFragment
            val navController = navHostFragment.navController

            val internAppBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.vacanciesListFragment,
                    R.id.myResumesFragment,
                    R.id.settingsFragment,
                )
            )

            it.setupActionBarWithNavController(navController, internAppBarConfiguration)

            val bottomNav = it.findViewById<BottomNavigationView>(R.id.bottom_nav)
            bottomNav.menu.clear()
            bottomNav.inflateMenu(R.menu.bottom_menu_intern)
            bottomNav.setupWithNavController(navController)
        }
    }


}