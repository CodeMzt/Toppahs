package com.codemzt.toppahs.plugin.wca

import com.codemzt.toppahs.config.ServiceConfig
import java.net.URL
import java.net.URLEncoder.encode
class PersonInfo{
    var other:String="not found\n"
    var name:String=""
    var wcaid:String=""
    var gender:String=""
    var times:String=""
    var area:String=""
    var single333:String=""
    var avg333:String=""
    var single222:String=""
    var avg222:String=""
    var single444:String=""
    var avg444:String=""
    var single555:String=""
    var avg555:String=""
    var single666:String=""
    var avg666:String=""
    var single777:String=""
    var avg777:String=""
    var single333bf:String=""
    var avg333bf:String=""
    var single444bf:String=""
    var avg444bf:String=""
    var single555bf:String=""
    var avg555bf:String=""
    var single333fm:String=""
    var avg333fm:String=""
    var single333oh:String=""
    var avg333oh:String=""
    var singleCl:String=""
    var avgCl:String=""
    var singleMinx:String=""
    var avgMinx:String=""
    var singlePyram:String=""
    var avgPyram:String=""
    var singleSk:String=""
    var avgSk:String=""
    var singleSQ1:String=""
    var avgSQ1:String=""
    var single333mbf:String=""
    var avg333mbf:String=""
    var single333ft:String=""
    var avg333ft:String=""
    var result:String=""
}
object WcaResultGet {
    //根据关键字找人Id
    fun getId(select:String):PersonInfo{
        var p1 =PersonInfo()
        var code= URL("${ServiceConfig.cubing}/results/person?region=World&gender=all&name=${encode(select,"utf-8")}").readText()
        code=code.substring(code.indexOf("<tbody>"),code.indexOf("</tbody>"))
        //若只有一个符合条件
        try{
        if(code.indexOf("data-id")==code.lastIndexOf("data-id")){
            p1.wcaid=code.substring(code.indexOf("data-id")+9,code.indexOf("data-id")+8+11)
            p1.name=code.substring(code.indexOf("data-name")+11,code.indexOf("\" type=\"checkbox\""))
        }else{
            var sum=0
            p1.other+="请选择：\n"
            while(code.indexOf("data-id")!=-1)
            {
                p1.other+="${code.substring(code.indexOf("data-name")+11,code.indexOf("\" type=\"checkbox\""))} ${code.substring(code.indexOf("data-id")+9,code.indexOf("data-id")+8+11)}\n"
                code=code.substring(code.indexOf("</td></tr>")+11)
                sum++
                if(sum>20){
                    p1.other="搜索范围过大，符合条件人数>20\n"
                    break
                }
            }
        }}catch (e:Exception){
        }
        return p1
    }
    fun getResult(person:PersonInfo):PersonInfo{
        var p:Int=0
        var p2:Int=0
        var code=URL("${ServiceConfig.cubing}/results/person/${person.wcaid}").readText()
        code=code.substring(code.indexOf("</div><!--//breadcrumbs-->"),code.indexOf("<h2>排名总和</h2>"))

        //截取国家
        p=code.indexOf("地区:</span>")+59
        while(code[p]!='<')p++
        person.area=code.substring(code.indexOf("地区:</span>")+59,p)
        while(person.area.endsWith(' '))person.area=person.area.substring(0,person.area.length-1)

        //截取参赛次数
        person.times=code.substring(code.indexOf("参赛次数:</span>")+48,code.indexOf("参赛次数:</span>")+51)
        while(person.times.endsWith('<')||person.times.endsWith('/'))person.times=person.times.substring(0,person.times.length-1)

        //截取性别
        person.gender=code.substring(code.indexOf("性别:</span>")+46,code.indexOf("性别:</span>")+46+1)
        
        person.result="${person.name}\n${person.wcaid},${person.area},${person.gender},${person.times}场比赛\n"
        //截取成绩
        code=code.substring(code.indexOf("<tbody>"),code.indexOf("</tbody>"))
        person.result+="项目 单次|平均\n"
        //222s
        if(code.indexOf("二阶")!=-1) {
            p = code.indexOf("/results/rankings?event=222&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.single222 = code.substring(p, p2)
            person.result+="2阶 ${person.single222}|"

        //222a
        p=code.indexOf("/results/rankings?event=222&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avg222=code.substring(p,p2)
            person.result+=person.avg222+'\n'
        }else person.result+='\n'}

        //333单次
        if(code.indexOf("三阶")!=-1) {
            p = code.indexOf("/results/rankings?event=333&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.single333 = code.substring(p, p2)
            person.result+="3阶 ${person.single333}|"

        //333平均
        p=code.indexOf("/results/rankings?event=333&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avg333=code.substring(p,p2)
            person.result+=person.avg333+'\n'
        }else person.result+='\n'}

        //444单次
        if(code.indexOf("四阶")!=-1) {
            p = code.indexOf("/results/rankings?event=444&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.single444 = code.substring(p, p2)
            person.result+="4阶 ${person.single444}|"

        //444平均
        p=code.indexOf("/results/rankings?event=444&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avg444=code.substring(p,p2)
            person.result+=person.avg444+'\n'
        }else person.result+='\n'}

        //555单次
        if(code.indexOf("五阶")!=-1) {
            p = code.indexOf("/results/rankings?event=555&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.single555 = code.substring(p, p2)
            person.result+="5阶 ${person.single555}|"

        //555平均
        p=code.indexOf("/results/rankings?event=555&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avg555=code.substring(p,p2)
            person.result+=person.avg555+'\n'
        }else person.result+='\n'}

        //666单次
        if(code.indexOf("六阶")!=-1) {
            p = code.indexOf("/results/rankings?event=666&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.single666 = code.substring(p, p2)
            person.result+="6阶 ${person.single666}|"

        //666平均
        p=code.indexOf("/results/rankings?event=666&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avg666=code.substring(p,p2)
            person.result+=person.avg666+'\n'
        }else person.result+='\n'}

        //777单次
        if(code.indexOf("七阶")!=-1) {
            p = code.indexOf("/results/rankings?event=777&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.single777 = code.substring(p, p2)
            person.result+="7阶 ${person.single777}|"

        //777平均
        p=code.indexOf("/results/rankings?event=777&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avg777=code.substring(p,p2)
            person.result+=person.avg777+'\n'
        }else person.result+='\n'}
        //3盲单次
        if(code.indexOf("三盲")!=-1) {
            p = code.indexOf("/results/rankings?event=333bf&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.single333bf = code.substring(p, p2)
            person.result+="3盲 ${person.single333bf}|"

        //3盲平均
        p=code.indexOf("/results/rankings?event=333bf&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avg333bf=code.substring(p,p2)
            person.result+=person.avg333bf+'\n'
        }else person.result+='\n'}
        //4盲单次
        if(code.indexOf("四盲")!=-1) {
            p = code.indexOf("/results/rankings?event=444bf&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.single444bf = code.substring(p, p2)
            person.result+="4盲 ${person.single444bf}|"

        //4盲平均
        p=code.indexOf("/results/rankings?event=444bf&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avg444bf=code.substring(p,p2)
            person.result+=person.avg444bf+'\n'
        }else person.result+='\n'}
        //5盲单次
        if(code.indexOf("五盲")!=-1) {
            p = code.indexOf("/results/rankings?event=555bf&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.single555bf = code.substring(p, p2)
            person.result+="5盲 ${person.single555bf}|"
        //5盲平均
        p=code.indexOf("/results/rankings?event=555bf&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avg555bf=code.substring(p,p2)
            person.result+=person.avg555bf+'\n'
        }else person.result+='\n'}
        //最少步单次
        if(code.indexOf("最少步")!=-1) {
            p = code.indexOf("/results/rankings?event=333fm&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.single333fm = code.substring(p, p2)
            person.result+="最少步 ${person.single333fm}|"

        //333fm平均
        p=code.indexOf("/results/rankings?event=333fm&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avg333fm=code.substring(p,p2)
            person.result+=person.avg333fm+'\n'
        }else person.result+='\n'}
        //333oh单次
        if(code.indexOf("单手")!=-1) {
            p = code.indexOf("/results/rankings?event=333oh&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.single333oh = code.substring(p, p2)
            person.result+="3单 ${person.single333oh}|"

        //333oh平均
        p=code.indexOf("/results/rankings?event=333oh&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avg333oh=code.substring(p,p2)
            person.result+=person.avg333oh+'\n'
        }else person.result+='\n'}
        //clock单次
        if(code.indexOf("魔表")!=-1) {
            p = code.indexOf("/results/rankings?event=clock&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.singleCl = code.substring(p, p2)
            person.result+="魔表 ${person.singleCl}|"

        //clock平均
        p=code.indexOf("/results/rankings?event=clock&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avgCl=code.substring(p,p2)
            person.result+=person.avgCl+'\n'
        }else person.result+='\n'}
        //minxs
        if(code.indexOf("五魔")!=-1) {
            p = code.indexOf("/results/rankings?event=minx&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.singleMinx = code.substring(p, p2)
            person.result+="五魔 ${person.singleMinx}|"

        //minxa
        p=code.indexOf("/results/rankings?event=minx&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avgMinx=code.substring(p,p2)
            person.result+=person.avgMinx+'\n'
        }else person.result+='\n'}
        //py单次
        if(code.indexOf("金字塔")!=-1) {
            p = code.indexOf("/results/rankings?event=pyram&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.singlePyram = code.substring(p, p2)
            person.result+="金字塔 ${person.singlePyram}|"

        //py平均
        p=code.indexOf("/results/rankings?event=pyram&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avgPyram=code.substring(p,p2)
            person.result+=person.avgPyram+'\n'
        }else person.result+='\n'}
        //sk单次
        if(code.indexOf("斜转")!=-1) {
            p = code.indexOf("/results/rankings?event=skewb&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.singleSk = code.substring(p, p2)
            person.result+="斜转 ${person.singleSk}|"

        //sk平均
        p=code.indexOf("/results/rankings?event=skewb&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avgSk=code.substring(p,p2)
            person.result+=person.avgSk+'\n'
        }else person.result+='\n'}
        //sq1单次
        if(code.indexOf("SQ1")!=-1) {
            p = code.indexOf("/results/rankings?event=sq1&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.singleSQ1 = code.substring(p, p2)
            person.result+="SQ1 ${person.singleSQ1}|"

        //sq1平均
        p=code.indexOf("/results/rankings?event=sq1&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avgSQ1=code.substring(p,p2)
            person.result+=person.avgSQ1+'\n'
        }else person.result+='\n'}
        //333mbfs
        if(code.indexOf("多盲")!=-1) {
            p = code.indexOf("/results/rankings?event=333mbf&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.single333mbf = code.substring(p, p2)
            person.result+="多盲 ${person.single333mbf}|"

        //3mbfa
        p=code.indexOf("/results/rankings?event=333mbf&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avg333mbf=code.substring(p,p2)
            person.result+=person.avg333mbf+'\n'
        }else person.result+='\n'}
        //333fts
        if(code.indexOf("脚拧")!=-1) {
            p = code.indexOf("/results/rankings?event=333ft&amp;region=") + 43
            while (code[p] != '>') p++
            p2 = p++
            while (code[p2] != '<') p2++
            person.single333ft = code.substring(p, p2)
            person.result+="脚拧 ${person.single333ft}|"

        //3mbfta
        p=code.indexOf("/results/rankings?event=333ft&amp;type=average&amp;region=")+60
        if(p!=59){
            while(code[p]!='>')p++
            p2=p++
            while(code[p2]!='<')p2++
            person.avg333ft=code.substring(p,p2)
            person.result+=person.avg333ft
        }
        }

        return person
    }
}