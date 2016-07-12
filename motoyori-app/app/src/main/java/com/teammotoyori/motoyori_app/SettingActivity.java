package com.teammotoyori.motoyori_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

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
