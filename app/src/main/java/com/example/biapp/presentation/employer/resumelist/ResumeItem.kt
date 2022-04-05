package com.example.biapp.presentation.employer.resumelist

data class ResumeItem(
    val id: Int,
    val userId: String,
    val title: String,
    val name: String,
    val skills: String,
    val contact: String,
)