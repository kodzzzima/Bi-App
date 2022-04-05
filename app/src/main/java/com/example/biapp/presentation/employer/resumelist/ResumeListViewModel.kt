package com.example.biapp.presentation.employer.resumelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biapp.data.AppRepository
import com.example.biapp.data.local.sample.SampleItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ResumeListViewModel @Inject constructor(
    private val appRepository: AppRepository,
) : ViewModel(), CoroutineScope {

    private val _resumeListLiveData: MutableLiveData<List<ResumeItem>> =
        MutableLiveData()
    val resumeListLiveData: LiveData<List<ResumeItem>>
        get() = _resumeListLiveData

    init {
        getSampleList()
    }

    fun getSampleList() {
        launch {
            _resumeListLiveData.postValue(appRepository.getAllLocalResumes())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext
}