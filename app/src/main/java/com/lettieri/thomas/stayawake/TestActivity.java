package com.lettieri.thomas.stayawake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This Activity is meant for testing functionality of the StayAwakeManager
 */
public class TestActivity extends AppCompatActivity {
    private Button btnKeepAwakeOn;
    private Button btnKeepAwakeOff;

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
    }

    /**
     * Get references for the inflated view
     */
    private void findViews() {
        btnKeepAwakeOn = (Button)findViewById(R.id.btnKeepAwakeOn);
        btnKeepAwakeOff = (Button)findViewById(R.id.btnKeepAwakeOff);
    }

    /**
     * Add click events to the buttons
     */
    private void addClickListeners() {
        btnKeepAwakeOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StayAwakeManager.turnOnKeepAwake(TestActivity.this);
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
