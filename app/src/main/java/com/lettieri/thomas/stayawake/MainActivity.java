package com.lettieri.thomas.stayawake;

import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.locks.Lock;

public class MainActivity extends AppCompatActivity {
    // TODO add settings
    // TODO fix update interval
    // TODO clean up urls
    // TODO add locks
    
    private Button btnKeepAwakeOn;
    private Button btnKeepAwakeOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        addClickListeners();
    }

    private void findViews() {
        btnKeepAwakeOn = (Button)findViewById(R.id.btnKeepAwakeOn);
        btnKeepAwakeOff = (Button)findViewById(R.id.btnKeepAwakeOff);
    }

    private void addClickListeners() {
        btnKeepAwakeOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StayAwakeManager.turnOnKeepAwake(MainActivity.this);
            }
        });

        btnKeepAwakeOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StayAwakeManager.turnOffKeepAwake();
            }
        });
    }
}
