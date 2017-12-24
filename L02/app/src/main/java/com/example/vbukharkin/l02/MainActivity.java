package com.example.vbukharkin.l02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.vbukharkin.l02.utill.ConvertingType;

public class MainActivity extends AppCompatActivity {

    public static String bundleConverterType = "converter_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startConverter();
    }

    private void startConverter() {

        final Button freqButton = findViewById(R.id.buttonFreq);
        freqButton.setOnClickListener(v -> {
            Intent intent = new Intent(ConverterActivity.class.getName());
            Bundle converterBundle = new Bundle();
            converterBundle.putInt(bundleConverterType, ConvertingType.FREQ.getId());
            intent.putExtras(converterBundle);
            startActivity(intent);
        });

        final Button dbButton = findViewById(R.id.buttonDb);
        dbButton.setOnClickListener(v -> {
            Intent intent = new Intent(ConverterActivity.class.getName());
            Bundle converterBundle = new Bundle();
            converterBundle.putInt(bundleConverterType, ConvertingType.DB_LEVEL.getId());
            intent.putExtras(converterBundle);
            startActivity(intent);
        });
    }
}
