package com.example.biapp.presentation.intern.createresume

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biapp.data.AppRepository
import com.example.biapp.presentation.employer.resumelist.ResumeItem
import com.example.biapp.presentation.employer.vacancies.VacancyItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class CreateResumeViewModel @Inject constructor(
    private val appRepository: AppRepository,
) : ViewModel(), CoroutineScope {

    fun insertData(resumeItem: ResumeItem) {
        launch { appRepository.insertLocalResume(resumeItem) }
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext
}