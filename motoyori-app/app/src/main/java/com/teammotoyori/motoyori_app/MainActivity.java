package com.teammotoyori.motoyori_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    HashMap<String, String> dic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView TextView1 = (TextView) this.findViewById(R.id.TextView1);
        TextView TextView2 = (TextView) this.findViewById(R.id.TextView2);
        EditText editText = (EditText) this.findViewById(R.id.editText);
        Button button1 = (Button) this.findViewById(R.id.button1);

        HashMap<String,String> map = new HashMap<String,String>();

        try {
            dic = MotoyoriLib.openFile(getAssets().open("aa"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
}
