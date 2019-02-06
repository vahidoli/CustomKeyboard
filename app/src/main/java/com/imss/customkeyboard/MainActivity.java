package com.imss.customkeyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private static MainActivity mActivity;
    InputConnection inputConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;

        editText = (EditText) findViewById(R.id.editText);
        MyKeyboard keyboard = (MyKeyboard) findViewById(R.id.keyboard);
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(false);

        inputConnection = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(inputConnection);

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int inType = editText.getInputType(); // backup the input type
                editText.setInputType(InputType.TYPE_NULL); // disable soft input
                editText.onTouchEvent(event); // call native handler
                editText.setInputType(inType); // restore input type
                return true; // consume touch event
            }
        });
    }

    public static MainActivity getInstance() {
        return mActivity;
    }

    public String getText() {
        if (editText != null) {
            String temp = editText.getText().toString();
            return temp;
        }
        return "";
    }

    public void clearText() {
        editText.setText("");
    }
}
