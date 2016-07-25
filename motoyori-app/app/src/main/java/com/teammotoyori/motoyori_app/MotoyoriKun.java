package com.teammotoyori.motoyori_app;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by 1503015 on 2016/07/08.
 */
public class MotoyoriKun {

    private HashMap<String, String> dic;    //  もとよりくん辞書データ
    private TextView textView;             //  もとよりくんの会話のテキストビュー
    private final String other = "知らないねえ";

    /**
     * @param tv もとよりくんが話す場所(TextView)
     * @param input もとよりくん辞書データのInputStream
     */
    public MotoyoriKun(TextView tv, InputStream input, String begin) {
        this.textView = tv;
        dic = openFile(input);
        tv.setText(begin);
    }
    /**
     インプットストリームからHashMapを作る関数
     separatorは','(カンマ)
     @return HashMap<String, String>
     */
    private HashMap<String, String> openFile(InputStream input) {
        HashMap<String, String> ret = new HashMap<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(input));
            String sepa = ",";
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                if (line.contains(sepa)) {
                    String[] keyValue = line.split(sepa, 2);
                    String key = keyValue[0];
                    if (!ret.containsKey(key)) {
                        String value = keyValue[1];
                        ret.put(key, value);
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            if (br == null) {
                try {
                    br.close();
                } catch (IOException ee) {
                }
            }
            e.printStackTrace();
        }
        return ret;
    }

    public void talk(String s) {
        boolean flag = false;
        int maxCount = 0;
        String talk = "";
        for(String key : dic.keySet()) {
            if(s.contains(key)) {
                String ss = dic.get(key);
                if(maxCount < key.length()) {
                    maxCount = key.length();
                    talk = ss;
                }
                flag = true;
            }
        }
        if(flag) {
            textView.setText(talk);
        } else {
            textView.setText(other);
        }
    }
}