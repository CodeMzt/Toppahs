package indi.mzt.toppahs.service;

import indi.mzt.toppahs.config.ServiceConfig;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static java.net.URLEncoder.encode;

public class WeatherService {
    public static String weather(String city){
        String result="";
        try {
            String js="";
            BufferedReader br=new BufferedReader(new InputStreamReader(new URL(ServiceConfig.WEATHER+"?location="+encode(city,"utf-8")).openConnection().getInputStream(),"utf-8"));
            String lin=br.readLine();
            while(lin!=null){
                js+=lin;
                lin=br.readLine();
            }
            JSONArray json=new JSONObject(js).getJSONArray("data");
            int i=0;
            result+=city+'\n';
            while(i<=6){
                result+=json.getJSONObject(i)
                        .getString("days")+' '+
                        json.getJSONObject(i)
                                .getString("week")+'\n'+ "气温 "+
                        json.getJSONObject(i)
                                .getString("temperature")+' '+
                        json.getJSONObject(i)
                                .getString("weather")+'\n'+
                        json.getJSONObject(i)
                                .getString("wind")+'\n';
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.substring(0,result.length()-1);
    }
}
