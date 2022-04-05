package com.example.biapp.presentation.intern.myresumes

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biapp.data.AppRepository
import com.example.biapp.data.local.sample.SampleItem
import com.example.biapp.presentation.employer.resumelist.ResumeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MyResumesViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel(), CoroutineScope {

    private val userLogin = sharedPreferences.getString("user_id", "") ?: ""
    private val _myResumesLiveData: MutableLiveData<List<ResumeItem>> =
        MutableLiveData()
    val myResumesLiveData: LiveData<List<ResumeItem>>
        get() = _myResumesLiveData

    init {
        getMyResumesList()
    }

    fun getMyResumesList() {
        launch {
            _myResumesLiveData.postValue(appRepository.getAllLocalResumesByCreatorId(userLogin)
            )
        }
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext
}