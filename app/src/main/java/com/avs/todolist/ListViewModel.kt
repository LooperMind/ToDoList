package com.avs.todolist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    private val toDoRepository = ToDoRepository.get()
    val toDoListLiveData = toDoRepository.getToDos()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun createNewToDo(toDoTask: ToDoTask) =
        coroutineScope.launch(Dispatchers.IO) {
        toDoRepository.createToDo(toDoTask)
    }
}