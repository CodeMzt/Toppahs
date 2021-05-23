package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.main.ToppahsMain;
import indi.mzt.toppahs.main.annotations.Plugin;
import indi.mzt.toppahs.plugin.annotations.*;

import indi.mzt.toppahs.service.CompetitionService;
import net.mamoe.mirai.event.events.GroupMessageEvent;

@Plugin
@Switch("Competition")
public class CompetitionPlugin {
    public CompetitionPlugin(){PluginHome.eventListener(this);}
    @Prefix(".")
    public void onGroupCommand(GroupMessageEvent g){
        String message=g.getMessage().contentToString().substring(1);
        if(message.startsWith("近期赛事")) {
            ToppahsMain.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            g.getGroup().sendMessage(CompetitionService.competition());
        }
        if(message.startsWith("赛事")){
            ToppahsMain.INSTANCE.getLogger().info(this.getClass().getSimpleName());
        }

    }
}
