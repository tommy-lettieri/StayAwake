package com.lettieri.thomas.stayawake.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lettieri.thomas.stayawake.R;

/**
 * This is the main activity for navigation to other activities
 */
public class MainActivity extends AppCompatActivity {
    // TODO add settings
    // TODO fix update interval
    // TODO clean up urls
    // TODO add locks

    private Button btnTest;

    /**
     * Initial logic for the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        addClickListeners();
    }

    /**
     * Get references to the inflated views
     */
    private void findViews() {
        btnTest = (Button)findViewById(R.id.btnTest);
    }

    /**
     * Add click listeners to the buttons
     */
    private void addClickListeners() {
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
    }
}
