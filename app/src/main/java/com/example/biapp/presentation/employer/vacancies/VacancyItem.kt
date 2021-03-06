package com.example.biapp.presentation.employer.vacancies

data class VacancyItem(
    val id: Int,
    val title: String,
    val userId: String,
    val ref: String,
    val companyName: String,
    val salary: String,
    val location: String,
    val schedule: String,
    val canShowMessage: Boolean,
)