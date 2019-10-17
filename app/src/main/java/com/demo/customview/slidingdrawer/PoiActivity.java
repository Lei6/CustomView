package com.demo.customview.slidingdrawer;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.demo.customview.R;

import java.util.ArrayList;

public class PoiActivity extends AppCompatActivity implements PoiLayout.OnChangeListener {

    private PoiLayout poiLayout;
    private PoiListView poiList;
    private PoiTextView tvBottom;
    private Handler handler;
    private ArrayList<PoiBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi);
        handler = new Handler();

        initView();
        initData();
        initPoiLayout();
    }

    private void initPoiLayout() {
        poiLayout.setOnChangeListener(this);
        tvBottom.setOnTikListener(new PoiTextView.OnTikListener() {
            @Override
            public void onTik(View v) {
                poiLayout.toggle(PoiLayout.STATUS_EXTEND);
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            list.add(new PoiBean("标题:", "xxxxxxxxxx"));
        }

//        getData(1);
        PoiAdapter adapter = new PoiAdapter(this, list,R.layout.item_poi);
        poiList.setLayoutManager(new LinearLayoutManager(this));
        poiList.setAdapter(adapter);

    }

    private void initView() {
        poiLayout = (PoiLayout) findViewById(R.id.poi_layout);
        poiList = (PoiListView) findViewById(R.id.poi_list);
        tvBottom = (PoiTextView) findViewById(R.id.tv_bottom);
    }

    /**
     * 模拟数据获取
     */
    private void getData(final int page) {
        long delayMillis = page == 1 ? 0 : 2000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFinishing()) {
                    return;
                }
                ArrayList<PoiBean> datas = new ArrayList<>();
                int count = page < 10 ? 10 : 6;
                for (int i = 0; i < count; i++) {
                    int index = 10 * (page - 1) + i;
                    datas.add(new PoiBean("标题:" + index, "xxxxxxxxxx" + index));
                }
                list.addAll(datas);
            }
        }, delayMillis);
    }

    @Override
    public void onChange(int status) {
        tvBottom.setVisibility(status == PoiLayout.STATUS_CLOSE ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onScroll(float offset) {
        tvBottom.setVisibility(offset == 1 ? View.VISIBLE : View.GONE);
    }
    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
