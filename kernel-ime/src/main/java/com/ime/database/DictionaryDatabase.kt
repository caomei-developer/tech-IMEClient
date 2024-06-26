package com.ime.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ime.dao.DictionaryDao
import com.ime.entity.TableDictionary

@Database(entities = [TableDictionary::class], version = 1)
abstract class DictionaryDatabase :RoomDatabase(){
     abstract fun dictionaryDao(): DictionaryDao

    companion object{
        private const val DB_MANE = "ime.db"

        private val version = 1

        private val updateVersion = 1

        private val updateSQL = ""

        @Volatile
        private var instance : DictionaryDatabase?=null

        fun get(context: Context): DictionaryDatabase {
            return if (updateVersion > version && updateSQL.isNotEmpty()) {
                instance ?: updateDatabase(context)
                    .also { instance = it }
            } else {
                instance ?: initDatabase(context)
                    .also { instance = it }
            }
        }


        private fun initDatabase(context: Context): DictionaryDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                DictionaryDatabase::class.java,
                DB_MANE
            ).build()
        }


        private fun updateDatabase(context: Context):DictionaryDatabase {
            return Room.databaseBuilder(context.applicationContext,
                DictionaryDatabase::class.java, DB_MANE)
                .addMigrations(object : Migration(version, updateVersion) {
                    override fun migrate(db: SupportSQLiteDatabase) {
                        db.execSQL(updateSQL)
                    }
                }).build()
        }

    }
}