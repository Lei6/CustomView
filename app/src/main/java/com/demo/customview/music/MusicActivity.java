package com.demo.customview.music;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.customview.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, MediaPlayer.OnCompletionListener {

    private TextView ivPlayPause;
    private SeekBar sbScale;
    private SeekBar sbProgress;
    private MusicView lrcvLrc;
    private MediaPlayer player;
    private boolean isPressed;

    private boolean isRunning;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!isRunning) {
                return;
            }
            setProgress();
            handler.postDelayed(runnable, 500);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initView();
    }

    private void initView() {
        ivPlayPause = (TextView) findViewById(R.id.tv_play_pause);
        sbScale = (SeekBar) findViewById(R.id.sb_scale);
        sbProgress = (SeekBar) findViewById(R.id.sb_progress);
        lrcvLrc = (MusicView) findViewById(R.id.lrcv_lrc);

        ivPlayPause.setOnClickListener(this);
        sbProgress.setOnSeekBarChangeListener(this);
        sbScale.setOnSeekBarChangeListener(this);
        sbScale.setMax(100);
        sbScale.setProgress(50);
        initPlayer();
        initLrc();
    }

    private void initLrc() {
        lrcvLrc.setOnSeekChangeListener(new MusicView.OnSeekChangeListener() {
            @Override
            public void onProgressChanged(int progress) {
                player.seekTo(progress);
                setProgress();
            }
        });
        lrcvLrc.setOnClickListener(new MusicView.OnClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(MusicActivity.this, "Click lrc!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initPlayer() {
        player = MediaPlayer.create(this, R.raw.huasha);
        player.setOnCompletionListener(this);
    }

    private void setProgress() {
        if (!isPressed) {
            sbProgress.setMax(player.getDuration());
            sbProgress.setProgress(player.getCurrentPosition());
        }
    }

    @Override
    public void onClick(View view) {
        int resId = view.getId();
        if (resId == R.id.tv_play_pause) {
            if ("Play".equals(ivPlayPause.getText())) {
                player.start();
                lrcvLrc.setData(getLrcRows());
                ivPlayPause.setText("暂停");
                reStartTimer();
            } else {
                if (player.isPlaying()) {
                    player.pause();
                    ivPlayPause.setText("播放");
                    stopTimer();
                } else {
                    player.start();
                    ivPlayPause.setText("暂停");
                    reStartTimer();
                }
            }
        }
    }

    private ArrayList<MusicRow> getLrcRows() {
        ArrayList<MusicRow> rows = null;
        InputStream is = getResources().openRawResource(R.raw.hs);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            rows = DefaultLrcParser.getLrcRows(sb.toString());
            Log.d("Lrc", sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    private void reStartTimer() {
        stopTimer();
        isRunning = true;
        handler.post(runnable);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == sbProgress) {
            lrcvLrc.seekTo(progress, fromUser);
        } else if (seekBar == sbScale && fromUser) {
            lrcvLrc.setMusicScale(progress / 100f);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (seekBar == sbProgress) {
            isPressed = true;
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar == sbProgress) {
            isPressed = false;
            player.seekTo(seekBar.getProgress());
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        stopTimer();
        player.seekTo(0);
        sbProgress.setProgress(0);
    }

    private void stopTimer() {
        isRunning = false;
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onDestroy() {
        stopTimer();
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
        if (lrcvLrc != null) {
            lrcvLrc.reset();
        }
        super.onDestroy();
    }
}
