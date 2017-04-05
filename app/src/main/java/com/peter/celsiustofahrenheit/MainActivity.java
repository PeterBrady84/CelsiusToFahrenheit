package com.peter.celsiustofahrenheit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText temperature;
    private TextView input_temperature_validation, result;
    private RadioButton cToFRadio, fToCRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperature = (EditText) findViewById(R.id.temperatureEdit);

        // read status of celsius and fahrenheit radio button
        cToFRadio = (RadioButton) findViewById(R.id.cToFRadioButton);
        fToCRadio = (RadioButton) findViewById(R.id.fToCRadioButton);
        cToFRadio.setChecked(true);

        final Button convert = (Button) findViewById(R.id.convertButton);
        result = (TextView) findViewById(R.id.result);

        // validation for temperature input
        input_temperature_validation = (TextView) findViewById(R.id.input_temperature_validation);
        input_temperature_validation.setVisibility(View.GONE);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temperature.getText().toString().equals("")) {
                    input_temperature_validation.setVisibility(View.VISIBLE);
                    input_temperature_validation.setText(R.string.empty_value);
                    result.setText("");
                } else {
                    input_temperature_validation.setVisibility(View.GONE);
                    String checkedRadio;
                    char tempSymbol1, tempSymbol2;
                    if (cToFRadio.isChecked()) {
                        checkedRadio = cToFRadio.getText().toString();
                        tempSymbol1 = 'C';
                        tempSymbol2 = 'F';
                    } else {
                        checkedRadio = fToCRadio.getText().toString();
                        tempSymbol1 = 'F';
                        tempSymbol2 = 'C';
                    }
                    double temp = convertTemperature();
                    DecimalFormat df = new DecimalFormat("#,###.##");
                    result.setText(getString(R.string.output, temperature.getText().toString(), tempSymbol1,
                            checkedRadio, df.format(temp), tempSymbol2));
                }
            }
        });
    }

    public double convertTemperature() {
        //To Convert from C to F = multiply by 9, divide by 5 and +32
        //To Convert from F to C = -32, multiply by 5 and divide by 9
        if (cToFRadio.isChecked()) {
            double value = Double.parseDouble(temperature.getText().toString());
            return ((value * 9) / 5) + 32;
        } else {
            double value = Double.parseDouble(temperature.getText().toString());
            return ((value - 32) * 5) / 9;
        }
    }
}