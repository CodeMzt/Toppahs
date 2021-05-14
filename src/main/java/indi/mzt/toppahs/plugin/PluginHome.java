package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.plugin.annotations.*;
import net.mamoe.mirai.event.events.*;

public class PluginHome {
    //这里集合了所有功能，需要时只需添加相应前缀与功能名，再转到对应的实现方法
    
    @Switch("Auth")
    @Prefix(".")
    public boolean authPlugin(GroupMessageEvent g){
        return new AuthPlugin().onGroupCommand(g);
    }
    @Switch("Competition")
    @Prefix(".")
    public boolean competitionPlugin(GroupMessageEvent g){
        return new CompetitionPlugin().onGroupCommand(g);
    }
    @Switch("Express")
    @Prefix(".")
    public boolean expressPlugin(GroupMessageEvent g){ return new ExpressPlugin().onGroupCommand(g); }
    @Switch("GroupAdmin")
    @Prefix(".")
    public boolean groupAdminPlugin(GroupMessageEvent g){ return new GroupAdminPlugin().onGroupCommand(g); }
    @Switch("Leave")
    public boolean leavePlugin(MemberLeaveEvent g){ return new LeavePlugin().onMemberLeavingEvent(g); }
    @Switch("Reread")
    public boolean rereadPlugin(GroupMessageEvent g){ return new RereadPlugin().onGroupCommand(g); }
    @Switch("Scrambles")
    @Prefix(".")
    public boolean scramblesPlugin(GroupMessageEvent g){ return new ScramblesPlugin().onGroupCommand(g); }
    @Switch("Switches")
    @Prefix(".")
    public boolean switchesPlugin(GroupMessageEvent g){ return new SwitchesPlugin().onGroupCommand(g); }
    @Switch("Translate")
    @Prefix(".")
    public boolean translatePlugin(GroupMessageEvent g){ return new TranslatePlugin().onGroupCommand(g); }
    @Switch("Wca")
    @Prefix(".")
    public boolean wcaPlugin(GroupMessageEvent g){ return new WcaPlugin().onGroupCommand(g); }
    @Switch("Weather")
    @Prefix(".")
    public boolean weatherPlugin(GroupMessageEvent g){ return new WeatherPlugin().onGroupCommand(g); }
    @Switch("Welcome")
    public boolean welcomePlugin(MemberJoinEvent g){ return new WelcomePlugin().onMemberJoinEvent(g); }
}
