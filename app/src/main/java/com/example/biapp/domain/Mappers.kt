package com.example.biapp.domain

import com.example.biapp.data.local.sample.SampleEntity
import com.example.biapp.data.local.sample.SampleItem

fun SampleEntity.toItem() = SampleItem(
    id = id,
    title = title,
)