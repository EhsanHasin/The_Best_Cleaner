package com.csofcs.ehsanhasin.thebestcleaner.SAFE_BROWSING;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.csofcs.ehsanhasin.thebestcleaner.R;

public class Safe_Brosing extends AppCompatActivity {
    Toolbar toolbar;
    EditText editTex;
    Button btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe__brosing);
        toolbar = findViewById(R.id.toolbar2);
        editTex = findViewById(R.id.editText);
        btn = findViewById(R.id.btn_cancel);

//        editTex.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toolbar.setVisibility(View.GONE);
//                btn.setVisibility(View.VISIBLE);
//            }
//        });


//        editTex.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                toolbar.setVisibility(View.GONE);
//                btn.setVisibility(View.VISIBLE);
//
//                return false;
//            }
//        });
//
//        editTex.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//                if(!TextUtils.isEmpty(editTex.getText().toString().trim())){
//                    btn.setText("search");
//                }else {
//                    btn.setText("cancel");
//                }
//                return false;
//            }
//        });
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(!TextUtils.isEmpty(editTex.getText().toString().trim())){
//                    btn.setText("search");
//
//                }else {
//                    btn.setText("cancel");
//                    toolbar.setVisibility(View.VISIBLE);
//                    btn.setVisibility(View.GONE);
//                }
//
//            }
//        });
    }
}
