package com.codemzt.toppahs.main

import com.codemzt.toppahs.plugin.cube.Cube
import com.codemzt.toppahs.plugin.cube.scrambles.Scrambles
import com.codemzt.toppahs.plugin.wca.WcaResultGet
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import java.io.File
import java.net.URL


var scramblesEventId=("redi,re,2,222,3,333fm,3fm,3ni,333ni,4ni,444ni,444f,4f,444fast,4fast,333,4,444,5,555,6,666,7,777,8,888,9,999,10,101010,11,111111,12,121212,13,131313,14,141414,15,151515,16,161616,17,171717,sq1,skewb,clock,minx,pyram,py,mi,sk,sq").split(",")
object ToppahsEntrance {
    suspend fun goTo(command:String, group: Group):String{
        var sendMsg : Message= EmptyMessageChain
        if(scramblesEventId.contains(command)){
            var cube:Cube=Cube()
            if(command=="2"||command=="3"||command=="4"||command=="5"||command=="6"||command=="7"||command=="8"||command=="9"||command=="10"||command=="11"||command=="12"||command=="13"||command=="14"||command=="15"||command=="16"||command=="17")cube.type=command+command+command
            else if(command=="py")cube.type="pyram"
            else if(command=="mi")cube.type="minx"
            else if(command=="sk")cube.type="skewb"
            else if(command=="sq")cube.type="sq1"
            else if(command=="3fm")cube.type="333fm"
            else if(command=="3ni")cube.type="333ni"
            else if(command=="444f"||command=="4f"||command=="4fast")cube.type="444fast"
            else if(command=="4ni")cube.type="444ni"
            else if(command=="re")cube.type="redi"
            else cube.type=command
            try{
                sendMsg=sendMsg.plus("${cube.type}\n${Scrambles.getScramble(cube).scramble}")
                group.sendMessage(sendMsg.plus(group.uploadImage(URL(cube.pic).openConnection().getInputStream().toExternalResource())))
            }catch(e:Exception){
                return e.toString()
            }
            return "Send message Ok."
        }

        else if(command.substring(0,3)=="wca"){
            var p=WcaResultGet.getId(command.substring(3,command.length))
            try{
                if(p.name.isEmpty())group.sendMessage (p.other)
                else group.sendMessage(WcaResultGet.getResult(p).result)
            }
            catch(e:Exception){
                return e.toString()
            }
            return "Send message Ok."
        }
        return ""
    }
}