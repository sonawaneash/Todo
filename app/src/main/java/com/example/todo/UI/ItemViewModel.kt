package com.example.todo.UI

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ItemViewModel(application : Application): AndroidViewModel(application) {

    val allItems: LiveData<List<Item>>

    init{
        val dao = ItemDatabase.getDatabase(application).getItemDao()
        val repository = ItemRepository(dao)
        allItems = repository.allItems
    }

}