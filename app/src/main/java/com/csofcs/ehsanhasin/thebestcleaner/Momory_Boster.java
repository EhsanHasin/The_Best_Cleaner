package com.csofcs.ehsanhasin.thebestcleaner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.csofcs.ehsanhasin.thebestcleaner.R;

public class Momory_Boster extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_momory__boster);
    }

    public void OnAnimation(View view) {

        startActivity(new Intent(this,LaunchRocketValueAnimatorAnimationActivity.class));
    }
}
