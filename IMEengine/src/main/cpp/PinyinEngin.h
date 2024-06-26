//
// Created by leshu on 2024/6/26.
//

#ifndef TECH_PINYINENGIN_H
#define TECH_PINYINENGIN_H

#endif //TECH_PINYINENGIN_H

#include <string>
#include <vector>
#include <unordered_map>

class PinyinEngine {
public:
    PinyinEngine(const std::string &dictionaryPath);

    ~PinyinEngine();

    std::vector<std::string> getWords(const std::string &pinyin);

private:
    void loadDictionary(const std::string& dictionaryPath);
    std::unordered_map<std::string, std::vector<std::string>> dictionary;
};

