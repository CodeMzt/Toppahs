package indi.mzt.toppahs.service;

import indi.mzt.toppahs.config.ServiceConfig;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;

public class ExpressService {
    public static String express(String number){
        String result="";
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(new URL(ServiceConfig.EXPRESS+"/?number="+number).openConnection().getInputStream(),"utf-8"));
            String data = "";
            String lin = br.readLine();
            while(lin!=null){
                data+=lin;
                lin=br.readLine();
            }
            br.close();
            JSONObject json=new JSONObject(data);
            if(json.getString("msg").equals("success")){
                json=json.getJSONObject("data");
                result+="快递单号: "+number+" 物流公司："+json.getString("com")+'\n';
                JSONArray ja=json.getJSONArray("info");
                int i=0;
                while(ja.length()>i){
                    JSONObject jsonNow=ja.getJSONObject(i++);
                    result+=jsonNow.getString("time")+' '+jsonNow.getString("content")+'\n';
                }
            }
        } catch (Exception e) {e.printStackTrace(); }
        return result.substring(0,result.length()-1);
    }
}
