package com.example.biapp.presentation.messageList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biapp.data.AppRepository
import com.example.biapp.presentation.employer.resumelist.ResumeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MessageListViewModel @Inject constructor(
    private val appRepository: AppRepository,
) : ViewModel(), CoroutineScope {

    private val _liveData: MutableLiveData<List<ResumeItem>> =
        MutableLiveData()
    val liveData: LiveData<List<ResumeItem>>
        get() = _liveData

    init {
        getSampleList()
    }

    fun getSampleList() {
        launch {
            _liveData.postValue(appRepository.getAllLocalResumes())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext
}
