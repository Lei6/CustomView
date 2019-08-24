package com.demo.customview.slide;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.demo.customview.R;

public class Slide2Activity extends AppCompatActivity {

    private NestedScrollView nestedScrollView;
    private ImageView img;
    private RelativeLayout toolbarDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        initView();
    }

    private void initView() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        img = (ImageView) findViewById(R.id.img);
        toolbarDetails = (RelativeLayout) findViewById(R.id.toolbar_details);

        setSystemBarAlpha(toolbarDetails,img,nestedScrollView);

    }

    public static void setSystemBarAlpha(final RelativeLayout toolbarDetails, final ImageView banner, final NestedScrollView nestedScrollView) {

        ViewTreeObserver viewTreeObserver = banner.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                banner.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                final int mHeight = banner.getHeight() - toolbarDetails.getHeight();//这里取的高度应该为图片的高度-标题栏
                nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                        if (scrollY <= 0) {
                            //顶部图处于最顶部，标题栏透明
                            toolbarDetails.setBackgroundColor(Color.argb(0, 48, 63, 159));
                        } else if (scrollY > 0 && scrollY < mHeight) {
                            //滑动过程中，渐变
                            float scale = (float) scrollY / mHeight;//算出滑动距离比例
                            float alpha = (255 * scale);//得到透明度
                            toolbarDetails.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                        } else {
                            //过顶部图区域，标题栏定色
                            toolbarDetails.setBackgroundColor(Color.argb(255, 255, 255, 255));
                        }
                    }
                });
            }
        });


    }
}
