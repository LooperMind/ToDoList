package com.avs.todolist

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
    }

    private fun updateUI(toDos : List<ToDoTask>){
        adapter = ToDoAdapter(toDos)
        toDoRecyclerView.adapter = adapter
    }

    private inner class ToDoHolder(view : View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var toDo : ToDoTask
        private val titleViewText : TextView = itemView.findViewById(R.id.to_do_title)
        private val importanceTextView : TextView = itemView.findViewById(R.id.to_do_importance)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(todo : ToDoTask){
            this.toDo = todo
            titleViewText.text = toDo.title
            importanceTextView.text = toDo.importance.toString()
        }

        override fun onClick(p0: View?) {
            //TODO: Implement deletion
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