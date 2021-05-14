package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.plugin.annotations.Switch;
import net.mamoe.mirai.event.events.GroupMessageEvent;

@Switch("Reread")
public class RereadPlugin {
    public boolean onGroupCommand(GroupMessageEvent g){
        return false;
    }
}
