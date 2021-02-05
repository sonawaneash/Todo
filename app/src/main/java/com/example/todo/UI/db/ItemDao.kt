package com.example.todo.UI.db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM items_table")
    fun getAllItems(): LiveData<List<Item>>

}