package database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.avs.todolist.ToDoTask

@Database(entities = [ ToDoTask :: class], version = 1)
@TypeConverters(TypeConverter :: class)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDODao() : ToDoDao
}