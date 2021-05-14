package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.plugin.annotations.Switch;
import net.mamoe.mirai.event.events.GroupMessageEvent;

@Switch("Wca")
public class WcaPlugin {
    public boolean onGroupCommand(GroupMessageEvent g){
        String message=g.getMessage().contentToString().substring(1);
        if(message.startsWith("wca")){
            Toppahs.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            return true;
        }
        return false;
    }
}
