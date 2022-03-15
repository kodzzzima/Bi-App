package com.example.biapp.data.local.sample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.biapp.data.local.sample.SampleEntity.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class SampleEntity(
    @PrimaryKey
    @ColumnInfo(name = ID)
    var id: Int = 0,

    @ColumnInfo(name = TITLE)
    var title: String,
) {

    companion object {

        const val TABLE_NAME = "sample"

        const val ID = "id"
        const val TITLE = "title"
    }
}
