package com.avs.todolist

import android.content.Context
import androidx.room.Room
import database.ToDoDatabase
import java.util.UUID

private const val DATABSAE_NAME = "to-do-list-database"
class ToDoRepository private constructor(context : Context) {
    private val database : ToDoDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            ToDoDatabase::class.java,
            DATABSAE_NAME
        ).build()

    private val dataBaseDao = database.toDODao()

    fun getToDos() : List<ToDoTask> = dataBaseDao.getToDos()

    fun getToDO(id : UUID) : ToDoTask? = dataBaseDao.getToDo(id)

    companion object {
        private var INSTANCE: ToDoRepository? = null
        fun initialize(context : Context){
            if (INSTANCE == null){
                INSTANCE = ToDoRepository(context)
            }
        }

        fun get() : ToDoRepository {
            return INSTANCE ?: throw IllegalStateException("No repo")
        }
    }
}