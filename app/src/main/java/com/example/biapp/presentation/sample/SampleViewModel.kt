package com.example.biapp.presentation.sample

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
class SampleViewModel @Inject constructor(
    private val appRepository: AppRepository,
) : ViewModel(), CoroutineScope {

    private val _sampleLiveData: MutableLiveData<List<SampleItem>> =
        MutableLiveData()
    val sampleLiveData: LiveData<List<SampleItem>>
        get() = _sampleLiveData

    init {
        getSampleList()
    }

    private fun getSampleList() {
        launch {
            _sampleLiveData.postValue(appRepository.getAllLocal())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext
}