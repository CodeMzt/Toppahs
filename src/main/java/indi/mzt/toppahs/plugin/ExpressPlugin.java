package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.main.annotations.Plugin;
import indi.mzt.toppahs.plugin.annotations.Prefix;
import indi.mzt.toppahs.plugin.annotations.Switch;
import indi.mzt.toppahs.main.ToppahsMain;

import indi.mzt.toppahs.service.ExpressService;
import net.mamoe.mirai.event.events.GroupMessageEvent;

@Plugin
@Switch("Express")
public class ExpressPlugin {
    public ExpressPlugin(){PluginHome.eventListener(this);}
    @Prefix(".")
    public void onGroupCommand(GroupMessageEvent g){
        String message=g.getMessage().contentToString().substring(1);
        if(message.startsWith("查快递")){
            ToppahsMain.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            g.getGroup().sendMessage(ExpressService.express(message.substring(3,message.length())));
        }

    }
}
