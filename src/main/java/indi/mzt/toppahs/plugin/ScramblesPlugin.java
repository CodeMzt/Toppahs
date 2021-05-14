package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.plugin.annotations.Switch;

import net.mamoe.mirai.event.events.GroupMessageEvent;
import java.util.Arrays;

@Switch("Scrambles")
public class ScramblesPlugin {
    private final static String[] TYPE= {
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
            "pyram", "py",
            "skewb", "sk",
            "sq1", "sq",
            "minx","mi"
    };
    public boolean onGroupCommand(GroupMessageEvent g){
        String message=g.getMessage().contentToString().substring(1);
        if(Arrays.binarySearch(TYPE,message
                .substring(
                        0,
                        message.indexOf(' ')!=-1?message.indexOf(' '):message.length()
                )
        )>=0){
            Toppahs.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            return true;
        }
        return false;
    }
}
