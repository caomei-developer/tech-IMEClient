package com.ime.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_dictionary")
data class TableDictionary(
    @PrimaryKey
    @ColumnInfo(name = "pinyin") var pinyin: String, //全拼音
    @ColumnInfo(name = "chinese") var chinese: String, //对应中文
    @ColumnInfo(name = "first_pinyin_combine") var firstPinyinCombine: String? = null, //首字母 检索
    @ColumnInfo(name = "used_frequency") var usedFrequency: Float? = 0.0f, //当前使用频率
    @ColumnInfo(name = "unknown_status") var unknownStatus: Int? = 0, //当前使用频率
)
