package com.demo.customview.logisticstraces;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.customview.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogisticsActivity extends AppCompatActivity {

    private RecyclerView logisticsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics);
        initView();
        initData();
    }

    private void initData() {
        ArrayList<TracesBean> traces = new ArrayList<>();
        traces.add(new TracesBean("【晋中市】  山西晋中公司龙湖分部-白晓丽(15525121508) 已揽收", "2019-08-21 14:10:16"));
        traces.add(new TracesBean("【太原市】 已到达  山西太原分拨中心", "2019-08-21 20:01:00"));
        traces.add(new TracesBean("【太原市】 已离开  山西太原分拨中心 发往 河南郑州分拨中心", "2019-08-21 20:03:20"));
        traces.add(new TracesBean("【郑州市】 已到达  河南郑州分拨中心", "2019-08-22 09:55:32"));
        traces.add(new TracesBean("【郑州市】 已离开  河南郑州分拨中心 发往 河南郑州公司", "2019-08-22 09:56:40"));
        traces.add(new TracesBean("【郑州市】 已到达  河南郑州公司 马上为您派送", "2019-08-22 10:00:29"));
        traces.add(new TracesBean("【郑州市】 已离开  河南郑州公司 发往  河南郑州公司沙口路分部(0371-55268283)", "2019-08-22 13:41:52"));
        traces.add(new TracesBean("【郑州市】 已到达  河南郑州公司沙口路分部 马上为您派送", "2019-08-22 14:55:37"));
        traces.add(new TracesBean("【郑州市】  河南郑州公司沙口路分部 派件员 张军(18939572891)正在为您派送", "2019-08-22 15:24:13"));
        traces.add(new TracesBean("【郑州市】 您的快件已送达 南阳路202号院门卫 保管 地址：南阳路202号院门卫处  如有问题请电联业务员 张军(18939572891)", "2019-08-22 16:07:25"));

        Collections.reverse(traces);  //倒序

        LogisticsAdapter adapter = new LogisticsAdapter(LogisticsActivity.this, traces, R.layout.item_logistics);

        logisticsRecycler.setLayoutManager(new LinearLayoutManager(LogisticsActivity.this));
        logisticsRecycler.setAdapter(adapter);
    }

    private void initView() {
        logisticsRecycler = (RecyclerView) findViewById(R.id.logistics_recycler);
    }
}
