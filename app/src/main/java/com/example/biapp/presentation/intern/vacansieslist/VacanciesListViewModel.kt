package com.example.biapp.presentation.intern.vacansieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biapp.data.AppRepository
import com.example.biapp.presentation.employer.vacancies.VacancyItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class VacanciesListViewModel @Inject constructor(
    private val appRepository: AppRepository,
) : ViewModel(), CoroutineScope {

    private val _vacanciesLiveData: MutableLiveData<List<VacancyItem>> =
        MutableLiveData()
    val vacanciesLiveData: LiveData<List<VacancyItem>>
        get() = _vacanciesLiveData

    init {
        getSampleList()
    }

    fun getSampleList() {
        launch {
            _vacanciesLiveData.postValue(appRepository.getAllLocalVacancies())
        }
    }

    fun searchDatabase(searchQuery: String) {
        launch {
            _vacanciesLiveData.postValue(appRepository.getVacanciesBySearch(searchQuery))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext
}