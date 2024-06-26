package com.engine

private const val externalName = "IMEengine Toolkit"

class PinyinEngine(private val dictionaryPath: String) {

    private var nativeHandle: Long = 0

    init {
        System.loadLibrary("PinyinEngin")
        nativeHandle = nativeInit(dictionaryPath)
    }

    fun getWords(pinyin: String): Array<String> {
        return nativeGetWords(nativeHandle, pinyin)
    }

    fun release() {
        nativeRelease(nativeHandle)
        nativeHandle = 0
    }

    private external fun nativeInit(dictionaryPath: String): Long
    private external fun nativeGetWords(enginePtr: Long, pinyin: String): Array<String>
    private external fun nativeRelease(enginePtr: Long)
}