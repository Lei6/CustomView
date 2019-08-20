package com.demo.customview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.customview.music.MusicActivity;
import com.demo.customview.sort.SideActivity;
import com.demo.customview.toggle.ToggleButtonActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPlay;
    private Button btnToggle;
    private Button btnSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnPlay = (Button) findViewById(R.id.btn_play);
        btnToggle = (Button) findViewById(R.id.btn_toggle);
        btnSort = findViewById(R.id.btn_sort);
        btnSort.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnToggle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_play:
                intent = new Intent(this, MusicActivity.class);
                break;
            case R.id.btn_toggle:
                intent = new Intent(this, ToggleButtonActivity.class);
                break;
            case R.id.btn_sort:
                intent = new Intent(this, SideActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
