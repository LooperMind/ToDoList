package com.avs.todolist

import android.app.Application

class ToDoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ToDoRepository.initialize(this)
    }
}