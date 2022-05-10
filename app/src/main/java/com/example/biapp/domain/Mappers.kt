package com.example.biapp.domain

import com.example.biapp.data.local.sample.SampleEntity
import com.example.biapp.data.local.sample.SampleItem
import com.example.biapp.data.models.ResumeItemEntity
import com.example.biapp.data.models.UserEntity
import com.example.biapp.data.models.UserItem
import com.example.biapp.data.models.VacancyItemEntity
import com.example.biapp.presentation.employer.resumelist.ResumeItem
import com.example.biapp.presentation.employer.vacancies.VacancyItem

fun SampleEntity.toItem() = SampleItem(
    id = id,
    title = title,
)

fun VacancyItem.toEntity() = VacancyItemEntity(
    id = id,
    userId = userId,
    title = title,
    ref = ref,
    companyName = companyName,
    salary = salary,
    location = location,
    schedule = schedule,
    canShowMessage = canShowMessage
)

fun VacancyItemEntity.toItem() = VacancyItem(
    id = id,
    userId = userId,
    title = title,
    ref = ref,
    companyName = companyName,
    salary = salary,
    location = location,
    schedule = schedule,
    canShowMessage = canShowMessage
)

fun ResumeItem.toEntity() = ResumeItemEntity(
    id = id,
    userId = userId,
    title = title,
    skills = skills,
    name = name,
    contact = contact
)

fun ResumeItemEntity.toItem() = ResumeItem(
    id = id,
    userId = userId,
    title = title,
    skills = skills,
    name = name,
    contact = contact
)

fun UserEntity.toItem() = UserItem(
    id = id,
    login = login,
    password = password,
)

fun UserItem.toEntity() = UserEntity(
    login = login,
    password = password,
)