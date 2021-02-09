package com.example.todo.todonotes.Model.db.dao

import com.example.todo.todonotes.Model.db.entity.Item
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


    @Query("Update Items_table SET title=:uTitle ,content=:uContent WHERE id=:id")
    suspend fun updateItem(id:Int,uTitle:String,uContent:String)

}