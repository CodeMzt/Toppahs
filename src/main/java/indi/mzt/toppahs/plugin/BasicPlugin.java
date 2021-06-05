package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.config.ToppahsConfig;
import indi.mzt.toppahs.main.ToppahsMain;
import indi.mzt.toppahs.plugin.annotations.Prefix;
import net.mamoe.mirai.event.events.GroupMessageEvent;

public class BasicPlugin {
    public BasicPlugin(){PluginHome.eventListener(this);}
    @Prefix(".")
    public void onGroupCommand(GroupMessageEvent g) {
        String message = g.getMessage().contentToString().substring(1);
        if (message.startsWith("使用说明"))
            g.getGroup().sendMessage(ToppahsConfig.USE);
        if (message.startsWith("eg0")||message.startsWith("cll"))
            g.getGroup().sendMessage(ToppahsConfig.CLL);
        if(message.startsWith("eg1"))
            g.getGroup().sendMessage(ToppahsConfig.EG1);
        if(message.startsWith("eg2"))
            g.getGroup().sendMessage(ToppahsConfig.EG2);
        if(message.startsWith("ortegaoll")||message.startsWith("ortega  oll"))
            g.getGroup().sendMessage(ToppahsConfig.ORTEGAOLL);
        if(message.startsWith("ortegapbl")||message.startsWith("ortega  pbl"))
            g.getGroup().sendMessage(ToppahsConfig.ORTEGAPBL);
        if(message.startsWith("f2l")||message.startsWith("F2L"))
            g.getGroup().sendMessage(ToppahsConfig.F2L);
        if(message.startsWith("oll")||message.startsWith("OLL"))
            g.getGroup().sendMessage(ToppahsConfig.OLL);
        if(message.startsWith("pll")||message.startsWith("PLL"))
            g.getGroup().sendMessage(ToppahsConfig.PLL);
        if(message.startsWith("coll")||message.startsWith("COLL"))
            g.getGroup().sendMessage(ToppahsConfig.COLL);
        if(message.startsWith("zbll")||message.startsWith("ZBLL"))
            g.getGroup().sendMessage(ToppahsConfig.ZBLL);
        if(message.startsWith("ollcp")||message.startsWith("OLLCP"))
            g.getGroup().sendMessage(ToppahsConfig.OLLCP);
        if(message.startsWith("ell")||message.startsWith("ELL"))
            g.getGroup().sendMessage(ToppahsConfig.ELL);
        if(message.startsWith("vls")||message.startsWith("VLS"))
            g.getGroup().sendMessage(ToppahsConfig.VLS);
        if(message.startsWith("wv")||message.startsWith("WV"))
            g.getGroup().sendMessage(ToppahsConfig.WV);
        if(message.startsWith("ep")||message.startsWith("EP"))
            g.getGroup().sendMessage(ToppahsConfig.EP);

    }

}
