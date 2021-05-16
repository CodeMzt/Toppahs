package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.main.annotations.Plugin;
import indi.mzt.toppahs.plugin.annotations.Prefix;
import indi.mzt.toppahs.plugin.annotations.Switch;

import net.mamoe.mirai.event.events.MemberJoinEvent;

@Plugin
@Switch("Welcome")
public class WelcomePlugin {
    public WelcomePlugin(){PluginHome.eventListener(this);}
    @Prefix(".")
    public boolean onMemberJoinEvent(MemberJoinEvent g){
        return false;
    }
}
