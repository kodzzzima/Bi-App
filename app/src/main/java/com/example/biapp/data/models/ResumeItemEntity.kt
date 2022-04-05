package com.example.biapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.biapp.data.models.ResumeItemEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class ResumeItemEntity(
    @PrimaryKey
    @ColumnInfo(name = ID)
    var id: Int = 0,
    @ColumnInfo(name = "user_id")
    val userId: String,
    var title: String,
    var name: String,
    var skills: String,
    var contact: String,
) {

    companion object {

        const val TABLE_NAME = "resumes"

        const val ID = "id"
    }
}
