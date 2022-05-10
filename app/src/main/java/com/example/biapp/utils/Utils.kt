package com.example.biapp.utils

import java.text.SimpleDateFormat
import java.util.*

enum class Authorized {
    NOT,
    Intern,
    Employer
}

fun String.asTime(): String {
    val time = Date(this.toLong())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(time)
}