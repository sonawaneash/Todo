package com.example.todo.TODO_LIST.View_Model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.TODO_LIST.Model.Item
import com.example.todo.TODO_LIST.db.ItemDatabase
import com.example.todo.TODO_LIST.Repository.ItemRepository
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
    fun deleteItem(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(item) // viewModel will call delete fn from repository which is suspend fn
    }

    fun insertItem(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }

    fun setUpdate(item: Item){

    }

    fun updateItem(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateItem(item)
    }
}