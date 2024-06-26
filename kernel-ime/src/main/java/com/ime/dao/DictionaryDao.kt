package com.ime.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ime.entity.TableDictionary

@Dao
interface DictionaryDao {

    @Query("select * from table_dictionary")
    fun queryAllData(): List<TableDictionary>? = mutableListOf()

    @Query("select * from table_dictionary where pinyin like :pinyin")
    suspend fun querySingleKey(pinyin: String): List<TableDictionary>? = mutableListOf()

    @Insert
    suspend fun insert(tableDictionary: TableDictionary)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDictionarys(historyList:List<TableDictionary>)

}