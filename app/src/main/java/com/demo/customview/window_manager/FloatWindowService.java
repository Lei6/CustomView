package com.demo.customview.window_manager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 * Author by YML, Date on 2021/3/30.
 * PS: Not easy to write code, please indicate.
 */
public class FloatWindowService extends Service {

    private FloatWindowManager floatWindowManager;

    @Override
    public void onCreate() {
        super.onCreate();
        floatWindowManager = new FloatWindowManager(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startOrStopFloatWindow(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    private void startOrStopFloatWindow(Intent intent) {
        if (floatWindowManager == null) floatWindowManager = new FloatWindowManager(this);

        floatWindowManager.createFloatWindow();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
