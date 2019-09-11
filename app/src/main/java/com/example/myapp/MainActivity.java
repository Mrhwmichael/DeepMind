package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button= (Button) findViewById(R.id.Button);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        button.setOnClickListener(this);

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.Button:
                if(progressBar.getVisibility()==View.GONE){
                    progressBar.setVisibility(View.VISIBLE);

                }
                else{
                    progressBar.setVisibility(View.GONE);
                }

        }
    }
}
