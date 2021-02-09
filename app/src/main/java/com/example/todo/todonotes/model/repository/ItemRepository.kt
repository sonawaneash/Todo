package com.example.todo.todonotes.model.repository

import androidx.lifecycle.LiveData
import com.example.todo.todonotes.model.db.entity.Item
import com.example.todo.todonotes.model.db.dao.ItemDao

class ItemRepository(private val itemDao: ItemDao) {

    val allItems: LiveData<List<Item>> = itemDao.getAllItems()


    suspend fun insert(item: Item){
        itemDao.insert(item)
    }

    suspend fun delete(item: Item){
        itemDao.delete(item)
    }

    suspend fun updateItem(id:Int,uTitle:String,uContent:String){
        itemDao.updateItem(id,uTitle,uContent)
    }

}