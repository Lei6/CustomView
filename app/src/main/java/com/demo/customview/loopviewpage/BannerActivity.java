package com.demo.customview.loopviewpage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.customview.R;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {

    private LoopViewPager loopViewPage;
    private LoopViewPager loopViewPage2;
    private LoopViewPager loopViewPage3;
    private LoopViewPager loopViewPage4;
    private LoopViewPager loopViewPage5;
    private LoopViewPager loopViewPage6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        initView();
        initData();
    }

    private void initData() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("http://e.hiphotos.baidu.com/nuomi/pic/item/a044ad345982b2b74ba5c21a37adcbef76099b32.jpg");
        arrayList.add("http://e.hiphotos.baidu.com/nuomi/pic/item/a044ad345982b2b74ba5c21a37adcbef76099b32.jpg");
        arrayList.add("http://e.hiphotos.baidu.com/nuomi/pic/item/a044ad345982b2b74ba5c21a37adcbef76099b32.jpg");
        arrayList.add("http://e.hiphotos.baidu.com/nuomi/pic/item/a044ad345982b2b74ba5c21a37adcbef76099b32.jpg");
        loopViewPage.setImgData(arrayList);
        loopViewPage.start();

        loopViewPage2.setImgData(arrayList);
        loopViewPage2.start();

        loopViewPage3.setImgData(arrayList);
        loopViewPage3.start();

        loopViewPage4.setImgData(arrayList);
        loopViewPage4.start();

        loopViewPage5.setImgData(arrayList);
        loopViewPage5.start();

        loopViewPage6.setImgData(arrayList);
        loopViewPage6.start();
    }

    private void initView() {
        loopViewPage = (LoopViewPager) findViewById(R.id.loop_view_page);
        loopViewPage2 = (LoopViewPager) findViewById(R.id.loop_view_page_2);
        loopViewPage3 = (LoopViewPager) findViewById(R.id.loop_view_page3);
        loopViewPage4 = (LoopViewPager) findViewById(R.id.loop_view_page4);
        loopViewPage5 = (LoopViewPager) findViewById(R.id.loop_view_page5);
        loopViewPage6 = (LoopViewPager) findViewById(R.id.loop_view_page6);
    }
}
