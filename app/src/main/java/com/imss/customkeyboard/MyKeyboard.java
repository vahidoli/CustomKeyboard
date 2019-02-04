package com.imss.customkeyboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class MyKeyboard extends LinearLayout implements View.OnClickListener {

    private Button button1, button2, button3, button4,
            button5, button6, button7, button8,
            button9, button0, buttonDelete, buttonEnter;

    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;
    AlertDialog.Builder builder;
    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();
    private boolean isTimerRunning = false;
    boolean key2_flag = false;

    public MyKeyboard(Context context) {
        this(context, null, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.keyboard_layout, this, true);
        builder = new AlertDialog.Builder(context);
        button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button_3);
        button3.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.button_4);
        button4.setOnClickListener(this);
        button5 = (Button) findViewById(R.id.button_5);
        button5.setOnClickListener(this);
        button6 = (Button) findViewById(R.id.button_6);
        button6.setOnClickListener(this);
        button7 = (Button) findViewById(R.id.button_7);
        button7.setOnClickListener(this);
        button8 = (Button) findViewById(R.id.button_8);
        button8.setOnClickListener(this);
        button9 = (Button) findViewById(R.id.button_9);
        button9.setOnClickListener(this);
        button0 = (Button) findViewById(R.id.button_0);
        button0.setOnClickListener(this);
        buttonDelete = (Button) findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(this);
        buttonEnter = (Button) findViewById(R.id.button_enter);
        buttonEnter.setOnClickListener(this);

        keyValues.put(R.id.button_1, "1");
        keyValues.put(R.id.button_2, "A");
        keyValues.put(R.id.button_3, "D");
        keyValues.put(R.id.button_4, "G");
        keyValues.put(R.id.button_5, "J");
        keyValues.put(R.id.button_6, "M");
        keyValues.put(R.id.button_7, "P");
        keyValues.put(R.id.button_8, "T");
        keyValues.put(R.id.button_9, "W");
        keyValues.put(R.id.button_0, "0");
        /*keyValues.put(R.id.button_enter, "\n");*/
    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        if (inputConnection == null)
            return;

        //Log.d("Keyboard", "StartTime: " + String.valueOf(startTime));

        if (view.getId() == R.id.button_delete) {
            CharSequence selectedText = inputConnection.getSelectedText(0);
            if (TextUtils.isEmpty(selectedText)) {
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                inputConnection.commitText("", 1);
            }
        } else if (view.getId() == R.id.button_enter) {
            String text;
            text = MainActivity.getInstance().getText();
            if (!TextUtils.isEmpty(text)) {
                builder.setMessage("Entered text is: " + text)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.getInstance().clearText();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        } else if (view.getId() == R.id.button_1 || view.getId() ==R.id.button_0){
            key2_flag = false;
            String value = keyValues.get(view.getId());
            inputConnection.commitText(value, 1);
        } else if (view.getId() == R.id.button_2){
            String value = keyValues.get(view.getId());
            long startTime, difference;
            if(!key2_flag) {
                startTime = SystemClock.elapsedRealtime();
                key2_flag = true;
            } else {
                startTime = 0;
            }

            if(key2_flag)
                difference = System.currentTimeMillis() - startTime;
            else
                difference = 0;

            Log.d("Keyboard_k1", "StartTime: " + String.valueOf(startTime));
            Log.d("Keyboard_k1", "Difference: " + String.valueOf(difference));

            if ((difference / 1000) <= 1) {
                CharSequence selectedText = inputConnection.getSelectedText(0);
                if (TextUtils.isEmpty(selectedText)) {
                    inputConnection.deleteSurroundingText(1, 0);
                } else {
                    inputConnection.commitText("", 1);
                }
                if(value == "A")
                    keyValues.put(R.id.button_2, "B");
                else if(value == "B")
                    keyValues.put(R.id.button_2, "C");
                else if (value == "C")
                    keyValues.put(R.id.button_2, "1");
                else
                    keyValues.put(R.id.button_2, "A");

                inputConnection.commitText(value, 1);
            } else {
                keyValues.put(R.id.button_2, "A");
            }
        } else {
            String value = keyValues.get(view.getId());
            inputConnection.commitText(value, 1);
        }
    }

    public void setInputConnection(InputConnection ic) {
        inputConnection = ic;
    }

    /*private void startTimer() {
        if(timer == null)
            timer =  new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 0, 2000);
        isTimerRunning = true;
    }

    private void stopTimer() {
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
        isTimerRunning = false;
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        stopTimer();
                    }
                });
            }
        };
    }*/
}
