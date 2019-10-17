package com.demo.customview;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.demo.customview.foldedtext.FoldedTextActivity;
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
    private Button btnSilde;
    private Button btnLogistics;
    private Button btnBanner;
    private Button btnFoldText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnPlay = (Button) findViewById(R.id.btn_play);
        btnToggle = (Button) findViewById(R.id.btn_toggle);
        btnSort = (Button) findViewById(R.id.btn_sort);
        btnPoi = (Button) findViewById(R.id.btn_poi);
        btnSilde = (Button) findViewById(R.id.btn_silde);
        btnLogistics = (Button) findViewById(R.id.btn_logistics);
        btnBanner = (Button) findViewById(R.id.btn_banner);
        btnFoldText = (Button) findViewById(R.id.btn_fold_text);
        btnFoldText.setOnClickListener(this);
        btnBanner.setOnClickListener(this);
        btnLogistics.setOnClickListener(this);
        btnSilde.setOnClickListener(this);
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
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 201);
                } else {
                    intent = new Intent(this, SideActivity.class);
                }
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
            case R.id.btn_fold_text:
                intent = new Intent(this, FoldedTextActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermissionDismiss = false;//有权限没有通过
        boolean showSystemSetting = true;
        if (201 == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    Log.e("yml", "permissions: " + permissions[i]);
                    hasPermissionDismiss = true;
                }
            }
            //如果有权限没有被允许
            if (hasPermissionDismiss) {
                if (showSystemSetting) {
                    showSystemPermissionsSettingDialog();//跳转到系统设置权限页面，
                } else {
//                    或者直接关闭页面，不让他继续访问
                }
            } else {
                //全部权限通过，可以进行下一步操作。。。
            }
        }

    }

    AlertDialog mPermissionDialog;

    public void showSystemPermissionsSettingDialog() {
        final String mPackName = this.getPackageName();
        if (mPermissionDialog == null) {
            mPermissionDialog = new AlertDialog.Builder(this)
                    .setMessage("已禁用权限，请手动授予")
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();
                            Uri packageURI = Uri.parse("package:" + mPackName);
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //关闭页面或者做其他操作
                            cancelPermissionDialog();
                        }
                    })
                    .create();
        }
        mPermissionDialog.show();
    }

    //关闭对话框
    private void cancelPermissionDialog() {
        if (mPermissionDialog != null) {
            mPermissionDialog.cancel();
            mPermissionDialog = null;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelPermissionDialog();
    }
}

