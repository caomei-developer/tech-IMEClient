//
// Created by leshu on 2024/6/26.
//


#include "PinyinEngin.h"
#include <fstream>
#include <sstream>
#include <jni.h>

// 用户进入开始初始化字典
PinyinEngine::PinyinEngine(const std::string& dictionaryPath) {
    loadDictionary(dictionaryPath);
}

//关闭释放资源
PinyinEngine::~PinyinEngine() {
    // 释放资源
    dictionary.clear();
}


void PinyinEngine::loadDictionary(const std::string& dictionaryPath) {
    std::ifstream file(dictionaryPath);
    std::string line;

    while (std::getline(file, line)) {
        std::istringstream iss(line);
        std::string pinyin, word;
        iss >> pinyin;
        while (iss >> word) {
            dictionary[pinyin].push_back(word);
        }
    }
}

std::vector<std::string> PinyinEngine::getWords(const std::string &pinyin) {
    if (dictionary.find(pinyin) != dictionary.end()) {
        return dictionary[pinyin];
    }
    return {};
}


/**
 *  启动 可以通过  通过c++
 *  android  后续使用不到
 */
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_pinyin_PinyinEngine_nativeGetWords(JNIEnv *env, jobject thiz, jlong enginePtr, jstring pinyin) {
    PinyinEngine* engine = reinterpret_cast<PinyinEngine*>(enginePtr);
    const char *pinyin_cstr = env->GetStringUTFChars(pinyin, nullptr);
    std::vector<std::string> words = engine->getWords(pinyin_cstr);
    env->ReleaseStringUTFChars(pinyin, pinyin_cstr);

    jobjectArray result = env->NewObjectArray(words.size(), env->FindClass("java/lang/String"), nullptr);
    for (size_t i = 0; i < words.size(); ++i) {
        env->SetObjectArrayElement(result, i, env->NewStringUTF(words[i].c_str()));
    }

    return reinterpret_cast<jstring>(result);
}