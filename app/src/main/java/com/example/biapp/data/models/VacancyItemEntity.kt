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
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = TITLE)
    var title: String,
    @ColumnInfo(name = "ref")
    val ref: String,
    @ColumnInfo(name = "company_name")
    val companyName: String,
    @ColumnInfo(name = "salary")
    val salary: String,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "schedule")
    val schedule: String,
    @ColumnInfo(name = "can_show_message")
    val canShowMessage: Boolean,
) {

    companion object {

        const val TABLE_NAME = "vacancies"

        const val ID = "id"
        const val TITLE = "title"
    }
}
