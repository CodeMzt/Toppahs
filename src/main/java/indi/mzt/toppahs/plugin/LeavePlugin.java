package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.main.annotations.Plugin;
import indi.mzt.toppahs.plugin.annotations.Prefix;
import indi.mzt.toppahs.plugin.annotations.Switch;

import net.mamoe.mirai.event.events.MemberLeaveEvent;

@Plugin
@Switch("Leave")
public class LeavePlugin {
    public LeavePlugin(){PluginHome.eventListener(this);}
    @Prefix(".")
    public boolean onMemberLeavingEvent(MemberLeaveEvent g){
        return false;
    }
}
