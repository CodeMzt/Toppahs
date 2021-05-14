package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.plugin.annotations.Switch;
import net.mamoe.mirai.event.events.MemberLeaveEvent;

@Switch("Leave")
public class LeavePlugin {
    public boolean onMemberLeavingEvent(MemberLeaveEvent g){
        return false;
    }
}
