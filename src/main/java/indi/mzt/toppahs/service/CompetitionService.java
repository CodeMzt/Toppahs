package indi.mzt.toppahs.service;

import indi.mzt.toppahs.config.ServiceConfig;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;

public class CompetitionService{
    public static String competition(){
        String result="";
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(new URL(ServiceConfig.CUBING+"/api/v0/competition").openConnection().getInputStream(),"utf-8"));
            String data = "";
            String lin = br.readLine();
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
                result+=i+"."+jsonNow.getString("name")+"  "+(from.equals(to)?from+'\n':from+" to "+to+'\n');
            }
        } catch (Exception e) { }
        return result.substring(0,result.length()-1);
    }
}
