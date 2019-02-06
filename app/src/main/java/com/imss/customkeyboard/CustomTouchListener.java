package com.imss.customkeyboard;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

public abstract class CustomTouchListener implements View.OnTouchListener {
    Handler handler = new Handler();

    private boolean manageInActionDown;
    private float tapTimeoutMultiplier;

    private int numberOfTaps = 0;
    private long lastTapTimeMs = 0;
    private long touchDownMs = 0;

    public CustomTouchListener() {
        this(false, 1);
    }

    public CustomTouchListener(boolean manageInActionDown, float tapTimeoutMultiplier) {
        this.manageInActionDown = manageInActionDown;
        this.tapTimeoutMultiplier = tapTimeoutMultiplier;
    }

    /**
     * @param e
     * @param numberOfTaps
     */
    public abstract void onMultipleTapEvent(MotionEvent e, int numberOfTaps);

    @Override
    public final boolean onTouch(View v, final MotionEvent event) {
        if (manageInActionDown)
            onTouchDownManagement(v, event);
        else
            onTouchUpManagement(v, event);
        return true;
    }


    private void onTouchDownManagement(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDownMs = System.currentTimeMillis();
                handler.removeCallbacksAndMessages(null);

                if (numberOfTaps > 0 && (System.currentTimeMillis() - lastTapTimeMs) < ViewConfiguration.getTapTimeout() * tapTimeoutMultiplier) {
                    numberOfTaps += 1;
                } else {
                    numberOfTaps = 1;
                }

                lastTapTimeMs = System.currentTimeMillis();

                if (numberOfTaps > 0) {
                    final MotionEvent finalMotionEvent = MotionEvent.obtain(event); // to avoid side effects
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onMultipleTapEvent(finalMotionEvent, numberOfTaps);
                        }
                    }, (long) (ViewConfiguration.getDoubleTapTimeout() * tapTimeoutMultiplier));
                }
                break;

            case MotionEvent.ACTION_UP:
                break;
        }
    }


    private void onTouchUpManagement(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDownMs = System.currentTimeMillis();
                break;

            case MotionEvent.ACTION_UP:
                handler.removeCallbacksAndMessages(null);

                if ((System.currentTimeMillis() - touchDownMs) > ViewConfiguration.getTapTimeout()) {
                    numberOfTaps = 0;
                    lastTapTimeMs = 0;
                    break;
                }

                if (numberOfTaps > 0 && (System.currentTimeMillis() - lastTapTimeMs) < ViewConfiguration.getDoubleTapTimeout()) {
                    numberOfTaps += 1;
                } else {
                    numberOfTaps = 1;
                }

                lastTapTimeMs = System.currentTimeMillis();

                if (numberOfTaps > 0) {
                    final MotionEvent finalMotionEvent = MotionEvent.obtain(event); // to avoid side effects
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onMultipleTapEvent(finalMotionEvent, numberOfTaps);
                        }
                    }, ViewConfiguration.getDoubleTapTimeout());
                }
        }
    }
}