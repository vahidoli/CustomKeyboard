package com.imss.customkeyboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;

public class MyKeyboard extends LinearLayout {

    private Button button1, button2, button3, button4,
            button5, button6, button7, button8,
            button9, button0, buttonDelete, buttonEnter;
    private AlertDialog.Builder builder;
    private InputConnection inputConnection;

    public MyKeyboard(Context context) {
        this(context, null, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.keyboard_layout, this, true);

        builder = new AlertDialog.Builder(context);

        buttonDelete = findViewById(R.id.button_delete);
        buttonDelete.setOnTouchListener(new CustomTouchListener() {
            @Override
            public void onMultipleTapEvent(MotionEvent e, int numberOfTaps) {
                CharSequence selectedText = inputConnection.getSelectedText(0);
                if (TextUtils.isEmpty(selectedText)) {
                    inputConnection.deleteSurroundingText(1, 0);
                } else {
                    inputConnection.commitText("", 1);
                }
            }
        });

        buttonEnter = findViewById(R.id.button_enter);
        buttonEnter.setOnTouchListener(new CustomTouchListener() {
            @Override
            public void onMultipleTapEvent(MotionEvent e, int numberOfTaps) {
                String text;
                text = MainActivity.getInstance().getText();
                if (!TextUtils.isEmpty(text)) {
                    builder.setMessage(getResources().getString(R.string.entered_text) + text)
                            .setCancelable(false)
                            .setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    MainActivity.getInstance().clearText();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });

        button0 = findViewById(R.id.button_0);
        button0.setOnTouchListener(new CustomTouchListener() {
            @Override
            public void onMultipleTapEvent(MotionEvent e, int numberOfTaps) {
                if(numberOfTaps == 1){
                    inputConnection.commitText("0", 1);
                } else if(numberOfTaps == 2){
                    inputConnection.commitText("\t", 1);
                }
            }
        });

        button1 = findViewById(R.id.button_1);
        button1.setOnTouchListener(new CustomTouchListener() {
            @Override
            public void onMultipleTapEvent(MotionEvent e, int numberOfTaps) {
                inputConnection.commitText("1", 1);
            }
        });

        button2 = findViewById(R.id.button_2);
        button2.setOnTouchListener(new CustomTouchListener() {
            @Override
            public void onMultipleTapEvent(MotionEvent e, int numberOfTaps) {
               if(numberOfTaps == 1){
                   inputConnection.commitText("A", 1);
               }else if(numberOfTaps == 2){
                   inputConnection.commitText("B", 1);
               }else if(numberOfTaps == 3){
                   inputConnection.commitText("C", 1);
               }else if(numberOfTaps == 4){
                   inputConnection.commitText("2", 1);
               }
            }
        });
        button3 = findViewById(R.id.button_3);
        button3.setOnTouchListener(new CustomTouchListener() {
            @Override
            public void onMultipleTapEvent(MotionEvent e, int numberOfTaps) {
                if(numberOfTaps == 1){
                    inputConnection.commitText("D", 1);
                }else if(numberOfTaps == 2){
                    inputConnection.commitText("E", 1);
                }else if(numberOfTaps == 3){
                    inputConnection.commitText("F", 1);
                }else if(numberOfTaps == 4){
                    inputConnection.commitText("3", 1);
                }
            }
        });
        button4 = findViewById(R.id.button_4);
        button4.setOnTouchListener(new CustomTouchListener() {
            @Override
            public void onMultipleTapEvent(MotionEvent e, int numberOfTaps) {
                if(numberOfTaps == 1){
                    inputConnection.commitText("G", 1);
                }else if(numberOfTaps == 2){
                    inputConnection.commitText("H", 1);
                }else if(numberOfTaps == 3){
                    inputConnection.commitText("I", 1);
                }else if(numberOfTaps == 4){
                    inputConnection.commitText("4", 1);
                }
            }
        });
        button5 = findViewById(R.id.button_5);
        button5.setOnTouchListener(new CustomTouchListener() {
            @Override
            public void onMultipleTapEvent(MotionEvent e, int numberOfTaps) {
                if(numberOfTaps == 1){
                    inputConnection.commitText("J", 1);
                }else if(numberOfTaps == 2){
                    inputConnection.commitText("K", 1);
                }else if(numberOfTaps == 3){
                    inputConnection.commitText("L", 1);
                }else if(numberOfTaps == 4){
                    inputConnection.commitText("5", 1);
                }
            }
        });
        button6 = findViewById(R.id.button_6);
        button6.setOnTouchListener(new CustomTouchListener() {
            @Override
            public void onMultipleTapEvent(MotionEvent e, int numberOfTaps) {
                if(numberOfTaps == 1){
                    inputConnection.commitText("M", 1);
                }else if(numberOfTaps == 2){
                    inputConnection.commitText("N", 1);
                }else if(numberOfTaps == 3){
                    inputConnection.commitText("O", 1);
                }else if(numberOfTaps == 4){
                    inputConnection.commitText("6", 1);
                }
            }
        });
        button7 = findViewById(R.id.button_7);
        button7.setOnTouchListener(new CustomTouchListener() {
            @Override
            public void onMultipleTapEvent(MotionEvent e, int numberOfTaps) {
                if(numberOfTaps == 1){
                    inputConnection.commitText("P", 1);
                }else if(numberOfTaps == 2){
                    inputConnection.commitText("Q", 1);
                }else if(numberOfTaps == 3){
                    inputConnection.commitText("R", 1);
                }else if(numberOfTaps == 4){
                    inputConnection.commitText("S", 1);
                }else if(numberOfTaps == 5){
                    inputConnection.commitText("7", 1);
                }
            }
        });
        button8 = findViewById(R.id.button_8);
        button8.setOnTouchListener(new CustomTouchListener() {
            @Override
            public void onMultipleTapEvent(MotionEvent e, int numberOfTaps) {
                if(numberOfTaps == 1){
                    inputConnection.commitText("T", 1);
                }else if(numberOfTaps == 2){
                    inputConnection.commitText("U", 1);
                }else if(numberOfTaps == 3){
                    inputConnection.commitText("V", 1);
                }else if(numberOfTaps == 4){
                    inputConnection.commitText("8", 1);
                }
            }
        });
        button9 = findViewById(R.id.button_9);
        button9.setOnTouchListener(new CustomTouchListener() {
            @Override
            public void onMultipleTapEvent(MotionEvent e, int numberOfTaps) {
                if(numberOfTaps == 1){
                    inputConnection.commitText("W", 1);
                }else if(numberOfTaps == 2){
                    inputConnection.commitText("X", 1);
                }else if(numberOfTaps == 3){
                    inputConnection.commitText("Y", 1);
                }else if(numberOfTaps == 4){
                    inputConnection.commitText("Z", 1);
                }else if(numberOfTaps == 5){
                    inputConnection.commitText("9", 1);
                }
            }
        });

    }
    public void setInputConnection(InputConnection ic) {
        inputConnection = ic;
    }
}