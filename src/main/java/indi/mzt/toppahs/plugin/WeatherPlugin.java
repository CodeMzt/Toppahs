package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.main.annotations.Plugin;
import indi.mzt.toppahs.plugin.annotations.Prefix;
import indi.mzt.toppahs.plugin.annotations.Switch;
import indi.mzt.toppahs.main.ToppahsMain;

import net.mamoe.mirai.event.events.GroupMessageEvent;

@Plugin
@Switch("Weather")
public class WeatherPlugin {
    public WeatherPlugin(){PluginHome.eventListener(this);}
    @Prefix(".")
    public boolean onGroupCommand(GroupMessageEvent g){
        String message=g.getMessage().contentToString().substring(1);
        if(message.startsWith("天气")){
            ToppahsMain.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            return true;
        }
        return false;
    }
}
