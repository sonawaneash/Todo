package com.example.todo.todonotes.model.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Items_table")
data class Item (
    @ColumnInfo(name="title") var title: String,
    @ColumnInfo(name="content") var content: String,

)
{
    @PrimaryKey(autoGenerate = true) var id = 0
}
