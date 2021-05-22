package indi.mzt.toppahs.plugin;

import indi.mzt.toppahs.config.ServiceConfig;
import indi.mzt.toppahs.main.ToppahsMain;
import indi.mzt.toppahs.main.annotations.Plugin;
import indi.mzt.toppahs.plugin.annotations.Prefix;
import indi.mzt.toppahs.plugin.annotations.Switch;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;

@Plugin
@Switch("Competition")
public class CompetitionPlugin {
    public CompetitionPlugin(){PluginHome.eventListener(this);}
    @Prefix(".")
    public boolean onGroupCommand(GroupMessageEvent g) throws IOException {
        String message=g.getMessage().contentToString().substring(1);
        if(message.startsWith("近期赛事")){
            ToppahsMain.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            String result="";
            BufferedReader br=new BufferedReader(new InputStreamReader(new URL(ServiceConfig.CUBING+"/api/v0/competition").openConnection().getInputStream(),"utf-8"));
            String lin=br.readLine(),data="";
            while(lin!=null){
                data+=lin;
                lin=br.readLine();
            }
            br.close();
            JSONArray json=new JSONObject(data).getJSONArray("data");
            JSONObject jsonNow;
            int i=0;
            while(i<9){
                jsonNow=json.getJSONObject(i++);
                String from=new SimpleDateFormat("yyyy-MM-dd").format(jsonNow.getJSONObject("date").getInt("from")*1000L);
                String to=new SimpleDateFormat("yyyy-MM-dd").format(jsonNow.getJSONObject("date").getInt("to")*1000L);
                result+=i+"."+jsonNow.getString("name")+"  "+(from.equals(to)?from+'\n':from+"~"+to+'\n');
            }
            g.getGroup().sendMessage(result.substring(0,result.length()-1));
            return true;
        }
        if(message.startsWith("赛事")){
            ToppahsMain.INSTANCE.getLogger().info(this.getClass().getSimpleName());
            return true;
        }
        return false;
    }
}
