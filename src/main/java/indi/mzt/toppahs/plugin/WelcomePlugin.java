package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.plugin.annotations.Switch;
import net.mamoe.mirai.event.events.MemberJoinEvent;

@Switch("Welcome")
public class WelcomePlugin {
    public boolean onMemberJoinEvent(MemberJoinEvent g){
        return false;
    }
}
