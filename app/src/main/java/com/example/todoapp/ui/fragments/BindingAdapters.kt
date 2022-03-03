package com.example.todoapp.ui.fragments

import android.view.View
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.room.RoomDatabase
import com.example.todoapp.R
import com.example.todoapp.data.models.Priority
import com.example.todoapp.data.models.ToDoData
import com.example.todoapp.ui.fragments.list.ListFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton


/*If data binding is placed inside a class then the annotation of @JVMStatic is required*/
//class BindingAdapters {
//    companion object {
//        @BindingAdapter("navigateToAddFragment")
//        @JvmStatic
//        fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean) {
//            view.setOnClickListener {
//                if (navigate) {
//                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
//                }
//            }
//        }
//    }
//}


@BindingAdapter("navigateToAddFragment")
fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean) {
    view.setOnClickListener {
        if (navigate) {
            view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }
}

@BindingAdapter("emptyDatabase")
fun emptyDatabase(view: View, emptyDatabase: LiveData<Boolean>) {
    view.isVisible = emptyDatabase.value!!
}

@BindingAdapter("parsePriorityToInt")
fun parsePriorityToInt(view: Spinner, priority: Priority) {
    return when (priority) {
        Priority.HIGH -> view.setSelection(0)
        Priority.MEDIUM -> view.setSelection(1)
        Priority.LOW -> view.setSelection(2)
    }
}

@BindingAdapter("parsePriorityColor")
fun parsePriorityColor(view: CardView, priority: Priority) {
    when (priority) {
        Priority.HIGH -> view.setCardBackgroundColor(view.context.getColor(R.color.red))
        Priority.MEDIUM -> view.setCardBackgroundColor(view.context.getColor(R.color.yellow))
        Priority.LOW -> view.setCardBackgroundColor(view.context.getColor(R.color.green))
    }
}

@BindingAdapter("sendDataToUpdateFragment")
fun sendDataToUpdateFragment(view: ConstraintLayout, currentItem: ToDoData) {
    view.setOnClickListener{
        val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
        view.findNavController().navigate(action)
    }
}