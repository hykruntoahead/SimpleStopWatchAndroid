package com.example.heyukun.simplestopwatch;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mCounterTextView;

    private ImageView mPlayImg,mPauseImg;
    private float mCount = 0.0f;


    private boolean isPlaying = false;

    private Handler mHandler = new Handler(
            new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    Log.i("handleMessage","handleMessage-----");

                    return false;
                }
            }
    );


//    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCounterTextView = (TextView) findViewById(R.id.tv_count);
        mPlayImg = (ImageView) findViewById(R.id.iv_play);
        mPauseImg = (ImageView) findViewById(R.id.iv_pause);


        findViewById(R.id.tv_reset).setOnClickListener(this);
        mPlayImg.setOnClickListener(this);
        mPauseImg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_reset:
                mPauseImg.setEnabled(true);
                mPlayImg.setEnabled(true);
                isPlaying = false;
                mHandler.removeCallbacks(null);
                mCount = 0.0f;
                mCounterTextView.setText(getString(R.string.label_counter));
                break;
            case R.id.iv_play:
                if (isPlaying){
                return;
                }

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(isPlaying){
                            mCount+=0.1f;
                        }
                        mCounterTextView.setText(String.format("%.1f",mCount));
                        mHandler.postDelayed(this,100);
                    }
                },100);
                isPlaying = true;
//                mHandler.postDelayed(mRunnable,100);
                mPauseImg.setEnabled(true);
                mPlayImg.setEnabled(false);

                break;
            case R.id.iv_pause:
                if (!isPlaying) {
                    return;
                }
                mPauseImg.setEnabled(false);
                mPlayImg.setEnabled(true);
                mHandler.removeCallbacks(null);
                isPlaying = false;
                break;
        }

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(null);
    }
}
