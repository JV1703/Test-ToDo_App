package com.example.todoapp.ui.fragments

import android.app.Application
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.R
import com.example.todoapp.data.models.Priority
import com.example.todoapp.data.models.ToDoData

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val _emptyDatabase = MutableLiveData<Boolean>(false)
    val emptyDatabase: LiveData<Boolean> get() = _emptyDatabase

    private val _navigate = MutableLiveData<Boolean>(false)
    val navigate: LiveData<Boolean> get() = _navigate

    fun checkIfDatabaseEmpty(toDoData: List<ToDoData>){
        _emptyDatabase.value = toDoData.isEmpty()
    }

    fun setNavigate(boolean: Boolean){
        _navigate.value = boolean
    }

    /*try to switch this to binding adapter*/
    /*this code is put in shared view model is because this will be use again in update fragment*/
    val listener: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when (position) {
                0 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.red
                        )
                    )
                }

                1 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.yellow
                        )
                    )
                }

                2 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.green
                        )
                    )
                }
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}

    }

    fun verifyDataFromUser(title: String, description: String): Boolean {
        /*TextUtils.isEmpty is the same as String.isEmpty. TextUtils however, checks for null therefore avoiding NPE*/
        return if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            false
        } else !(title.isEmpty() || description.isEmpty())
    }

    fun parsePriority(priority: String): Priority {
        return when (priority) {
            "High Priority" -> Priority.HIGH
            "Medium Priority" -> Priority.MEDIUM
            "Low Priority" -> Priority.LOW
            else -> Priority.LOW
        }
    }

//    fun parsePriorityToInt(priority: Priority): Int {
//        return when (priority) {
//            Priority.HIGH -> 0
//            Priority.MEDIUM -> 1
//            Priority.LOW -> 2
//        }
//    }

    override fun onCleared() {
        super.onCleared()
        Log.i("banana", "shared view model is destroyed")
    }
}