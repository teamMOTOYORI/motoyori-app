package com.teammotoyori.motoyori_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button addButton = (Button)findViewById(R.id.addbutton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText keyaddText = (EditText)findViewById(R.id.keyaddtext);
                EditText valueaddText = (EditText)findViewById(R.id.valueaddtext);
                String key = keyaddText.getText().toString();
                String value = valueaddText.getText().toString();
                try{
                        OutputStream out = openFileOutput("save.txt",MODE_APPEND);
                        PrintWriter writer =
                            new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
                        writer.append(key + "," + value + "\n");
                        writer.close();
                }catch(IOException e){
                        e.printStackTrace();
                }
            }
        });

        Button deleteButton = (Button)findViewById(R.id.deletebutton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    InputStream in = openFileInput("save.txt");
                        BufferedReader reader =
                            new BufferedReader(new InputStreamReader(in,"UTF-8"));
                        EditText keydeleteText = (EditText)findViewById(R.id.keydeletetext);
                        String deletekey = keydeleteText.getText().toString();
                        Map<String,String> map = new LinkedHashMap<String,String>();
                        StringBuilder sb = new StringBuilder();
                        String line;
                        String sepa = ",";
                        while((line = reader.readLine())!= null) {
                            if (line.contains(sepa)) {
                                String[] keyValue = line.split(sepa, 2);
                                String key = keyValue[0];
                                String value = keyValue[1];
                                map.put(key, key + "," + value);
                            }
                        }

                        map.remove(deletekey);

                    try{
                        OutputStream out = openFileOutput("save.txt",MODE_PRIVATE);
                        PrintWriter writer =
                                new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
                        for (String key : map.keySet()) {
                            writer.append(map.get(key) + "\n");
                        }
                        writer.close();
                        //out.flush();
                        //out.close();
                    }catch(IOException e){
                        e.printStackTrace();
                    }

                        reader.close();
                }catch(IOException e){
                        e.printStackTrace();
                }

            }
        });

        Button ichiranButton = (Button)findViewById(R.id.ichiranbutton);
        ichiranButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, IchiranActivity.class);
                startActivity(intent);
            }
        });

        Button debugButton = (Button)findViewById(R.id.debugbutton);
        debugButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OutputStream out = null;
                try{
                    out = openFileOutput("save.txt",MODE_PRIVATE);
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(out,"UTF-8"));
                    for(int i = 1; i < 50; i++) {
                        StringBuilder sb = new StringBuilder();
                        for(int j = 0; j < i; j++) {
                            sb.append("α");
                        }
                        writer.append(sb.toString() + ",test" + i +  "\n");
                    }
                    writer.close();
                    out.flush();
                    out.close();
                }catch(IOException e){
                    if(out != null) {
                        try {
                            out.close();
                        }catch (IOException ee) {
                        }
                    }
                    e.printStackTrace();
                }
            }
        });
    }
}
