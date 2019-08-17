package com.demo.customview.toggle;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.customview.R;

public class ToggleButtonActivity extends AppCompatActivity {

    private ToggleButton btnToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle_button);
        initView();
    }

    private void initView() {
        btnToggle = (ToggleButton) findViewById(R.id.btn_toggle);
        btnToggle.setOnToggleListener(new IToggleView.OnToggleListener() {
            @Override
            public void onToggle(boolean isOpen) {
                Toast.makeText(ToggleButtonActivity.this, ""+isOpen, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
