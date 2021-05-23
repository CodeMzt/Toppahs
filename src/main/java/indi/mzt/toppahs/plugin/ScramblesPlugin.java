package indi.mzt.toppahs.plugin;
import indi.mzt.toppahs.main.annotations.Plugin;
import indi.mzt.toppahs.plugin.annotations.Prefix;
import indi.mzt.toppahs.plugin.annotations.Switch;
import indi.mzt.toppahs.main.ToppahsMain;
import indi.mzt.toppahs.service.ScramblesService;

import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.EmptyMessageChain;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.utils.ExternalResource;

@Plugin
@Switch("Scrambles")
public class ScramblesPlugin {
    public ScramblesPlugin(){PluginHome.eventListener(this);}
    private static final String[] TYPE= {
            "222", "2",
            "333", "3",
            "444", "4",
            "555", "5",
            "666", "6",
            "777", "7",
            "888", "8",
            "999", "9",
            "101010", "10",
            "111111", "11",
            "121212", "12",
            "131313", "13",
            "141414", "14",
            "151515", "15",
            "161616", "16",
            "171717", "17",
            "444fast","4fast",
            "333ni","3ni",
            "444ni","4ni",
            "555ni","5ni",
            "333fm","3fm",
            "pyram", "py",
            "sq1", "sq",
            "minx","mi",
            "clock","cl",
            "skewb", "sk"
    };
    private static final ScramblesService[] TYPE2= {
            ScramblesService.TWO,
            ScramblesService.THREE,
            ScramblesService.FOUR,
            ScramblesService.FIVE,
            ScramblesService.SIX,
            ScramblesService.SEVEN,
            ScramblesService.EIGHT,
            ScramblesService.NINE,
            ScramblesService.TEN,
            ScramblesService.ELEVEN,
            ScramblesService.TWELVE,
            ScramblesService.THIRTEEN,
            ScramblesService.FOURTEEN,
            ScramblesService.FIFTEEN,
            ScramblesService.SIXTEEN,
            ScramblesService.SEVENTEEN,
            ScramblesService.FOUR_FAST,
            ScramblesService.THREE_NI,
            ScramblesService.FOUR_NI,
            ScramblesService.FIVE_NI,
            ScramblesService.THREE_FM,
            ScramblesService.PYRA,
            ScramblesService.SQ1,
            ScramblesService.MEGA,
            ScramblesService.CLOCK,
            ScramblesService.SKEWB
    };
    @Prefix(".")
    public void onGroupCommand(GroupMessageEvent g){
        ToppahsMain mir=ToppahsMain.INSTANCE;
        String message=g.getMessage().contentToString().substring(1);
        String type=message
                .substring(
                        0,
                        message.indexOf(' ')!=-1?message.indexOf(' '):message.length()
                );
        int p=-1;
        for(int i=0;i<TYPE.length;i++){
            if(TYPE[i].equals(type))p=i;
        }
        System.out.println(p);
        if(p>=0&&p<TYPE.length){
            mir.getLogger().info(this.getClass().getSimpleName());
            MessageChain result= EmptyMessageChain.INSTANCE;
            ScramblesService scramble= TYPE2[p/2];
            result=result.plus(scramble.getScramble());
            scramble.drawScramble();
            result=result.plus(g.getGroup().uploadImage(ExternalResource.create(scramble.getPuzzleImage())));
            g.getGroup().sendMessage(result);

        }

    }
}
