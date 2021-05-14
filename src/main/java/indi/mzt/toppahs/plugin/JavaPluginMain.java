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

public final class JavaPluginMain extends JavaPlugin {
    public static final JavaPluginMain INSTANCE = new JavaPluginMain();
    private JavaPluginMain() {
        super(new JvmPluginDescriptionBuilder("indi.mzt.toppahs.plugin", "0.1.0")
                .info("EG")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("日志");
        PluginHome deal =new PluginHome();
        Class<PluginHome> cl = PluginHome.class;
        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, g -> {
            //监听群消息
            try {
                for (Method m : cl.getDeclaredMethods()) {
                    if(m.isAnnotationPresent(Prefix.class)&&g.getMessage().contentToString().startsWith(m.getAnnotation(Prefix.class).value())){
                        m.invoke(deal,g);
                    }else if(m.getAnnotation(Switch.class).value()=="Reread")
                            m.invoke(deal,g);
                }
            }catch (ReflectiveOperationException e){
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
                    if(m.getAnnotation(Switch.class).value()=="Welcome")
                        m.invoke(deal,p);
                }
            }catch (ReflectiveOperationException e){
                e.printStackTrace();
            }
        });
    }
}