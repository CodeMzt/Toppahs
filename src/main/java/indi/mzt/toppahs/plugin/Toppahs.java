package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.plugin.annotations.*;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * A program called Toppahs QQ-Cuber-Bot. It aims to learn Java.
 * @version 0.1.0 2021.5.14
 * @author CodeMzt
 */

public final class Toppahs extends JavaPlugin {
    //实例化本类
    public static final Toppahs INSTANCE = new Toppahs();
    //重写父类方法
    private Toppahs() {
        super(new JvmPluginDescriptionBuilder("indi.mzt.toppahs.plugin", "0.1.0")
                .info("EG")
                .build());
    }

    //插件启动
    @Override
    public void onEnable() {
        getLogger().info("日志");
        //实例化一个插件home对象
        PluginHome deal =new PluginHome();
        //实例化一个Class对象，强制类型PluginHome
        Class<PluginHome> cl = PluginHome.class;
        //群消息lambda
        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, g -> {
            //监听群消息
            try {
                //循环PluginHome类里的每一个方法
                for (Method m : cl.getDeclaredMethods()) {
                    //含有前缀的，即指令
                    if(m.isAnnotationPresent(Prefix.class)&&g.getMessage().contentToString().startsWith(m.getAnnotation(Prefix.class).value()))
                        //执行此方法
                        m.invoke(deal,g);
                    //否则触发复读
                    else
                        if(m.getAnnotation(Switch.class).value()=="Reread")
                            //执行复读方法
                            m.invoke(deal,g);
                }
            }catch (ReflectiveOperationException e){
                //输出ReflectiveOperationException异常
                e.printStackTrace();
            }
        });
        GlobalEventChannel.INSTANCE.subscribeAlways(FriendMessageEvent.class, f -> {
            //监听好友消息
            getLogger().info(f.getMessage().contentToString());
        });
        GlobalEventChannel.INSTANCE.subscribeAlways(MemberLeaveEvent.class, p -> {
            //监听群成员退出
            try {
                for (Method m : cl.getDeclaredMethods()) {
                    //执行退群语方法
                    if(m.getAnnotation(Switch.class).value()=="Leave")
                        m.invoke(deal,p);
                }
            }catch (ReflectiveOperationException e){
                e.printStackTrace();
            }
        });
        GlobalEventChannel.INSTANCE.subscribeAlways(MemberJoinEvent.class, p -> {
            //监听成员加入
            try {
                for (Method m : cl.getDeclaredMethods()) {
                    //执行欢迎语方法
                    if(m.getAnnotation(Switch.class).value()=="Welcome")
                        m.invoke(deal,p);
                }
            }catch (ReflectiveOperationException e){
                e.printStackTrace();
            }
        });
    }
    //插件正常结束
    public void onDisable(){

    }
}