package indi.mzt.toppahs.service;

import indi.mzt.toppahs.service.WcaResultGet;

public class WcaService {
    public static String getResult(String person){
        return WcaResultGet.INSTANCE.getResult(WcaResultGet.INSTANCE.getId(person)).getResult();
    }
}
