package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.plugin.annotations.*;
import net.mamoe.mirai.event.events.GroupMessageEvent;

@Switch("Auth")
public class AuthPlugin {
    public boolean onGroupCommand(GroupMessageEvent g){
        String message=g.getMessage().contentToString().substring(1);
        if(message.startsWith("授权+")){
            Toppahs.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            return true;
        }
        if(message.startsWith("授权-")){
            Toppahs.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            return true;
        }
        return false;
    }
}
