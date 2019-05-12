package com.example.eatit;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relay1, relay2;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            relay1.setVisibility(View.VISIBLE);
            relay2.setVisibility(View.VISIBLE);
        }
    };

    private static final String TAG = MainActivity.class.getSimpleName();
    EditText name, pass;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relay1 = (RelativeLayout) findViewById(R.id.relay1);
        relay2 = (RelativeLayout) findViewById(R.id.relay2);
        handler.postDelayed(runnable, 2000);

        name = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        btn = (Button) findViewById(R.id.login);
        pass.setEnabled(false);
        btn.setEnabled(false);

        final TextWatcher nameWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pass.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    pass.setEnabled(false);
                } else {
                    pass.setEnabled(true);
                }
            }
        };

        final TextWatcher passWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btn.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    btn.setEnabled(false);
                } else {
                    btn.setEnabled(true);
                }
            }
        };
        name.addTextChangedListener(nameWatcher);
        pass.addTextChangedListener(passWatcher);


    }

    public void login(View v) {
        String u = name.getText().toString();
        String p = pass.getText().toString();

        try {
            if (u.equals("Rabia") && p.equals("123")) {
                Log.i("info", "SUCCESS");
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                i.putExtra("m1", "success Hello");

                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                startActivity(i);
            } else {




                    Intent i = new Intent(MainActivity.this, Main2Activity.class);
                    i.putExtra("m1", "login failed---wrong credential");
                    startActivity(i);

            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }


    }
}


