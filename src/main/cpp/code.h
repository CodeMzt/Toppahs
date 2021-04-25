#pragma once
#include "pch.h"
string Utf8ToGbk(const char* src_str);
struct UTF8Url {
    static string Encode(const string& url);
    static string Decode(const string& url);
private:
    static const string& HEX_2_NUM_MAP();
    static const string& ASCII_EXCEPTION();
    static unsigned char NUM_2_HEX(const char h, const char l);
};
char* GBKToUTF8(const char* chGBK);
void GetPinYin(unsigned char* Chinese, string& PinYin);
void GetOtherCodePinYin(int nCode, string& strValue);