package com.example.biapp.presentation.messageList

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biapp.data.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MessageListViewModel @Inject constructor(
    private val appRepository: AppRepository,
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
