package com.ime.helper


import androidx.room.withTransaction
import com.ime.database.DictionaryDatabase
import com.ime.entity.TableDictionary


class IMERemote(private val db: DictionaryDatabase) {

   suspend fun queryAll() = db.dictionaryDao().queryAllData()

   suspend fun querySingleKey(key: String) = db.dictionaryDao().querySingleKey(key)

   //插入一条词
   suspend fun insertDictionary(tableDictionary: TableDictionary)=  db.withTransaction{db.dictionaryDao().insert(tableDictionary)}

   //插入一组词
   suspend fun insertDictionarys(tableDictionarys: MutableList<TableDictionary>)= db.withTransaction { db.dictionaryDao().insertDictionarys(tableDictionarys) }

}