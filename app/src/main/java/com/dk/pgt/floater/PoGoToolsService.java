package com.dk.pgt.floater;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.dk.pgt.PGTApp;
import com.dk.pgt.R;
import com.dk.pgt.databinding.FloaterBinding;
import com.dk.pgt.evolve.EvolveView;

public class PoGoToolsService extends Service {

    private final WindowManager.LayoutParams mParams = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams
                    .FLAG_DIM_BEHIND,
            PixelFormat.TRANSLUCENT);

    private WindowManager mWindowManager;
    private View mFloater;
    private FloaterBinding binding;
    private FloaterViewModel viewModel;
    private Point screenSize;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        screenSize = new Point();
        mWindowManager.getDefaultDisplay().getSize(screenSize);

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        mFloater = layoutInflater.inflate(R.layout.floater, null);

        binding = FloaterBinding.bind(mFloater);
        viewModel = new FloaterViewModel();
        binding.setViewModel(viewModel);

        binding.content.addView(new EvolveView(this, viewModel));

        mParams.gravity = Gravity.TOP | Gravity.LEFT;
        mParams.x = 0;
        mParams.y = 100;
        mParams.dimAmount = 0.5f;

        mWindowManager.addView(mFloater, mParams);

        setupMotionHandler();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFloater != null) mWindowManager.removeView(mFloater);
    }

    private void setupMotionHandler() {
        binding.floaterIcon.setOnTouchListener(new View.OnTouchListener() {
            private static final double MOTION_THRESHOLD = 2000;
            private static final double BOTTOM_SCREEN_PERCENTAGE_TO_DESTROY = 0.9;
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;
            private float totalMotion;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = mParams.x;
                        initialY = mParams.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d(PGTApp.PGT, "TotalMotion: " + totalMotion);
                        if (totalMotion < MOTION_THRESHOLD) {
                            viewModel.controlFormVisbility(mParams);
                            mWindowManager.updateViewLayout(mFloater, mParams);
                        }
                        totalMotion = 0;
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        totalMotion += event.getRawX() + event.getRawY();
                        if (event.getRawY() > screenSize.y * BOTTOM_SCREEN_PERCENTAGE_TO_DESTROY) {
                            stopSelf();
                        }
                        mParams.x = initialX + (int) (event.getRawX() - initialTouchX);
                        mParams.y = initialY + (int) (event.getRawY() - initialTouchY);
                        mWindowManager.updateViewLayout(mFloater, mParams);
                        return true;
                }
                return false;
            }
        });
    }
}
