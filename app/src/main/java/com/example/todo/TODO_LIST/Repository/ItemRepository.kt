package com.example.todo.TODO_LIST.Repository

import androidx.lifecycle.LiveData
import com.example.todo.TODO_LIST.Model.Item
import com.example.todo.TODO_LIST.db.ItemDao

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