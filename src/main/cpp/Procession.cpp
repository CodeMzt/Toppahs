#include "pch.h"
#include "mbot.h"
#include "code.h"
#pragma warning(disable : 4996)
string sendEnd;
string pinying;
//��������Ϣ���ݡ�
string readContent;
//��¼�յ���Ϣ����Դ
unsigned long beforeSenderId, beforeGroupId;
//�յ�ͬ����Ϣ�Ĵ���
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
    sendEnd += u8"����:\n";
    w1 = ccode.find("<dd class=\"week\">") + 17;
    for (i = w1;; i++) {
        if (ccode[i] != '<')
            sendEnd += ccode[i];
        else
            break;
    }
    sendEnd += u8"\n��ǰ�¶�:";
    w1 = ccode.find("<p class=\"now\"><b>") + 18;
    for (i = w1;; i++) {
        if (ccode[i] != '<')
            sendEnd += ccode[i];
        else
            break;
    }
    sendEnd += u8"��\n��ǰ����:";
    w1 = ccode.find("<span><b>") + 9;
    for (i = w1;; i++) {
        if (ccode[i] != '<')
            sendEnd += ccode[i];
        else
            break;
    }
    w1 = i + 4;
    sendEnd += u8"\nȫ��ſ���\n";
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
    sendEnd += u8"��";
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
    string url = "https://www.tianqi.com/"; //��ַurl
    url += pinying;
    url += '/';
    id.clear();
    LPCSTR szUrl = url.c_str();
    char szAgent[] = "";
    HINTERNET hInternet1 = InternetOpenA(NULL, INTERNET_OPEN_TYPE_PRECONFIG, NULL, NULL, NULL);
    if (NULL == hInternet1) {
        InternetCloseHandle(hInternet1); //�������NULL����رմ���
        return;
    }
    HINTERNET hInternet2 = InternetOpenUrlA(hInternet1, szUrl, NULL, NULL, INTERNET_FLAG_NO_CACHE_WRITE, NULL);
    if (NULL == hInternet2) {
        InternetCloseHandle(hInternet2); //�������NULL����رմ���
        InternetCloseHandle(hInternet1);
        return;
    }
    DWORD dwMaxDataLength = 99999; //������󳤶�
    PBYTE pBuf = (PBYTE)malloc(
        dwMaxDataLength * sizeof(TCHAR)); //����ָ�룬����һ��PBYTE���͵Ŀռ䣬����ΪdwMaxDataLength*TCHAR�ַ����͵Ĵ�С
    if (NULL == pBuf) //�������ʧ�ܣ�ָ��Ϊ��ָ�룩
    {
        InternetCloseHandle(hInternet2); //�رմ���
        InternetCloseHandle(hInternet1);
        return;
    }
    DWORD dwReadDataLength = NULL; //��ʼ��
    BOOL bRet = TRUE; //��ʼ��
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
    if (*n == "ʹ��˵��") {
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
        sendEnd = "OLL\nhttp://algdb.net/puzzle/333/oll\nOLL����\nhttp://algdb.net/puzzle/333/oholl";
        return true;
    }
    if (*n == "pll" || *n == "PLL") {
        sendEnd = "PLL\nhttp://algdb.net/puzzle/333/pll\nPLL����\nhttp://algdb.net/puzzle/333/ohpll";
        return true;
    }
    if (*n == "coll" || *n == "COLL") {
        sendEnd = "http://algdb.net/puzzle/333/coll";
        return true;
    }
    if (*n == "zbll" || *n == "ZBLL") {
        sendEnd = "����ZBLL��\nhttp://algdb.net/puzzle/333/zbll\n---------------------------------------\nZBLL AS\nhttp://algdb.net/puzzle/333/zbll-zbllas\nZBLL H\nhttp://algdb.net/puzzle/333/zbll-zbllh\nZBLL L\nhttp://algdb.net/puzzle/333/zbll-zblll\nZBLL PI\nhttp://algdb.net/puzzle/333/zbll-zbllpi\nZBLL S\nhttp://algdb.net/puzzle/333/zbll-zblls\nZBLL T\nhttp://algdb.net/puzzle/333/zbll-zbllt\nZBLL U\nhttp://algdb.net/puzzle/333/zbll-zbllu";
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
	/*�������*/
	/*
	ע���¼�����-�û��Զ���
	logger - ��־���
		logger->Info(string)������Ϣ����־
		logger->Warning(string)���;��漶��־
		logger->Error(string)���ʹ�����־
	procession �㲥Դ
		procession->registerEvent(lambda) ע�����
		procession->registerEvent([](GroupMessageEvent param){ \*����*\});�Ǽ���Ⱥ��Ϣ
		procession->registerEvent([](PrivateMessageEvent param){ \*����*\});�Ǽ���˽����Ϣ
		...
	��������param�������lambda����ʹ��param.xxx������
	*/
	procession->registerEvent([](GroupMessageEvent e) {
		try {
		e.init();
        if (e.message == "mzttest") {

        }
		////���1���ʺ�������ʱ�����ܽ���
		////ͼ���ַ��Ӧ����Ҫqq�ķ��������е�ͼƬ��Ҳ����˵���ϴ�(����)ͼƬȻ��ȡ��������
		//string icon = "http://gchat.qpic.cn/gchatpic_new/1924306130/1044565129-2580521429-8ECE44863FC01DBD17FB8A177B355356/0";
		////С����
		//string titles = "{\"title\":\"hi\", \"value\":\"test3\"}";
		////����İ�ť�����ǲ��ܰ�
		//string buttons = "{\"name\":\"Test4\",\"action\":\"plugin\",\"actionData\":\"https://baidu.com\"}";
		//string x = "{\"app\":\"com.tencent.miniapp\",\"desc\":\"\",\"view\":\"notification\",\"ver\":\"0.0.0.1\",\"prompt\":\"[Ӧ��]\",\"appID\":\"\",\"sourceName\":\"\",\"actionData\":\"\",\"actionData_A\":\"\",\"sourceUrl\":\"\",\"meta\":{\"notification\":{\"appInfo\":"
		//	"{\"appName\":\"Test1\",\"appType\":4,\"appid\":1109659848,"
		//	"\"iconUrl\":\""+icon+"\"},"
		//	"\"data\":["+titles+"],"
		//	"\"title\":\"Test2\",\"button\":"
		//	"["+buttons+"],"
		//	"\"emphasis_keyword\":\"\"}},\"text\":\"\",\"sourceAd\":\"\"}";
		//e.group.SendMiraiCode(LightApp(x).toString());
		////���2�����ܽ�������Ԥ��ͼ
		////iconͼ��,Ӧ����Ҫqq�ķ��������е�ͼƬ��Ҳ����˵���ϴ�(����)ͼƬȻ��ȡ��������
		//string icon1 = "http://gchat.qpic.cn/gchatpic_new/1924306130/1044565129-2580521429-8ECE44863FC01DBD17FB8A177B355356/0";
		////Ԥ��ͼ(��ͼ),Ӧ����Ҫqq�ķ��������е�ͼƬ��Ҳ����˵���ϴ�(����)ͼƬȻ��ȡ��������
		//string preview1 = icon1;
		//string a = "{\"config\":"
		//	"{\"height\":0,\"forward\":1,\"ctime\":0,\"width\":0,\"type\":\"normal\",\"token\":\"\",\"autoSize\":0},"
		//	"\"prompt\":\"[QQС����]\",\"app\":\"com.tencent.miniapp_01\",\"ver\":\"1.0.0.103\",\"view\":\"view_8C8E89B49BE609866298ADDFF2DBABA4\","
		//	"\"meta\":{\"detail_1\":{\"appid\":\"1110081493\",\"preview\":\""+preview1+"\",\"shareTemplateData\":{},"
		//	"\"gamePointsUrl\":\"\",\"gamePoints\":\"\",\"url\":\"m.q.qq.com\",\"scene\":0,\"desc\":\"Test5\",\"title\":\"Test6\","
		//	"\"host\":{\"uin\":1000000,\"nick\":\"Ӧ����Ϣ\"},"
		//	"\"shareTemplateId\":\"8C8E89B49BE609866298ADDFF2DBABA4\",\"icon\":\""+icon1+"\",\"showLittleTail\":\"\"}},\"desc\":\"\"}";
		//e.group.SendMiraiCode(LightApp(a).toString());
		////���3��������ת����ַ
		////��ת����
		//string url = "https://www.baiduc.com";
		////iconͼ��,Ӧ����Ҫqq�ķ��������е�ͼƬ��Ҳ����˵���ϴ�(����)ͼƬȻ��ȡ��������
		//string icon2 = "http://gchat.qpic.cn/gchatpic_new/1924306130/1044565129-2580521429-8ECE44863FC01DBD17FB8A177B355356/0";
		////Ԥ��ͼ(��ͼ),Ӧ����Ҫqq�ķ��������е�ͼƬ��Ҳ����˵���ϴ�(����)ͼƬȻ��ȡ��������
		//string preview = icon2;
		//string b = "{\"config\":{\"height\":0,\"forward\":1,\"ctime\":0,\"width\":0,\"type\":\"normal\",\"token\":\"\",\"autoSize\":0},"
		//	"\"prompt\":\"[QQС����]\",\"app\":\"com.tencent.miniapp_01\",\"ver\":\"0.0.0.1\",\"view\":\"view_8C8E89B49BE609866298ADDFF2DBABA4\","
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
            if (msg.find("ͣ��") == 0) {
                if (e.sender.permission >= 1) {
                    string type = msg.erase(0, 4);
                    if (type == "������") {
                        sendEnd = "�������Ѿ�ͣ��";
                        Mzt_Write(path1, "main", "0");
                    }
                    if (type == "wca��ѯ" || type == "wca") {
                        sendEnd = "[wca��ѯ]�����Ѿ�ͣ��";
                        Mzt_Write(path1, "wca", "0");
                    }
                    if (type == "����") {
                        sendEnd = "[����]�����Ѿ�ͣ��";
                        Mzt_Write(path1, "daluan", "0");
                    }
                    if (type == "����") {
                        sendEnd = "[����]�����Ѿ�ͣ��";
                        Mzt_Write(path1, "weather", "0");
                    }
                    type.clear();
                }
                else
                    sendEnd = "��û���Ǹ����¡�";
               e.group.SendMiraiCode(sendEnd);
                sendEnd.clear();
                ans++;
            }
            else if (msg.find("����") == 0) {
                if (e.sender.permission >= 1) {
                    string type = msg.erase(0, 4);
                    if (type == "������") {
                        sendEnd = "�������Ѿ�����";
                        Mzt_Write(path1, "main", "1");
                    }
                    if (type == "wca��ѯ" || type == "wca") {
                        sendEnd = "[wca��ѯ]�����Ѿ�����";
                        Mzt_Write(path1, "wca", "1");
                    }
                    if (type == "����") {
                        sendEnd = "[����]�����Ѿ�����";
                        Mzt_Write(path1, "daluan", "1");
                    }
                    if (type == "����") {
                        sendEnd = "[����]�����Ѿ�����";
                        Mzt_Write(path1, "weather", "1");
                    }
                    type.clear();
                }
                else
                    sendEnd = "��û���Ǹ����¡�";
               e.group.SendMiraiCode(sendEnd);
                sendEnd.clear();
                ans++;
            }

            if (Mzt_Read(path1, "main") != "0") {
                if (msg.find("����") == 0 && Mzt_Read(path1, "weather") != "0") {
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
                    if (msg.find("����") == 0 && Mzt_Read(path1, "fy") != "0") {
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
                        e.group.SendMiraiCode("����[" + atid0 + ']' + time0);
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
                            Mzt_Write(path + "qun.mzt", "��ӭ��", answ);
                           e.group.SendMiraiCode("���û�ӭ��ɹ�");
                        }
                        else if (ques == "group_out") {
                            Mzt_Write(path + "qun.mzt", "��Ⱥ��", answ);
                           e.group.SendMiraiCode("������Ⱥ��ɹ�");
                        }
                        else {
                            Mzt_Write(path2, ques, answ);
                           e.group.SendMiraiCode("�������[" + ques + "]�ɹ�");

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
                            Mzt_Delete(path + "qun.mzt", "��ӭ��");
                           e.group.SendMiraiCode("ɾ����ӭ��ɹ�");
                        }
                        else if (sentence == "group_out") {
                            Mzt_Delete(path + "qun.mzt", "��Ⱥ��");
                           e.group.SendMiraiCode("ɾ����Ⱥ��ɹ�");
                        }
                        else {
                            if (Mzt_Delete(path2, sentence) == 1)
                               e.group.SendMiraiCode("ɾ������[" + sentence + "]�ɹ�");
                            else
                               e.group.SendMiraiCode("ɾ������[" + sentence + "]ʧ��");
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
			//Ȩ�޲���
			logger->Error(err.what());
		}
		catch (MemberException err) {
			if (err.type == 1) {
				//�Ҳ���Ⱥ
			}
			if (err.type == 2) {
				//�Ҳ���Ⱥ��Ա
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
                e.sender.SendMiraiCode("��ȨȺ[" + e.message + "]�ɹ�");
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
                e.sender.SendMiraiCode("����ϵ����Ա����bot");
            return false;
            });
        procession->registerEvent([](MemberJoinEvent e) {
            sendEnd.clear();
            string path = to_string(e.member.id);
            sendEnd += e.member.at() + '\n';
            string wel = Mzt_Read(path + "qun.mzt", "��ӭ��");
            if (!(wel.empty()) && wel != "error")
                sendEnd += wel;
            else sendEnd += "��ӭ������Ⱥ������С��������������Ⱥ��������������ĺ�Ⱥ����ħ��������Ҫ�������ع���Ŷ��\n���˽����𣿷���.ʹ��˵���鿴����ɣ�\n";
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
            string lev = Mzt_Read(path + "qun.mzt", "��Ⱥ��");
            if (!(lev.empty()) && lev != "error")
                sendEnd += lev;
            else {
                if (e.type == 1)
                    sendEnd += "������Ա�Ƴ���Ⱥ";
                else sendEnd += "�뿪�˱�Ⱥ";
            }
           e.group.SendMiraiCode(sendEnd);
            });
}
void onDisable() {
	/*�������,�����˳��Ż����*/
}