package com.example.todo.UI.db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("SELECT * FROM items_table")
    fun getAllItems():LiveData<List<Item>>

}