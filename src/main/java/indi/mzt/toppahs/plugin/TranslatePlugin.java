package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.main.annotations.Plugin;
import indi.mzt.toppahs.plugin.annotations.Prefix;
import indi.mzt.toppahs.plugin.annotations.Switch;
import indi.mzt.toppahs.main.ToppahsMain;

import indi.mzt.toppahs.service.TranslateService;
import net.mamoe.mirai.event.events.GroupMessageEvent;

@Plugin
@Switch("Translate")
public class TranslatePlugin {
    public TranslatePlugin(){PluginHome.eventListener(this);}
    @Prefix(".")
    public void onGroupCommand(GroupMessageEvent g){
        String message=g.getMessage().contentToString().substring(1);
        if(message.startsWith("翻译")){
            ToppahsMain.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            g.getGroup().sendMessage(TranslateService.translate(message.substring(2,message.length())));
        }

    }
}
