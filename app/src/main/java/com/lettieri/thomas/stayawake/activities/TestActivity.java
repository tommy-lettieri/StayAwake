package com.lettieri.thomas.stayawake.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lettieri.thomas.stayawake.R;
import com.lettieri.thomas.stayawake.StayAwakeManager;

/**
 * This Activity is meant for testing functionality of the StayAwakeManager
 */
public class TestActivity extends AppCompatActivity {
    private Button btnKeepAwakeOn;
    private Button btnKeepAwakeOff;
    private Button btnUpdate;
    private TextView txtStatus;
    private LinearLayout layoutStatus;
    private TextView txtUpdateCount;

    private static int updateCount = 0;


    /**
     * Initial logic for the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViews();
        addClickListeners();
        updateStatus();
    }

    /**
     * Get references for the inflated view
     */
    private void findViews() {
        btnKeepAwakeOn = findViewById(R.id.btnKeepAwakeOn);
        btnKeepAwakeOff = findViewById(R.id.btnKeepAwakeOff);
        btnUpdate = findViewById(R.id.btnUpdate);
        txtStatus = findViewById(R.id.txtStatus);
        layoutStatus = findViewById(R.id.layoutStatus);
        txtUpdateCount = findViewById(R.id.txtUpdateCount);
    }

    /**
     * Update the status views to portray the information about the lock
     */
    private void updateStatus() {
        updateCount++;
        
        int color;
        String statusText;
        if(StayAwakeManager.isWakeLocked()) {
            color = Color.GREEN;
            statusText = "LOCKED";
        } else {
            color = Color.RED;
            statusText = "UNLOCKED";
        }
        layoutStatus.setBackgroundColor(color);
        txtStatus.setText(statusText);
        txtUpdateCount.setText("Updated: " + updateCount);
    }

    /**
     * Add click events to the buttons
     */
    private void addClickListeners() {
        btnKeepAwakeOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StayAwakeManager.turnOnKeepAwake(TestActivity.this);
                updateStatus();
            }
        });

        btnKeepAwakeOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StayAwakeManager.turnOffKeepAwake();
                updateStatus();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStatus();
            }
        });
    }
}
