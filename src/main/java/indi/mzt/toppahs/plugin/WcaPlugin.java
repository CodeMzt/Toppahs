package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.main.annotations.Plugin;
import indi.mzt.toppahs.plugin.annotations.Prefix;
import indi.mzt.toppahs.plugin.annotations.Switch;
import indi.mzt.toppahs.main.ToppahsMain;

import indi.mzt.toppahs.service.WcaService;
import net.mamoe.mirai.event.events.GroupMessageEvent;

@Plugin
@Switch("Wca")
public class WcaPlugin {
    public WcaPlugin(){PluginHome.eventListener(this);}
    @Prefix(".")
    public void onGroupCommand(GroupMessageEvent g){
        String message=g.getMessage().contentToString().substring(1);
        if(message.startsWith("wca")){
            ToppahsMain.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            String name=message.substring(3);
            g.getGroup().sendMessage(WcaService.getResult(name));
        }

    }
}
