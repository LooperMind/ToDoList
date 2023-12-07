package com.avs.todolist

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class ToDoTask(@PrimaryKey val id : UUID = UUID.randomUUID(),
    var title : String = "",
    var importance : Int = 0)