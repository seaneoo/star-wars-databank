package edu.regis.soconnor005.starwarsdatabank.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entry::class], version = 1, exportSchema = false)
abstract class DatabankDatabase : RoomDatabase() {
    abstract val entryDao: EntryDao

    companion object {
        @Volatile
        private var INSTANCE: DatabankDatabase? = null

        fun getInstance(context: Context): DatabankDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DatabankDatabase::class.java,
                        "databank_database"
                    ).fallbackToDestructiveMigration(true).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
