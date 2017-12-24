package com.example.vbukharkin.l02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vbukharkin.l02.converter.DbConverter;
import com.example.vbukharkin.l02.converter.FreqConverter;
import com.example.vbukharkin.l02.converter.IConverter;
import com.example.vbukharkin.l02.excptions.ConverterActivityException;
import com.example.vbukharkin.l02.utill.ConvertingType;

public class ConverterActivity extends AppCompatActivity {

    private Integer inValueType;
    private Integer outValueType;
    private Float value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        try {
            execute();
        } catch (ConverterActivityException e) {
            e.printStackTrace();
            Intent intent = new Intent(MainActivity.class.getName());
            startActivity(intent);
            finish();
        }
    }

    private void execute() throws ConverterActivityException {

        TextView resultText = findViewById(R.id.result);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            ConvertingType convertingType = ConvertingType.get(b.getInt(MainActivity.bundleConverterType));

            final IConverter converter;
            if (convertingType == ConvertingType.FREQ) {
                converter = new FreqConverter();
            } else {
                converter = new DbConverter();
            }
            ArrayAdapter<?> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, converter.getSpinnerElements());

            Spinner inSpinner = findViewById(R.id.spinnerInput);
            inSpinner.setAdapter(adapter);
            inSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    inValueType = i;
                    if (value != null && outValueType != null) {
                        resultText.setText(String.valueOf(converter.calculate(value, inValueType, outValueType)));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            Spinner outSpinner = findViewById(R.id.spinnerOutput);
            outSpinner.setAdapter(adapter);
            outSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    outValueType = i;
                    if (value != null && inValueType != null) {
                        resultText.setText(String.valueOf(converter.calculate(value, inValueType, outValueType)));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            startValueListener(resultText, converter);
        } else {
            throw new ConverterActivityException("Bundle doesn't exist");
        }
    }

    private void startValueListener(TextView resultText, IConverter converter) {

        EditText inputValue = findViewById(R.id.inputText);
        inputValue.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    value = Float.parseFloat(charSequence.toString());
                    if (inValueType != null && outValueType != null) {
                        resultText.setText(String.valueOf(converter.calculate(value, inValueType, outValueType)));
                    }
                } else {
                    value = null;
                    resultText.setText(R.string.result);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}
