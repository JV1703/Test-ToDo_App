package com.example.todoapp.ui.fragments.list.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.todoapp.data.models.ToDoData

class ToDoDiffUtil(
    private val oldList: List<ToDoData>,
    private val newList: List<ToDoData>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    /*if items have unique ids, this should check for item ids*/
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        Log.i("bnn_items", "${oldList[oldItemPosition].id == newList[newItemPosition].id}")
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    /*only called when areItemsTheSame is true*/
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        Log.i("bnn_content", "${oldList[oldItemPosition] == newList[newItemPosition]}")
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}