package database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.avs.todolist.ToDoTask
import java.util.UUID

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todotask")
    fun getToDos() : LiveData<List<ToDoTask>>

    @Query("SELECT * FROM todotask WHERE id = (:id)")
    fun getToDo(id : UUID) : LiveData<ToDoTask?>
}