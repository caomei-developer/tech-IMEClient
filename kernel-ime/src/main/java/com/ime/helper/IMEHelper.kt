package com.ime.helper


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.ime.database.DictionaryDatabase
import com.ime.entity.TableDictionary

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")
object IMEHelper {
    private const val TAG = "IME_HELPER"

    private lateinit var context: Context
    private lateinit var dictionaryDatabase: DictionaryDatabase

    fun init(context: Context){
        this.context = context
        dictionaryDatabase = DictionaryDatabase.get(context)
    }


    suspend fun insert(list:MutableList<TableDictionary>) = withContext(Dispatchers.IO){
        coroutineScope {
            ensureActive()
            IMERemote(DictionaryDatabase.get(context)).insertDictionarys(list)
        }
    }


    suspend fun getQuerySingleKey(key: String,onSuccess:(list:MutableList<TableDictionary>?)->Unit={}) = withContext(Dispatchers.IO) {
        coroutineScope {
            if (key.isEmpty()) {
                Log.e(TAG, "检测输入拼音没有值！")
                return@coroutineScope
            }
            launch {
                val data = IMERemote(DictionaryDatabase.get(context)).querySingleKey("%$key%") ?: mutableListOf()
                onSuccess.invoke(data as MutableList<TableDictionary>)
            }

        }
    }


    suspend fun getQueryAll(onSuccess:(list:MutableList<TableDictionary>?)->Unit={}) = withContext(Dispatchers.IO) {
        coroutineScope {

            val deferred = async {
                IMERemote( DictionaryDatabase.get(context)).queryAll() ?: mutableListOf()
            }
            val data = deferred.await()
            onSuccess.invoke(data as MutableList<TableDictionary>)
        }
    }


}