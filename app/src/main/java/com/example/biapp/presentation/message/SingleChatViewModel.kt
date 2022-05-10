package com.example.biapp.presentation.message

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class SingleChatViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : ViewModel(), CoroutineScope {

    private val userLogin = sharedPreferences.getString("user_id", "") ?: ""

    private val _liveData: MutableLiveData<String> =
        MutableLiveData(userLogin)
    val liveData: LiveData<String>
        get() = _liveData

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext
}