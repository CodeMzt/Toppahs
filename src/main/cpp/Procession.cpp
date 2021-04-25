#include "pch.h"
#include "mbot.h"
#include "code.h"
#pragma warning(disable : 4996)
string sendEnd;
string pinying;
//复读的信息内容·
string readContent;
//记录收到消息的来源
unsigned long beforeSenderId, beforeGroupId;
//收到同样消息的次数
int readTimes;

unsigned char chinese[10];
bool Mzt_Write(string w, string father0, string son0) {
    string father = father0 + '$';
    string son = son0 + '$';
    string txt = "", end = "", flag = "\n--------------------\n";
    // C:\Users\Administrator\Desktop
    FILE* fp;
    char buf[1000];
    fp = fopen(w.c_str(), "r");
    if (NULL == fp) {
        txt = "";
    }
    else {
        while (fgets(buf, 999, fp)) {
            txt += buf;
        }
        fclose(fp);
    }
    int hw = txt.find("\n--------------------\n");
    if (txt == "") {
        txt = "$question$\n--------------------\n$answer$";
        hw = txt.find("\n--------------------\n");
        //	return 0;
    }
    string questions = "", answers = "";
    questions = txt.substr(0, hw);
    answers = txt.substr(hw + 22);
    // cout<<endl<<hw<<endl<<questions<<endl<<answers<<endl;
    if (questions.find(father) == string::npos) {
        questions += father;
        answers += son;
        end = questions + flag + answers;
    }
    else {
        string qu2 = "", an2 = "";
        int f1 = 0, f2 = 0, f = 0;
        int fw = questions.find(father) + 1;
        for (int i = 0; i < fw; i++)
            if (questions[i] == '$') f1++;
        for (int i = 0; i < questions.length(); i++) {
            if (f != f1) qu2 += questions[i];
            if (questions[i] == '$') f++;
        }
        qu2 += father0 + '$';
        //	cout<<qu2<<endl<<fw<<endl<<f1<<endl;
        f = 0;
        for (int i = 0; i < answers.length(); i++) {
            if (f != f1) an2 += answers[i];
            if (answers[i] == '$') f++;
        }
        an2 += son0 + '$';
        //		cout<<an2<<endl<<f;
        end = qu2 + flag + an2;
    }
    // cout<<father<<endl<<son<<endl<<end<<endl<<1;
    ofstream out(w.c_str());
    out << end;
    out.close();
    return 1;
}
bool Mzt_Delete(string w, string father0) {
    string father = father0 + '$';
    string txt = "", end = "", flag = "\n--------------------\n";
    // C:\Users\Administrator\Desktop
    FILE* fp;
    char buf[1000];
    fp = fopen(w.c_str(), "r");
    if (NULL == fp) return 0;
    while (fgets(buf, 999, fp)) {
        txt += buf;
    }
    fclose(fp);
    int hw = txt.find("\n--------------------\n");
    if (txt == "") {
        return 0;
    }
    string questions = "", answers = "";
    questions = txt.substr(0, hw);
    answers = txt.substr(hw + 22);
    // cout<<endl<<hw<<endl<<questions<<endl<<answers<<endl;
    if (questions.find(father) == string::npos) {
        //   cout << "Doesn\'t have the question.\n";
        return 0;
    }
    else {
        string qu2 = "", an2 = "";
        int f1 = 0, f2 = 0, f = 0;
        int fw = questions.find(father) + 1;
        for (int i = 0; i < fw; i++)
            if (questions[i] == '$') f1++;
        for (int i = 0; i < questions.length(); i++) {
            if (f != f1) qu2 += questions[i];
            if (questions[i] == '$') f++;
        }
        //	cout<<qu2<<endl<<fw<<endl<<f1<<endl;
        f = 0;
        for (int i = 0; i < answers.length(); i++) {
            if (f != f1) an2 += answers[i];
            if (answers[i] == '$') f++;
        }
        //		cout<<an2<<endl<<f;
        end = qu2 + flag + an2;
        //   cout << "Clean over.\n";
    }
    // cout<<father<<endl<<son<<endl<<end<<endl<<1;
    ofstream out(w.c_str());
    out << end;
    out.close();
    return 1;
}
string Mzt_Read(string w, string father0) {
    string father = father0;
    father += "$";
    string son, txt;
    FILE* fp;
    char buf[1000];
    fp = fopen(w.c_str(), "r");
    if (fp == NULL)return "";
    else
        while (fgets(buf, 1000, fp)) {
            txt += buf;
        }
    fclose(fp);
    if (txt.find(father) == string::npos)
        return "";
    else {
        int num = 0, nun = 0;
        bool f = 1;
        string code = txt.substr(0, txt.find(father) + father.length());
        for (int i = 0; i < code.length(); i++)
            if (code[i] == '$') num++;
        int j = txt.find("--------------------") + 21;
        string eend = txt.substr(j);
        num -= 1;
        for (int i = 0; i < eend.length(); i++) {
            if (eend[i] == '$' && f) nun++;
            if (nun == num) {
                f = 0;
                if (nun == 0)
                    ;
                else
                    i += 1;
                for (; i < eend.length(); i++) {
                    if (eend[i] != '$')
                        son += eend[i];
                    else
                        return son;
                }
            }
        }
    }
    if (son == "")
        return "";
    else
        return son;
}
int cube_Before = 0;
void print2(string ccode) {
    long long w1 = ccode.find("<div class=\"weatherbox\">") + 25, i;
    ccode.erase(0, w1);
    w1 = ccode.find("<dd class=\"name\"><h2>") + 21;
    string no;
    no.clear();
    for (i = w1;; i++) {
        if (ccode[i] != '<')
            no += ccode[i];
        else
            break;
    }

    sendEnd += no;
    sendEnd += u8"天气:\n";
    w1 = ccode.find("<dd class=\"week\">") + 17;
    for (i = w1;; i++) {
        if (ccode[i] != '<')
            sendEnd += ccode[i];
        else
            break;
    }
    sendEnd += u8"\n当前温度:";
    w1 = ccode.find("<p class=\"now\"><b>") + 18;
    for (i = w1;; i++) {
        if (ccode[i] != '<')
            sendEnd += ccode[i];
        else
            break;
    }
    sendEnd += u8"℃\n当前天气:";
    w1 = ccode.find("<span><b>") + 9;
    for (i = w1;; i++) {
        if (ccode[i] != '<')
            sendEnd += ccode[i];
        else
            break;
    }
    w1 = i + 4;
    sendEnd += u8"\n全天概况：\n";
    for (i = w1;; i++) {
        if (ccode[i] != '<')
            sendEnd += ccode[i];
        else
            break;
    }
    sendEnd += '\n';
    w1 = ccode.find("<dd class=\"shidu\"><b>") + 21;
    for (i = w1;; i++) {
        if (ccode[i] != '<')
            sendEnd += ccode[i];
        else
            break;
    }
    w1 = i + 7;
    sendEnd += u8"，";
    for (i = w1;; i++) {
        if (ccode[i] != '<')
            sendEnd += ccode[i];
        else
            break;
    }
    w1 = i + 7;
    sendEnd += '\n';
    for (i = w1;; i++) {
        if (ccode[i] != '<')
            sendEnd += ccode[i];
        else
            break;
    }
}
void TIANQI(string plname)
{
    string id;
    for (int ii = 0; ii < size(plname); ii++)
    {
        chinese[ii] = plname[ii];
    }

    GetPinYin(chinese, pinying);
    vector<char> v;
    string url = "https://www.tianqi.com/"; //网址url
    url += pinying;
    url += '/';
    id.clear();
    LPCSTR szUrl = url.c_str();
    char szAgent[] = "";
    HINTERNET hInternet1 = InternetOpenA(NULL, INTERNET_OPEN_TYPE_PRECONFIG, NULL, NULL, NULL);
    if (NULL == hInternet1) {
        InternetCloseHandle(hInternet1); //如果返回NULL，则关闭处理
        return;
    }
    HINTERNET hInternet2 = InternetOpenUrlA(hInternet1, szUrl, NULL, NULL, INTERNET_FLAG_NO_CACHE_WRITE, NULL);
    if (NULL == hInternet2) {
        InternetCloseHandle(hInternet2); //如果返回NULL，则关闭处理
        InternetCloseHandle(hInternet1);
        return;
    }
    DWORD dwMaxDataLength = 99999; //设置最大长度
    PBYTE pBuf = (PBYTE)malloc(
        dwMaxDataLength * sizeof(TCHAR)); //设置指针，申请一个PBYTE类型的空间，长度为dwMaxDataLength*TCHAR字符类型的大小
    if (NULL == pBuf) //如果申请失败（指针为空指针）
    {
        InternetCloseHandle(hInternet2); //关闭处理
        InternetCloseHandle(hInternet1);
        return;
    }
    DWORD dwReadDataLength = NULL; //初始化
    BOOL bRet = TRUE; //初始化
    do {
        ZeroMemory(pBuf, dwMaxDataLength * sizeof(TCHAR));
        bRet = InternetReadFile(hInternet2, pBuf, dwMaxDataLength, &dwReadDataLength);
        for (DWORD dw = 0; dw < dwReadDataLength; dw++) {
            v.push_back(pBuf[dw]);
        }
    } while (NULL != dwReadDataLength);
    vector<char>::iterator i;
    string eid;
    for (i = v.begin(); i != v.end(); i++) eid += *i;
    print2(eid);
    pinying.clear();
    int dsa = 1;
    return;
}
string fy(string word) {
    vector<char> v;
    string url = "http://www.iciba.com/word?w=";
    string sendEnd;
    for (int i = 0; i < word.length(); i++) {
        if (word[i] == '\\'&&word[i+1]=='n' )word.replace(i, 2, "$");
    }
    url += UTF8Url::Encode(GBKToUTF8(word.c_str()));
    for (int i = 0; i < url.length(); i++) {
        if (url[i] == ' ')url.replace(i, 1, "%20");
    }
    cout << url << endl;
    LPCSTR szurl = url.c_str();
    char szAgent[] = "";
    HINTERNET h1 = InternetOpenA(NULL, INTERNET_OPEN_TYPE_PRECONFIG, NULL, NULL, 0);
    if (NULL == h1) {
        InternetCloseHandle(h1);
        return 0;
    }
    HINTERNET h2 = InternetOpenUrlA(h1, szurl, NULL, INTERNET_FLAG_NO_CACHE_WRITE, 0, 0);
    if (NULL == h2) {
        InternetCloseHandle(h2);
        InternetCloseHandle(h1);
        return 0;
    }
    DWORD ll1 = 1000;
    PBYTE pbuf = (PBYTE)malloc(ll1 * sizeof(TCHAR));
    if (NULL == pbuf) {
        InternetCloseHandle(h2);
        InternetCloseHandle(h1);
        return 0;
    }
    string result;
    DWORD ll2 = NULL;
    BOOL F = 1;
    do {
        ZeroMemory(pbuf, ll1 * sizeof(TCHAR));
        F = InternetReadFile(h2, pbuf, ll1, &ll2);
        for (DWORD o = 0; o < ll2; o++) {
            v.push_back(pbuf[o]);
        }
    } while (ll2 != NULL);
    vector<char>::iterator ii;
    for (ii = begin(v); ii != end(v); ii++) {
        result += *ii;
    }
    result.erase(0, result.find("\"application/json\">") + 19);
    int p;
    if (result.find("translate_result") == string::npos) {
        p = result.find("\",\"means\":[\"") + 12;
        result.erase(0, p);
        p = result.find("\"") ;
    }
    else {
        p = result.find("translate_result\":\"") + 19;
        result.erase(0, p);
        p = result.find(",\"translate_type\"") - 1;
    }
        result = result.substr(0, p);
    for (int i = 0; i < result.length(); i++) {
        if (result[i] == '$')result.replace(i, 1, "\n");
    }
    for (int i = 0; i < result.length(); i++) {
        if (result[i] == '\\')result.erase(i, 1);
    }
    return result;
    // endd = Utf8ToGbk(endd.c_str());
    
}
bool MiscellaneousClass(string* n) {
    if (*n == "使用说明") {
        sendEnd = "https://www.showdoc.cc/p/22dde62c998bf1e392f841568c89fec0";
        return true;
    }
    if (*n == "cll" || *n == "eg0") {
        sendEnd = "http://algdb.net/puzzle/222/cll";
        return true;
    }
    if (*n == "eg1") {
        sendEnd = "http://algdb.net/puzzle/222/eg1";
        return true;
    }
    if (*n == "eg2") {
        sendEnd = "http://algdb.net/puzzle/222/eg2";
        return true;
    }
    if (*n == "ortegaoll" || *n == "ortega oll") {
        sendEnd = "http://algdb.net/puzzle/222/ortegaoll";
        return true;
    }
    if (*n == "ortega pbl" || *n == "ortegapbl") {
        sendEnd = "http://algdb.net/puzzle/222/ortegapbl";
        return true;
    }
    if (*n == "f2l" || *n == "F2L") {
        sendEnd = "http://algdb.net/puzzle/333/f2l";
        return true;
    }
    if (*n == "oll" || *n == "OLL") {
        sendEnd = "OLL\nhttp://algdb.net/puzzle/333/oll\nOLL单手\nhttp://algdb.net/puzzle/333/oholl";
        return true;
    }
    if (*n == "pll" || *n == "PLL") {
        sendEnd = "PLL\nhttp://algdb.net/puzzle/333/pll\nPLL单手\nhttp://algdb.net/puzzle/333/ohpll";
        return true;
    }
    if (*n == "coll" || *n == "COLL") {
        sendEnd = "http://algdb.net/puzzle/333/coll";
        return true;
    }
    if (*n == "zbll" || *n == "ZBLL") {
        sendEnd = "三阶ZBLL：\nhttp://algdb.net/puzzle/333/zbll\n---------------------------------------\nZBLL AS\nhttp://algdb.net/puzzle/333/zbll-zbllas\nZBLL H\nhttp://algdb.net/puzzle/333/zbll-zbllh\nZBLL L\nhttp://algdb.net/puzzle/333/zbll-zblll\nZBLL PI\nhttp://algdb.net/puzzle/333/zbll-zbllpi\nZBLL S\nhttp://algdb.net/puzzle/333/zbll-zblls\nZBLL T\nhttp://algdb.net/puzzle/333/zbll-zbllt\nZBLL U\nhttp://algdb.net/puzzle/333/zbll-zbllu";
        return true;
    }
    if (*n == "ollcp" || *n == "OLLCP") {
        sendEnd = "http://algdb.net/puzzle/333/ollcp";
        return true;
    }
    if (*n == "ell") {
        sendEnd = "http://algdb.net/puzzle/333/ell";
        return true;
    }
    if (*n == "vls") {
        sendEnd = "http://algdb.net/puzzle/333/vls";
        return true;
    }
    if (*n == "wv") {
        sendEnd = "http://algdb.net/puzzle/333/wv\n-- -------------------------------------\nwvls\nhttp://algdb.net/puzzle/333/wv-wvls\nwvls fl\nhttp://algdb.net/puzzle/333/wv-wvlsfl";
        return true;
    }
    if (*n == "ep") {
        sendEnd = "Square-1 EP:\nhttp://algdb.net/puzzle/sq1/ep\n-- -------------------------------------\non U\nhttp://algdb.net/puzzle/sq1/ep-solvedu\nAdj on U\nhttp://algdb.net/puzzle/sq1/ep-adju\nH on U\nhttp://algdb.net/puzzle/sq1/ep-hu\nOccw on U\nhttp://algdb.net/puzzle/sq1/ep-occwu\nOcw on U\nhttp://algdb.net/puzzle/sq1/ep-ocwu\nOpp on U\nhttp://algdb.net/puzzle/sq1/ep-oppu\nUccw on U\nhttp://algdb.net/puzzle/sq1/ep-uccwu\nUcw on U\nhttp://algdb.net/puzzle/sq1/ep-ucwu\nW on U\nhttp://algdb.net/puzzle/sq1/ep-wu\nZ on U\nhttp://algdb.net/puzzle/sq1/ep-zu";
        return true;
    }

    return false;
}
string url;
void onEnable() {
	/*插件启动*/
	/*
	注册事件监听-用户自定义
	logger - 日志组件
		logger->Info(string)发送消息级日志
		logger->Warning(string)发送警告级日志
		logger->Error(string)发送错误级日志
	procession 广播源
		procession->registerEvent(lambda) 注册监听
		procession->registerEvent([](GroupMessageEvent param){ \*处理*\});是监听群消息
		procession->registerEvent([](PrivateMessageEvent param){ \*处理*\});是监听私聊消息
		...
	参数都在param变量里，在lambda块中使用param.xxx来调用
	*/
	procession->registerEvent([](GroupMessageEvent e) {
		try {
		e.init();
        if (e.message == "mzttest") {

        }
		////风格1，适合文字暂时，不能交互
		////图标地址，应该是要qq的服务器里有的图片，也就是说先上传(发送)图片然后取下载链接
		//string icon = "http://gchat.qpic.cn/gchatpic_new/1924306130/1044565129-2580521429-8ECE44863FC01DBD17FB8A177B355356/0";
		////小标题
		//string titles = "{\"title\":\"hi\", \"value\":\"test3\"}";
		////下面的按钮，但是不能按
		//string buttons = "{\"name\":\"Test4\",\"action\":\"plugin\",\"actionData\":\"https://baidu.com\"}";
		//string x = "{\"app\":\"com.tencent.miniapp\",\"desc\":\"\",\"view\":\"notification\",\"ver\":\"0.0.0.1\",\"prompt\":\"[应用]\",\"appID\":\"\",\"sourceName\":\"\",\"actionData\":\"\",\"actionData_A\":\"\",\"sourceUrl\":\"\",\"meta\":{\"notification\":{\"appInfo\":"
		//	"{\"appName\":\"Test1\",\"appType\":4,\"appid\":1109659848,"
		//	"\"iconUrl\":\""+icon+"\"},"
		//	"\"data\":["+titles+"],"
		//	"\"title\":\"Test2\",\"button\":"
		//	"["+buttons+"],"
		//	"\"emphasis_keyword\":\"\"}},\"text\":\"\",\"sourceAd\":\"\"}";
		//e.group.SendMiraiCode(LightApp(x).toString());
		////风格2，不能交互，有预览图
		////icon图标,应该是要qq的服务器里有的图片，也就是说先上传(发送)图片然后取下载链接
		//string icon1 = "http://gchat.qpic.cn/gchatpic_new/1924306130/1044565129-2580521429-8ECE44863FC01DBD17FB8A177B355356/0";
		////预览图(大图),应该是要qq的服务器里有的图片，也就是说先上传(发送)图片然后取下载链接
		//string preview1 = icon1;
		//string a = "{\"config\":"
		//	"{\"height\":0,\"forward\":1,\"ctime\":0,\"width\":0,\"type\":\"normal\",\"token\":\"\",\"autoSize\":0},"
		//	"\"prompt\":\"[QQ小程序]\",\"app\":\"com.tencent.miniapp_01\",\"ver\":\"1.0.0.103\",\"view\":\"view_8C8E89B49BE609866298ADDFF2DBABA4\","
		//	"\"meta\":{\"detail_1\":{\"appid\":\"1110081493\",\"preview\":\""+preview1+"\",\"shareTemplateData\":{},"
		//	"\"gamePointsUrl\":\"\",\"gamePoints\":\"\",\"url\":\"m.q.qq.com\",\"scene\":0,\"desc\":\"Test5\",\"title\":\"Test6\","
		//	"\"host\":{\"uin\":1000000,\"nick\":\"应用消息\"},"
		//	"\"shareTemplateId\":\"8C8E89B49BE609866298ADDFF2DBABA4\",\"icon\":\""+icon1+"\",\"showLittleTail\":\"\"}},\"desc\":\"\"}";
		//e.group.SendMiraiCode(LightApp(a).toString());
		////风格3，可以跳转到网址
		////跳转链接
		//string url = "https://www.baiduc.com";
		////icon图标,应该是要qq的服务器里有的图片，也就是说先上传(发送)图片然后取下载链接
		//string icon2 = "http://gchat.qpic.cn/gchatpic_new/1924306130/1044565129-2580521429-8ECE44863FC01DBD17FB8A177B355356/0";
		////预览图(大图),应该是要qq的服务器里有的图片，也就是说先上传(发送)图片然后取下载链接
		//string preview = icon2;
		//string b = "{\"config\":{\"height\":0,\"forward\":1,\"ctime\":0,\"width\":0,\"type\":\"normal\",\"token\":\"\",\"autoSize\":0},"
		//	"\"prompt\":\"[QQ小程序]\",\"app\":\"com.tencent.miniapp_01\",\"ver\":\"0.0.0.1\",\"view\":\"view_8C8E89B49BE609866298ADDFF2DBABA4\","
		//	"\"meta\":{\"detail_1\":{\"appid\":\"1109937557\",\"preview\":\""+preview+"\",\"shareTemplateData\":{},\"gamePointsUrl\":\"\",\"gamePoints\":\"\",\"url\":\"m.q.qq.com\",\"scene\":0,\"desc\":\"Test1\",\"title\":\"Test3\",\"host\":{\"uin\":0,\"nick\":\"\"},\"shareTemplateId\":\"8C8E89B49BE609866298ADDFF2DBABA4\",\"icon\":\""+icon+"\",\"qqdocurl\":\""+url+"\",\"showLittleTail\":\"\"}},\"desc\":\"\"}";
		//e.group.SendMiraiCode(LightApp(b).toString());
        string path =to_string(e.group.id);
        if (Mzt_Read("group.mzt", path) != "1") {
            return;
        }
        string msg = e.message;
        int ans = 0;
        if (msg[0] == '.') {
            msg.erase(0, 1);
           msg = Utf8ToGbk(msg.c_str());
           for (int i = 0; i < msg.length(); i++) {
               if (msg[i]==' ')msg.erase(0,1);
               else break;
           }
            string path1, path2;
            path1 += path + ".mzt";
            path2 += path + "oc.mzt";
            if (msg.find("停用") == 0) {
                if (e.sender.permission >= 1) {
                    string type = msg.erase(0, 4);
                    if (type == "机器人") {
                        sendEnd = "机器人已经停用";
                        Mzt_Write(path1, "main", "0");
                    }
                    if (type == "wca查询" || type == "wca") {
                        sendEnd = "[wca查询]功能已经停用";
                        Mzt_Write(path1, "wca", "0");
                    }
                    if (type == "打乱") {
                        sendEnd = "[打乱]功能已经停用";
                        Mzt_Write(path1, "daluan", "0");
                    }
                    if (type == "天气") {
                        sendEnd = "[天气]功能已经停用";
                        Mzt_Write(path1, "weather", "0");
                    }
                    type.clear();
                }
                else
                    sendEnd = "你没有那个本事。";
               e.group.SendMiraiCode(sendEnd);
                sendEnd.clear();
                ans++;
            }
            else if (msg.find("启用") == 0) {
                if (e.sender.permission >= 1) {
                    string type = msg.erase(0, 4);
                    if (type == "机器人") {
                        sendEnd = "机器人已经启用";
                        Mzt_Write(path1, "main", "1");
                    }
                    if (type == "wca查询" || type == "wca") {
                        sendEnd = "[wca查询]功能已经启用";
                        Mzt_Write(path1, "wca", "1");
                    }
                    if (type == "打乱") {
                        sendEnd = "[打乱]功能已经启用";
                        Mzt_Write(path1, "daluan", "1");
                    }
                    if (type == "天气") {
                        sendEnd = "[天气]功能已经启用";
                        Mzt_Write(path1, "weather", "1");
                    }
                    type.clear();
                }
                else
                    sendEnd = "你没有那个本事。";
               e.group.SendMiraiCode(sendEnd);
                sendEnd.clear();
                ans++;
            }

            if (Mzt_Read(path1, "main") != "0") {
                if (msg.find("天气") == 0 && Mzt_Read(path1, "weather") != "0") {
                    string placeN =msg;
                    placeN.erase(0, 4);
                    TIANQI(placeN);
                    sendEnd = Utf8ToGbk(sendEnd.c_str());
                    if (sendEnd.find("Transitional//EN") != string::npos)
                        e.group.SendMiraiCode("Area not found");
                    else e.group.SendMiraiCode(sendEnd);
                    sendEnd.clear();
                    placeN.clear();
                    ans++;
                }
                else
                    if (msg.find("翻译") == 0 && Mzt_Read(path1, "fy") != "0") {
                        string word =msg;
                        word.erase(0, 4);
                        sendEnd = fy(word);
                        sendEnd = Utf8ToGbk(sendEnd.c_str());
                       e.group.SendMsg(sendEnd);
                        sendEnd.clear();
                        word.clear();
                        ans++;
                    }
                    else if (MiscellaneousClass(&msg) == 1) {
                       e.group.SendMiraiCode(sendEnd);
                        sendEnd.clear();
                        ans++;
                    }
                    else if (msg[0] == 'b' && msg[1] == 'a' && msg[2] == 'n'
                        && e.sender.permission >= 1) {
                        if (msg.find("banoall") == 0) {
                            
                        }
                        string s, atid0, time0;
                        long long atid, time;
                        bool f = 0;
                        int ss = 0, ff = 0;
                        if (msg[3] == ' ')
                            msg.erase(0, 4);
                        else
                            msg.erase(0, 3);
                        s =msg;
                        for (int i = 0; i < s.length(); i++) {
                            if (s[i] >= '0' && s[i] <= '9') {
                                if (f == 0) {
                                    f = 1;
                                    ss = i;
                                }
                            }
                            else if (f == 1) {
                                f = 0;
                                ff = i;
                                break;
                            }
                        }
                        atid0 = s.substr(ss, ff);
                        atid = atoll(atid0.c_str());
                        for (int i = ff; i < s.length(); i++) {
                            if (s[i] >= '0' && s[i] <= '9') {
                                if (!f) {
                                    f = 1;
                                    ss = i;
                                }
                            }
                            else if (f) {
                                ff = i;
                                f = 0;
                            }
                        }
                        time0 = s.substr(ss, ff - 1);
                        time = atoll(time0.c_str());
                        char l = s[s.length() - 1];
                        int op = 0;
                        switch (l) {
                        case 's':
                            op = 1;
                            break;
                        case 'm':
                            op = 60;
                            break;
                        case 'h':
                            op = 60 * 60;
                            break;
                        case 'd':
                            op = 60 * 60 * 24;
                            break;
                        case 'w':
                            op = 60 * 60 * 24 * 7;
                            break;
                        }
                        Member me;
                        me = e.sender;
                        me.id = atid;
                        me.Mute(op * time);
                        e.group.SendMiraiCode("禁言[" + atid0 + ']' + time0);
                        sendEnd.clear();
                        ans++;
                    }
                    else if (msg[0] == 'a' && msg[1] == 'd' && msg[2] == 'd'
                        && Mzt_Read(path1, "add") != "0" && msg.find("$") != string::npos
                        && e.sender.permission >= 1) {
                        string sentence = msg;
                        sentence.erase(0, 4);
                        int w1 = sentence.find('$');
                        string ques = sentence.substr(0, w1);
                        string answ = sentence.substr(w1 + 1);
                        if (ques == "group_in") {
                            Mzt_Write(path + "qun.mzt", "欢迎语", answ);
                           e.group.SendMiraiCode("设置欢迎语成功");
                        }
                        else if (ques == "group_out") {
                            Mzt_Write(path + "qun.mzt", "退群语", answ);
                           e.group.SendMiraiCode("设置退群语成功");
                        }
                        else {
                            Mzt_Write(path2, ques, answ);
                           e.group.SendMiraiCode("添加问题[" + ques + "]成功");

                        }
                        sendEnd.clear();
                        ans++;
                    }
                    else if (msg.find("t")==0|| msg.find("k")==0) {
                    msg.erase(0, 1);
                    string kid ;
                    kid =msg;
                    if (kid.find("[mirai") == 1) {
                        kid.erase(0, 10);
                        kid.erase(kid.length()-1,1);
                    }
                    cout << kid << endl;
                    e.sender.id = atoi(kid.c_str());
                    e.sender.Kick("");
                    }
                    else if (msg[0] == 'd' && msg[1] == 'l' && msg[2] == 't'
                        && Mzt_Read(path1, "dlt") != "0"
                        && e.sender.permission >= 1) {
                        string sentence = msg;
                        sentence.erase(0, 4);
                        if (sentence == "group_in") {
                            Mzt_Delete(path + "qun.mzt", "欢迎语");
                           e.group.SendMiraiCode("删除欢迎语成功");
                        }
                        else if (sentence == "group_out") {
                            Mzt_Delete(path + "qun.mzt", "退群语");
                           e.group.SendMiraiCode("删除退群语成功");
                        }
                        else {
                            if (Mzt_Delete(path2, sentence) == 1)
                               e.group.SendMiraiCode("删除问题[" + sentence + "]成功");
                            else
                               e.group.SendMiraiCode("删除问题[" + sentence + "]失败");
                        }

                        sendEnd.clear();
                        ans++;
                    }
                    else {
                        sendEnd = Mzt_Read(path2, msg);
                       e.group.SendMsg(sendEnd);
                    }
            }
        }
        else {
            if (ans == 0) {
                if (e.sender.id != beforeSenderId && e.message == readContent
                    && e.group.id == beforeGroupId
                    || (readTimes == 0)) {
                    readTimes++;
                    beforeSenderId = e.sender.id;
                    if (readTimes == 1) {
                        readContent = e.message;
                        beforeGroupId = e.group.id;
                    }
                }
                else {
                    if (e.message != readContent) {
                        readContent = "";
                        readTimes = 0;
                        beforeSenderId = 0;
                        beforeGroupId = 0;
                    }
                };
            }
            else
                ans = 0;
            if (readTimes == 3) {
               e.group.SendMiraiCode(Utf8ToGbk(e.message.c_str()));
                readTimes = 0;
                readContent = "";
                beforeGroupId = 0;
            }
            return;
        }
        msg.clear();
		}
		catch (BotException err) {
			//权限不足
			logger->Error(err.what());
		}
		catch (MemberException err) {
			if (err.type == 1) {
				//找不到群
			}
			if (err.type == 2) {
				//找不到群成员
			}
		}
		});
        procession->registerEvent([](PrivateMessageEvent e) {
            try {
                e.init();
                e.sender.init();
            }
            catch (const std::exception&) {
            }
            if (Mzt_Read("admin.mzt",to_string(e.sender.id)) == "1" && e.message.find(".sq") != string::npos) {
                e.message.erase(0, 3);
                Mzt_Write("group.mzt", e.message, "1");
                e.sender.SendMiraiCode("授权群[" + e.message + "]成功");
            }
            });
        procession->registerEvent([](GroupInviteEvent e)->bool {
            try {
                e.init();
                e.sender.init();
            }
            catch (const std::exception&) {

            }
            if (Mzt_Read("admin.mzt", to_string(e.sender.id)) == "1") {
                return ACCEPT;
            }
            else
                e.sender.SendMiraiCode("请联系管理员申请bot");
            return false;
            });
        procession->registerEvent([](MemberJoinEvent e) {
            sendEnd.clear();
            string path = to_string(e.member.id);
            sendEnd += e.member.at() + '\n';
            string wel = Mzt_Read(path + "qun.mzt", "欢迎语");
            if (!(wel.empty()) && wel != "error")
                sendEnd += wel;
            else sendEnd += "欢迎大佬入群！我是小助手托帕。你在群里可以随心所欲的和群友聊魔方，但不要忘记遵守规则哦！\n想了解我吗？发送.使用说明查看详情吧！\n";
           e.group.SendMiraiCode(sendEnd);
            });
        procession->registerEvent([](MemberLeaveEvent e) {
            sendEnd.clear();
            try
            {
                e.member.init();
            }
            catch (const std::exception&)
            {
            }
            string path = to_string(e.member.id);
            sendEnd += '[' + path + ']' + ' ';
            string lev = Mzt_Read(path + "qun.mzt", "退群语");
            if (!(lev.empty()) && lev != "error")
                sendEnd += lev;
            else {
                if (e.type == 1)
                    sendEnd += "被管理员移出本群";
                else sendEnd += "离开了本群";
            }
           e.group.SendMiraiCode(sendEnd);
            });
}
void onDisable() {
	/*插件结束,正常退出才会调用*/
}