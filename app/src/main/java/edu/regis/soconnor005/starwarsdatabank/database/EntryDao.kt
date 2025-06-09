package edu.regis.soconnor005.starwarsdatabank.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg entries: Entry)

    @Insert
    suspend fun insert(entry: Entry)

    @Query("SELECT * FROM entry")
    fun getAll(): Flow<List<Entry>>

    @Query("SELECT * FROM entry WHERE id = :id")
    fun getById(id: Int): LiveData<Entry?>

    @Update
    suspend fun update(entry: Entry)

    @Delete
    suspend fun delete(entry: Entry)
}
