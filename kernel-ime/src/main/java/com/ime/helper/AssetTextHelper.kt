package com.ime.helper

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.ime.entity.TableDictionary
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

object AssetTextHelper {

     fun readTxtFileFromFileSystem(filePath: String): String {
        val file = File(filePath)
        val inputStream = FileInputStream(file)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))

        val stringBuilder = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line).append("\n")
        }
        bufferedReader.close()
        return stringBuilder.toString()
    }

     fun readTxtFileFromAssets(context: Context, fileName: String): MutableList<TableDictionary> {
         var datas :MutableList<TableDictionary> = mutableListOf()
        val assetManager = context.assets
        val inputStream = assetManager.open(fileName)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream, "UTF-16"))

        val stringBuilder = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            var strings = line?.split(" ")?: mutableListOf()
            datas.add(TableDictionary(strings[3]+strings[strings.size-1],strings[0],"",strings[1].toFloat(),strings[2].toInt()))
            stringBuilder.append(line).append("\n")
        }
        bufferedReader.close()
        return datas
    }
}
