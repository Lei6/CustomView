package com.demo.customview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.customview.logisticstraces.LogisticsActivity;
import com.demo.customview.loopviewpage.BannerActivity;
import com.demo.customview.music.MusicActivity;
import com.demo.customview.slide.Slide2Activity;
import com.demo.customview.slidingdrawer.PoiActivity;
import com.demo.customview.sort.SideActivity;
import com.demo.customview.toggle.ToggleButtonActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPlay;
    private Button btnToggle;
    private Button btnSort;
    private Button btnPoi;
    private Button btnSide;
    private Button btnLogistics;
    private Button btnBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnPlay = (Button) findViewById(R.id.btn_play);
        btnToggle = (Button) findViewById(R.id.btn_toggle);
        btnSort = (Button)findViewById(R.id.btn_sort);
        btnPoi = (Button)findViewById(R.id.btn_poi);
        btnSide = findViewById(R.id.btn_silde);
        btnLogistics = findViewById(R.id.btn_logistics);
        btnBanner = findViewById(R.id.btn_banner);
        btnBanner.setOnClickListener(this);
        btnLogistics.setOnClickListener(this);
        btnSide.setOnClickListener(this);
        btnPoi.setOnClickListener(this);
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
            case R.id.btn_poi:
                intent = new Intent(this, PoiActivity.class);
                break;
            case R.id.btn_silde:
                intent = new Intent(this, Slide2Activity.class);
                break;
            case R.id.btn_logistics:
                intent = new Intent(this, LogisticsActivity.class);
                break;
            case R.id.btn_banner:
                intent = new Intent(this, BannerActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
