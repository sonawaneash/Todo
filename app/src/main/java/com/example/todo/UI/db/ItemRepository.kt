package com.example.todo.UI.db

import androidx.lifecycle.LiveData

class ItemRepository(private val itemDao: ItemDao) {

    val allItems: LiveData<List<Item>> = itemDao.getAllItems()


    suspend fun insert(item: Item){
        itemDao.insert(item)
    }

    suspend fun delete(item: Item){
        itemDao.delete(item)
    }

}