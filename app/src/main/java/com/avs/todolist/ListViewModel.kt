package com.avs.todolist

import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {
    private val toDoRepository = ToDoRepository.get()
    val toDoListLiveData = toDoRepository.getToDos()
}