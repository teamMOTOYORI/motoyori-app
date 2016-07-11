package com.teammotoyori.motoyori_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IchiranActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ichiran);
        BufferedReader reader = null;
        try{
            InputStream in = openFileInput("a.txt");
            reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String s;
            while((s = reader.readLine())!= null){
            }
            reader.close();
        }catch(IOException e){
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException ee) {
                }
            }
            e.printStackTrace();
        }
    }
}
