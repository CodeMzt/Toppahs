package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.main.annotations.Plugin;
import indi.mzt.toppahs.plugin.annotations.Prefix;
import indi.mzt.toppahs.plugin.annotations.Switch;
import indi.mzt.toppahs.main.ToppahsMain;

import net.mamoe.mirai.event.events.GroupMessageEvent;

@Plugin
@Switch("Switches")
public class SwitchesPlugin {
    public SwitchesPlugin(){PluginHome.eventListener(this);}
    @Prefix(".")
    public void onGroupCommand(GroupMessageEvent g){
        String message=g.getMessage().contentToString().substring(1);
        if(message.startsWith("启用")){
            ToppahsMain.INSTANCE.getLogger().info(this.getClass().getSimpleName());

        }
        if(message.startsWith("停用")){
            ToppahsMain.INSTANCE.getLogger().info(this.getClass().getSimpleName());

        }

    }
}
