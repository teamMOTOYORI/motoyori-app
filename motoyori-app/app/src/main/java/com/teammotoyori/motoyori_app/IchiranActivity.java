package com.teammotoyori.motoyori_app;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class IchiranActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ichiran);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager glManager = new GridLayoutManager(this, 2);
        //  縦スクロール
        glManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(glManager);

        ArrayList<KeyValueData> data = new ArrayList<>();
        BufferedReader reader = null;
        try{
            InputStream in = openFileInput("save.txt");
            reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String line;
            String sepa = ",";
            while((line = reader.readLine())!= null){
                if(line.contains(sepa)) {
                    String[] keyValue = line.split(sepa, 2);
                    String key = keyValue[0];
                    String value = keyValue[1];
                    data.add(new KeyValueData(key, value));
                }
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

        RecyclerView.Adapter adapter = new KeyValueAdapter(data);
        recyclerView.setAdapter(adapter);
    }
}
