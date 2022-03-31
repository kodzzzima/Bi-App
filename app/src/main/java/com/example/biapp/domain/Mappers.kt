package com.example.biapp.domain

import com.example.biapp.data.local.sample.SampleEntity
import com.example.biapp.data.local.sample.SampleItem
import com.example.biapp.data.models.VacancyItemEntity
import com.example.biapp.presentation.employer.vacancies.VacancyItem

fun SampleEntity.toItem() = SampleItem(
    id = id,
    title = title,
)

fun VacancyItem.toEntity() = VacancyItemEntity(
    id = id,
    title = title,
    ref = ref,
    companyName = companyName
)

fun VacancyItemEntity.toItem() = VacancyItem(
    id = id,
    title = title,
    ref = ref,
    companyName = companyName
)