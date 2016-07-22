package com.teammotoyori.motoyori_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
                if(!key.contains(",")){

                    String value = valueaddText.getText().toString();
                    try{
                            OutputStream out = openFileOutput("save.txt",MODE_APPEND);
                            PrintWriter writer =
                                new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
                            writer.append(key + "," + value + "\n");
                            writer.close();
                            Toast.makeText(v.getContext(), "Key: " + key + "\n" + "Value: " + value + "\nを追加しました"   ,Toast.LENGTH_SHORT).show();
                    }catch(IOException e){
                            e.printStackTrace();
                    }
                } else{
                    Toast.makeText(v.getContext(), "' が含まれています。" ,Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button deleteButton = (Button)findViewById(R.id.deletebutton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream in = null;
                Map<String, String> map = new LinkedHashMap<>();
                try {
                    in = openFileInput("save.txt");
                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    String line;
                    String sepa = ",";
                    while ((line = reader.readLine()) != null) {
                        if (line.contains(sepa)) {
                            String[] keyValue = line.split(sepa, 2);
                            String key = keyValue[0];
                            String value = keyValue[1];
                            map.put(key, key + "," + value);
                        }
                    }
                    in.close();
                } catch (IOException e) {
                    if(in != null) {
                        try {
                            in.close();
                        } catch (IOException ee) {
                        }
                    }
                }

                EditText keydeleteText = (EditText) findViewById(R.id.keydeletetext);
                String deletekey = keydeleteText.getText().toString();
                if (map.containsKey(deletekey)) {
                    map.remove(deletekey);
                    OutputStream out = null;
                    try{
                        out = openFileOutput("save.txt",MODE_PRIVATE);
                        PrintWriter writer =
                                new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
                        for (String key : map.keySet()) {
                            writer.append(map.get(key) + "\n");
                        }
                        Toast.makeText(v.getContext(), "Key: " + deletekey + "を削除しました。" ,Toast.LENGTH_SHORT).show();
                        writer.close();
                        out.flush();
                        out.close();
                    }catch(IOException e){
                        if(out != null) {
                            try {
                                out.close();
                            } catch (IOException ee) {
                            }
                        }
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(v.getContext(), "Key: " + deletekey + "は含まれていません。", Toast.LENGTH_SHORT).show();
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

        Button defaultButton = (Button)findViewById(R.id.defaultbutton);
        defaultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream in = null;
                OutputStream out = null;
                try{
                    in = getAssets().open("test.csv");
                    out = openFileOutput("save.txt",MODE_PRIVATE);
                    PrintWriter writer =
                            new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        writer.append(line.toString() + "\n");
                    }
                    in.close();
                    writer.close();
                    out.flush();
                    out.close();
                }catch(IOException e){
                    if(in != null) {
                        try {
                            in.close();
                        }catch (IOException ee) {
                        }
                    }
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
