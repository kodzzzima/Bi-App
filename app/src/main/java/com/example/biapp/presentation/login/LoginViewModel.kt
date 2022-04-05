package com.example.biapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biapp.data.AppRepository
import com.example.biapp.data.models.UserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val appRepository: AppRepository,
) : ViewModel(), CoroutineScope {

    private val _userLiveData: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val userLiveData: LiveData<Boolean>
        get() = _userLiveData

    fun getUser(login: String, password: String) {
        launch {
            val user = appRepository.getUser(login = login)
            when {
                user.login == "-1" -> {
                    createUser(login, password)
                    _userLiveData.postValue(true)
                }
                user.password != password -> {
                    _userLiveData.postValue(false)
                }
                else -> {
                    _userLiveData.postValue(true)
                }
            }
        }
    }

    private fun createUser(login: String, password: String) {
        launch {
            appRepository.insertUser(
                UserItem(
                    login = login,
                    password = password,
                )
            )
        }
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext
}