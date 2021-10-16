package id5190011.todolist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import id5190011.todolist.data.models.ToDoData

@Dao
interface ToDoDao {

    @Query(value = "SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)

    @Update
    suspend fun updateData(toDoData: ToDoData)
}