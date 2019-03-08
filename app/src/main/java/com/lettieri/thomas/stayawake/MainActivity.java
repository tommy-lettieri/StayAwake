package com.lettieri.thomas.stayawake;

import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.locks.Lock;

public class MainActivity extends AppCompatActivity {
    private Button btnKeepAwakeOn;
    private Button btnKeepAwakeOff;

    private PowerManager.WakeLock wakeLock = null;

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
                turnOnKeepAwake();
            }
        });

        btnKeepAwakeOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnOffKeepAwake();
            }
        });
    }

    private boolean turnOnKeepAwake() {
        // TODO there should be a lock that locks at the beginning and end of this method and the other method (same lock)
        if(wakeLock != null) {
            return false;
        }
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
                "StayAwake::WakeLockTag");
        wakeLock.acquire();
        return true;
    }

    private boolean turnOffKeepAwake() {
        // TODO there should be a lock that locks at the beginning and end of this method and the other method (same lock)
        if(wakeLock == null) {
            return false;
        }
        wakeLock.release();
        wakeLock = null;
        return true;
    }
}
