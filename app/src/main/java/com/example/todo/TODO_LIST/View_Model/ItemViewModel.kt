package com.example.todo.TODO_LIST.View_Model

import android.app.Application
import android.icu.text.CaseMap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.TODO_LIST.Model.Item
import com.example.todo.TODO_LIST.Model.Note
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
    fun deleteItem(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        var item = Note.convertNoteToItem(note)
        repository.delete(item) // viewModel will call delete fn from repository which is suspend fn
    }

    fun insertItem(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        var item = Note.convertNoteToItem(note)
        repository.insert(item)
    }

    fun updateItem(id:Int,uTitle:String,uContent:String) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateItem(id,uTitle,uContent)
    }


}