package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.main.annotations.Plugin;
import indi.mzt.toppahs.plugin.annotations.Prefix;
import indi.mzt.toppahs.plugin.annotations.Switch;
import indi.mzt.toppahs.main.ToppahsMain;

import net.mamoe.mirai.event.events.GroupMessageEvent;

@Plugin
@Switch("GroupAdmin")
public class GroupAdminPlugin {
    public GroupAdminPlugin(){PluginHome.eventListener(this);}
    @Prefix(".")
    public boolean onGroupCommand(GroupMessageEvent g){
        String message=g.getMessage().contentToString().substring(1);
        if(message.startsWith("t")||message.startsWith("k")){
            ToppahsMain.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            return true;
        }
        if(message.startsWith("ban")){
            ToppahsMain.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            return true;
        }
        return false;
    }
}
