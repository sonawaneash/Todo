package com.example.todo.TODO_LIST.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todo.todonotes.Model.db.entity.Item


class Note (
    var title: String = "",
    var content: String = ""

    ) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    //static fn, Note.convertItemToNote
    companion object {
        fun convertItemToNote(item: Item): Note {
            var note = Note()

            item?.let {
                note.id = it.id
                note.title = it.title
                note.content = it.content
            }
            return note
        }

        fun convertNoteToItem(note: Note): Item {
            var item = Item("", "")

            note?.let {
                item.id = it.id
                item.title = it.title
                item.content = it.content
            }
            return item

        }



    fun convertItemListToNoteList(itemList: ArrayList<Item>): ArrayList<Note> {
        val noteList = ArrayList<Note>()

        for (i in itemList) {
            val note = convertItemToNote(i)
            noteList.add(note)
        }
        return noteList
    }
}

}
