package com.example.todo.UI

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.UI.UI.ListItem
import com.example.todo.UI.db.Item
import com.example.todo.UI.db.ItemDatabase
import com.example.todo.UI.db.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application : Application): AndroidViewModel(application) {

    val allItems: LiveData<List<Item>>
    val repository: ItemRepository

    init{
        val dao = ItemDatabase.getDatabase(application).getItemDao()
        repository = ItemRepository(dao)
        allItems = repository.allItems
    }

    //coroutine to delete item
    fun deleteItem(item: ListItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(item)
    }

    fun insertItem(item: ListItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }
}