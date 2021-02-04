package com.example.todo.UI.db

import androidx.lifecycle.LiveData
import com.example.todo.UI.UI.ListItem

class ItemRepository(private val itemDao: ItemDao) {

    val allItems: LiveData<List<Item>> = itemDao.getAllItems()


    suspend fun insert(item: ListItem){
        itemDao.insert(item)
    }

    suspend fun delete(item: ListItem){
        itemDao.delete(item)
    }

}