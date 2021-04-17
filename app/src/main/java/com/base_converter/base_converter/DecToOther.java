package com.base_converter.base_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DecToOther extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_dec;
    private TextView binaryOutput, octalOutput, hexOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dec_to_other);

        editText_dec = findViewById(R.id.editText_dec);
        Button convertButton = findViewById(R.id.button_convert);
        binaryOutput = findViewById(R.id.textView_bin_output);
        octalOutput = findViewById(R.id.textView_oct_output);
        hexOutput= findViewById(R.id.textView_hex_output);
        Button reset = findViewById(R.id.button_reset);

        convertButton.setOnClickListener(this);
        reset.setOnClickListener(this);
    }
    static char reVal(int num)
    {
        if (num >= 0 && num <= 9)
            return (char)(num + 48);
        else
            return (char)(num - 10 + 65);
    }
    static String fromDeci(int base_output, double inputNum)
    {
        StringBuilder s = new StringBuilder();
        int Integral = (int) inputNum;
        double fractional = inputNum - Integral;
        while (Integral > 0)
        {
            s.append(reVal(Integral % base_output));
            Integral /= base_output;
        }
        StringBuilder ix = new StringBuilder();
        ix.append(s);
        StringBuilder output = new StringBuilder(new String(ix.reverse()));
        if (fractional > 0)
        {
            output.append('.');
        }
        while (fractional > 0)
        {
            fractional *= base_output;
            int fractional_bit = (int) fractional;
            output.append(reVal(fractional_bit));
            fractional -= fractional_bit;
        }
        return output.toString();
    }

    @Override
    public void onClick(View v) {

        try {
            if(v.getId()==R.id.button_convert)
            {
                String decimal_string = editText_dec.getText().toString();
                double decimal = Double.parseDouble(decimal_string);
                if(decimal > 2147483647)
                {
                    editText_dec.setText(null);
                    binaryOutput.setText("...........................");
                    octalOutput.setText("...........................");
                    hexOutput.setText("...........................");
                    Toast toast_massage_dec_error = Toast.makeText(DecToOther.this,"Number Is Too Large! Overflow!",Toast.LENGTH_SHORT);
                    toast_massage_dec_error.setGravity(Gravity.BOTTOM,0,200);
                    toast_massage_dec_error.show();
                }
                else
                    {
                    String binary = fromDeci(2,decimal);
                    binaryOutput.setText(binary);
                    binaryOutput.setTypeface(null, Typeface.BOLD);

                    String octal = fromDeci(8,decimal);
                    octalOutput.setText(octal);
                    octalOutput.setTypeface(null, Typeface.BOLD);

                    String hexadecimal = fromDeci(16,decimal);
                    hexOutput.setText(hexadecimal);
                    hexOutput.setTypeface(null, Typeface.BOLD);
                }
            }

            if(v.getId()==R.id.button_reset)
            {
                editText_dec.setText(null);
                binaryOutput.setText("...........................");
                binaryOutput.setTypeface(null, Typeface.NORMAL);
                octalOutput.setText("...........................");
                octalOutput.setTypeface(null, Typeface.NORMAL);
                hexOutput.setText("...........................");
                hexOutput.setTypeface(null, Typeface.NORMAL);
            }
        }
        catch (Exception e) {
            Toast toast_massage_dec = Toast.makeText(DecToOther.this,"Please Enter Input",Toast.LENGTH_SHORT);
            toast_massage_dec.setGravity(Gravity.BOTTOM,0,200);
            toast_massage_dec.show();
        }
    }
}
