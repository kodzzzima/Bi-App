package com.example.biapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.biapp.data.models.VacancyItemEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class VacancyItemEntity(
    @PrimaryKey
    @ColumnInfo(name = ID)
    var id: Int = 0,
    @ColumnInfo(name = TITLE)
    var title: String,
    @ColumnInfo(name = "ref")
    val ref: String,
    @ColumnInfo(name = "company_name")
    val companyName: String,
) {

    companion object {

        const val TABLE_NAME = "vacancies"

        const val ID = "id"
        const val TITLE = "title"
    }
}
