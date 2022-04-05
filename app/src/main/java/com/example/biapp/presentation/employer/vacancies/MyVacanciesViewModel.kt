package com.example.biapp.presentation.employer.vacancies

import android.content.SharedPreferences
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
class MyVacanciesViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val sharedPreferences: SharedPreferences,
) : ViewModel(), CoroutineScope {

    private val userLogin = sharedPreferences.getString("user_id", "") ?: ""

    private val _vacanciesLiveData: MutableLiveData<List<VacancyItem>> =
        MutableLiveData()
    val vacanciesLiveData: LiveData<List<VacancyItem>>
        get() = _vacanciesLiveData

    init {
        getSampleList()
    }

    fun getSampleList() {
        launch {
            _vacanciesLiveData.postValue(appRepository.getAllLocalVacanciesByCreatorId(userLogin))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext
}