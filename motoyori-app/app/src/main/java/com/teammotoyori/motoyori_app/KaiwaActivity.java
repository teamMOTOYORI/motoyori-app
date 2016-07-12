package com.teammotoyori.motoyori_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class KaiwaActivity extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    EditText editText;
    MotoyoriKun motoyori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaiwa);
        textView1 = (TextView) this.findViewById(R.id.textView1);
        textView2 = (TextView) this.findViewById(R.id.textView2);
        editText = (EditText) this.findViewById(R.id.editText);
        Button button1 = (Button) this.findViewById(R.id.button1);

        InputStream is = null;
        try {
            is = getAssets().open("test.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        motoyori = new MotoyoriKun(textView2, is);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                textView1.setText(s);
                motoyori.talk(s);
            }
        });
    }
}