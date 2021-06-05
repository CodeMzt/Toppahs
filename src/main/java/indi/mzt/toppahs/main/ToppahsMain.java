package indi.mzt.toppahs.main;

//import indi.mzt.WcaData.utils.DealMain;
import indi.mzt.toppahs.plugin.*;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.*;

import java.io.IOException;


/**
 * A program called Toppahs QQ-Cuber-Bot. It aims to learn Java.
 * @version 0.1.0 2021.5.14
 * @author CodeMzt
 */

public final class ToppahsMain extends JavaPlugin {
    //实例化本类
    public static final ToppahsMain INSTANCE = new ToppahsMain();
    //重写父类方法
    private ToppahsMain() {
        super(new JvmPluginDescriptionBuilder("indi.mzt.toppahs", "0.1.0")
                .info("EG")
                .build());
    }
    //插件启动
    @Override
    public void onEnable() {
        /*try {
            DealMain.deal();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        getLogger().info("日志");
        //群消息lambda
        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, g -> {
            //实例化一个插件home对象,进入功能监听
            PluginHome deal =new PluginHome(g);
        });
        GlobalEventChannel.INSTANCE.subscribeAlways(FriendMessageEvent.class, f -> {
            //监听好友消息
            PluginHome deal =new PluginHome(f);
        });
        GlobalEventChannel.INSTANCE.subscribeAlways(MemberLeaveEvent.class, p -> {
            //监听群成员离开事件
            PluginHome deal =new PluginHome(p);
        });
        GlobalEventChannel.INSTANCE.subscribeAlways(MemberJoinEvent.class, p -> {
            //监听群成员加入事件
            PluginHome deal =new PluginHome(p);
        });
    }
    //插件正常结束
    public void onDisable(){

    }
}