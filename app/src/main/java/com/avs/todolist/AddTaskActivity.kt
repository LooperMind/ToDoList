package com.avs.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.util.UUID

class AddTaskActivity : AppCompatActivity() {
    private lateinit var createTaskButton : Button
    private val toDoListViewModel : ListViewModel by lazy {
        ViewModelProvider(this)[ListViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        createTaskButton = findViewById(R.id.create_task)
        createTaskButton.setOnClickListener{
                createToDo()
                startActivity(Intent(this, MainActivity::class.java))
            }
    }

    private fun createToDo(){
        val title = findViewById<EditText>(R.id.task_title).text.toString()
        val importance = when (findViewById<RadioGroup>(R.id.radioGroup).checkedRadioButtonId) {
                R.id.importance_low -> 0
                R.id.importance_normal -> 1
                R.id.importance_high -> 2
                else -> 0
            }
        toDoListViewModel.createNewToDo(ToDoTask(UUID.randomUUID(), title, importance))
        }
    }