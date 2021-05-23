package indi.mzt.toppahs.service;

import indi.mzt.toppahs.config.ServiceConfig;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import static java.net.URLEncoder.encode;

public class TranslateService {
    public static String translate(String text){
        String result="";
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(new URL(ServiceConfig.TRANSLATE+"?info="+encode(text,"utf-8")).openConnection().getInputStream(),"utf-8"));
            String lin=br.readLine();
            while(lin!=null){
                result+=lin;
                lin=br.readLine();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(result).getString("fanyi");
    }
}