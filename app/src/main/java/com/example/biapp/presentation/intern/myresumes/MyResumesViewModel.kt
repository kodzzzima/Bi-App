package com.example.biapp.presentation.intern.myresumes

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
class MyResumesViewModel @Inject constructor(
    private val appRepository: AppRepository,
) : ViewModel(), CoroutineScope {

    private val _myResumesLiveData: MutableLiveData<List<SampleItem>> =
        MutableLiveData()
    val myResumesLiveData: LiveData<List<SampleItem>>
        get() = _myResumesLiveData

    init {
        getMyResumesList()
    }

    private fun getMyResumesList() {
        launch {
            _myResumesLiveData.postValue(
                listOf(
                    SampleItem(
                        id = 0,
                        title = "Андроид разработчик"),
                    SampleItem(
                        id = 1,
                        title = "Java разработчик"),
                )
            )
        }
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext
}