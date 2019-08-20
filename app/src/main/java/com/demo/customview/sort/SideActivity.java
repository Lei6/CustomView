package com.demo.customview.sort;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.customview.R;

import java.util.ArrayList;
import java.util.List;

public class SideActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private LinearLayout llytTin;
    private SideBarView sbSidebar;
    private TextView tvTinLetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side);
        initView();
    }

    private void initView() {
        rvList = (RecyclerView) findViewById(R.id.rv_list);
        llytTin = (LinearLayout) findViewById(R.id.llyt_tin);
        sbSidebar = (SideBarView) findViewById(R.id.sb_sidebar);
        tvTinLetter = (TextView) findViewById(R.id.tv_tin_letter);

        init();
    }

    private ArrayList<SortBean> getDatas() {
        String[] arrays = getResources().getStringArray(R.array.arrays_sort);
        ArrayList<SortBean> datas = new ArrayList<>();
        for (String c:arrays){
            SortBean bean = new SortBean(c);
            bean.content = c;
            datas.add(bean);
        }
        return datas;
    }

    private void init() {
        ArrayList<SortBean> datas = getDatas();
        final SortUtil sortUtil = new SortUtil();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(new SortAdapter(this,datas,R.layout.item_sort));
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                sortUtil.onScrolled(recyclerView,llytTin,tvTinLetter);
            }
        });
        sbSidebar.setType(SideBarView.TYPE_CENTER);
        sbSidebar.reset(sortUtil.sortDatas(datas));
        sbSidebar.setOnLetterChangedListener(new SideBarView.OnLetterChangedListener() {
            @Override
            public void onChange(int index, String c) {
                sortUtil.onChange(index,c,rvList);
            }
        });

    }

}
