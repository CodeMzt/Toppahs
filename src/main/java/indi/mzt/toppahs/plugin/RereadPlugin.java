package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.main.annotations.Plugin;
import indi.mzt.toppahs.plugin.annotations.Prefix;
import indi.mzt.toppahs.plugin.annotations.Switch;

import net.mamoe.mirai.event.events.GroupMessageEvent;

@Plugin
@Switch("Reread")
public class RereadPlugin {
    public RereadPlugin(){PluginHome.eventListener(this);}
    @Prefix(".")
    public void onGroupCommand(GroupMessageEvent g){

    }
}
