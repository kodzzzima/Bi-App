package com.example.biapp.presentation.login

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.biapp.R
import com.example.biapp.databinding.DialogLoginInternBinding
import com.example.biapp.utils.Authorized
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DialogLoginIntern() : BottomSheetDialogFragment() {
    private lateinit var binding: DialogLoginInternBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DialogLoginInternBinding.bind(inflater.inflate(R.layout.dialog_login_intern, container))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGo.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_myResumesFragment)
            sharedPreferences.edit().putString("authorized_user", Authorized.Intern.name).apply()
            dismiss()

            setNavView()
        }


    }

    private fun setNavView() {

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