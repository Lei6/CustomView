package com.demo.customview.sort;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.customview.R;

import java.util.List;

public class SideActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private LinearLayout llytTin;
    private SideBarView sbSidebar;
    private TextView tvTinLetter;
    private EditText editSearch;

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
        editSearch = (EditText) findViewById(R.id.edit_search);
        init();
    }

    private List<SortBean> getDatas() {
//        String[] arrays = getResources().getStringArray(R.array.arrays_sort);
//        ArrayList<SortBean> datas = new ArrayList<>();
//        for (String c:arrays){
//            SortBean bean = new SortBean(c);
//            bean.content = c;
//            datas.add(bean);
//        }
        PhoneUtil phoneUtil = new PhoneUtil(this);
        List<SortBean> phone = phoneUtil.getPhone();

        return phone;
    }

    private void init() {
        List<SortBean> datas = getDatas();
        final SortUtil sortUtil = new SortUtil();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        final SortAdapter sortAdapter = new SortAdapter(this, datas, R.layout.item_sort);
        rvList.setAdapter(sortAdapter);
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                sortUtil.onScrolled(recyclerView, llytTin, tvTinLetter);
            }
        });
        sbSidebar.setType(SideBarView.TYPE_CENTER);
        sbSidebar.reset(sortUtil.sortDatas(datas));
        sbSidebar.setOnLetterChangedListener(new SideBarView.OnLetterChangedListener() {
            @Override
            public void onChange(int index, String c) {
                sortUtil.onChange(index, c, rvList);
            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sortAdapter.getFilter().filter(editable);
            }
        });

    }

}
