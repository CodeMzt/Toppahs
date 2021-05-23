package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.main.ToppahsMain;

import indi.mzt.toppahs.main.annotations.Plugin;
import indi.mzt.toppahs.plugin.annotations.Prefix;
import indi.mzt.toppahs.plugin.annotations.Switch;
import net.mamoe.mirai.event.events.GroupMessageEvent;

@Plugin
@Switch("Auth")
public class AuthPlugin {
    //注册监听
    public AuthPlugin(){PluginHome.eventListener(this);}
    @Prefix(".")
    public void onGroupCommand(GroupMessageEvent g){
        String message=g.getMessage().contentToString().substring(1);
        if(message.startsWith("授权+")){
            ToppahsMain.INSTANCE.getLogger().info(this.getClass().getSimpleName());

        }
        if(message.startsWith("授权-")){
            ToppahsMain.INSTANCE.getLogger().info(this.getClass().getSimpleName());

        }

    }
}
