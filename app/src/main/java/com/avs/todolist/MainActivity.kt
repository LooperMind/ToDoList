package com.avs.todolist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var toDoRecyclerView : RecyclerView
    private var adapter : ToDoAdapter? = ToDoAdapter(emptyList())
    private val toDoListViewModel : ListViewModel by lazy {
        ViewModelProvider(this)[ListViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toDoRecyclerView = findViewById(R.id.to_do_list)
        toDoRecyclerView.layoutManager = LinearLayoutManager(this)
        toDoRecyclerView.adapter = adapter
        toDoListViewModel.toDoListLiveData.observe(
            this
        ) { toDos ->
            toDos?.let { updateUI(toDos) }
        }
        findViewById<FloatingActionButton>(R.id.add_task_button)
            .setOnClickListener{
                startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }

    private fun updateUI(toDos : List<ToDoTask>){
        adapter = ToDoAdapter(toDos)
        toDoRecyclerView.adapter = adapter
    }

    private inner class ToDoHolder(view : View) : RecyclerView.ViewHolder(view) {
        private lateinit var toDo : ToDoTask
        private val titleViewText : TextView = itemView.findViewById(R.id.to_do_title)
        private val importanceTextView : TextView = itemView.findViewById(R.id.to_do_importance)

        fun bind(todo : ToDoTask){
            this.toDo = todo
            titleViewText.text = toDo.title
            importanceTextView.text = toDo.importance.toString()
        }
    }

    private inner class ToDoAdapter(var toDos: List<ToDoTask>) : RecyclerView.Adapter<ToDoHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoHolder {
            val view = layoutInflater.inflate(R.layout.to_do_list_item, parent, false)
            return  ToDoHolder(view)
        }

        override fun getItemCount(): Int {
            return toDos.size
        }

        override fun onBindViewHolder(holder: ToDoHolder, position: Int) {
            val toDo = toDos[position]
            holder.bind(toDo)
        }
    }
}