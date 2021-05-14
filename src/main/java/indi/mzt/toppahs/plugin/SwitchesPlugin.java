package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.plugin.annotations.Switch;
import net.mamoe.mirai.event.events.GroupMessageEvent;

@Switch("Switches")
public class SwitchesPlugin {
    public boolean onGroupCommand(GroupMessageEvent g){
        String message=g.getMessage().contentToString().substring(1);
        if(message.startsWith("启用")){
            Toppahs.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            return true;
        }
        if(message.startsWith("停用")){
            Toppahs.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            return true;
        }
        return false;
    }
}
