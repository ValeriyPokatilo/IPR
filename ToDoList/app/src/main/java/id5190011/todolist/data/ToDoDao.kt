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

    @Delete
    suspend fun deleteItem(toDoData: ToDoData)

    @Query(value = "DELETE FROM todo_table")
    suspend fun deleteAll()
}