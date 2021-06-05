package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.main.annotations.Plugin;
import indi.mzt.toppahs.plugin.annotations.*;

import net.mamoe.mirai.event.events.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Plugin
public class PluginHome {
    //这里集合了所有功能，需要时只需添加相应前缀与功能名，再转到对应的实现方法

    static GroupMessageEvent gP=null;
    static FriendMessageEvent fD=null;
    static MemberJoinEvent mJ=null;
    static MemberLeaveEvent mL=null;

    static BasicPlugin basic;
    static AuthPlugin auth;
    static CompetitionPlugin comp;
    static ExpressPlugin exp;
    static GroupAdminPlugin gam;
    static LeavePlugin leave;
    static  RereadPlugin red;
    static ScramblesPlugin scram;
    static SwitchesPlugin swit;
    static TranslatePlugin tran;
    static WcaPlugin wca;
    static WeatherPlugin weather;
    static WelcomePlugin wel;
    //群聊
    public PluginHome(GroupMessageEvent a){
        gP=a;
        //群消息相关功能，利用构造方法注册监听
        basic=new BasicPlugin();
        auth=new AuthPlugin();
        comp=new CompetitionPlugin();
        exp=new ExpressPlugin();
        gam=new GroupAdminPlugin();
        red=new RereadPlugin();
        scram=new ScramblesPlugin();
        swit=new SwitchesPlugin();
        tran=new TranslatePlugin();
        wca=new WcaPlugin();
        weather=new WeatherPlugin();
    }
    //私聊
    public PluginHome(FriendMessageEvent a){
        fD=a;
    }
    //成员加入
    public PluginHome(MemberJoinEvent a){
        mJ=a;
        wel=new WelcomePlugin();
    }
    //群成员离开
    public PluginHome(MemberLeaveEvent a){
        mL=a;
        leave=new LeavePlugin();
    }

    public static void eventListener(Object obj){
        Character prefix=new Character('.');
        if(gP!=null)prefix= gP.getMessage().contentToString().charAt(0);
        if(fD!=null)prefix= fD.getMessage().contentToString().charAt(0);
        try {
            //实例化一个Class对象，强制类型不定
            Class<?> cl=obj.getClass();
            for(Method m: cl.getDeclaredMethods()){
                //有前缀注解，即指令
                if(m.getAnnotation(Prefix.class)!=null&&m.getAnnotation(Prefix.class).value().charAt(0)==prefix)
                    //分配职责
                    switch(m.getName()){
                    case "onGroupCommand":m.invoke(obj,gP);break;
                    case "onFriendCommand":m.invoke(obj,fD);break;
                    case "onMemberLeavingEvent":m.invoke(obj,mL);break;
                    case "onMemberJoinEvent":m.invoke(obj,mJ);break;
                    }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
