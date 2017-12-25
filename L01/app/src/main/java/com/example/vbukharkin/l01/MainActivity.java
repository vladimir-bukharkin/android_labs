package com.example.vbukharkin.l01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private TextView text;
    private Intent playServiceIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        playServiceIntent = new Intent(MainActivity.this, PlayService.class);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bPlayPause:
                startService(playServiceIntent);
                text.setText("Play");
                break;
            case R.id.bStop:
                stopService((playServiceIntent));
                text.setText("Stop");
                break;
        }
    }
}
