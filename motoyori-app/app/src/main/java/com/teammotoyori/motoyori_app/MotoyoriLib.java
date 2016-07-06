package com.teammotoyori.motoyori_app;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
/**
 * @author teamMOTOYORI
 * @version 0.01
 * Created by 1503015 on 2016/07/05.
 */
public class MotoyoriLib {
    /**
    インプットストリームからHashMapを作る関数
    separatorは','(カンマ)
    @return HashMap<String, String>
     */
    public static HashMap<String, String> openFile(InputStream input) throws IOException {
        HashMap<String, String> ret = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        while(true) {
            String line = br.readLine();
            if(line == null) {
                break;
            }
            String sepa = ",";
            if(line.contains(sepa)) {
                String[] keyValue = line.split(sepa, 2);
                String key = keyValue[0];
                if (!ret.containsKey(key)) {
                    String value = keyValue[1];
                    ret.put(key, value);
                }
            }
        }
        br.close();
        return ret;
    }
}