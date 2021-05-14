package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.plugin.annotations.Switch;
import net.mamoe.mirai.event.events.GroupMessageEvent;

@Switch("Competition")
public class CompetitionPlugin {
    public boolean onGroupCommand(GroupMessageEvent g){
        String message=g.getMessage().contentToString().substring(1);
        if(message.startsWith("近期赛事")){
            JavaPluginMain.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            return true;
        }
        if(message.startsWith("赛事")){
            JavaPluginMain.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            return true;
        }
        return false;
    }
}
