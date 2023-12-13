package com.avs.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AddTaskActivity : AppCompatActivity() {
    private lateinit var createTaskButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        createTaskButton = findViewById<Button>(R.id.create_task)
        createTaskButton.setOnClickListener{
                startActivity(Intent(this, MainActivity::class.java))
            }
    }
}