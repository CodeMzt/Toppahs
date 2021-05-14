package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.plugin.annotations.Switch;
import net.mamoe.mirai.event.events.GroupMessageEvent;

@Switch("GroupAdmin")
public class GroupAdminPlugin {
    public boolean onGroupCommand(GroupMessageEvent g){
        String message=g.getMessage().contentToString().substring(1);
        if(message.startsWith("t")||message.startsWith("k")){
            Toppahs.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            return true;
        }
        if(message.startsWith("ban")){
            Toppahs.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            return true;
        }
        return false;
    }
}
