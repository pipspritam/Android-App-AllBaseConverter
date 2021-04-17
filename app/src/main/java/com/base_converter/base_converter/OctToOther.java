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

public class OctToOther extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_oct;
    private TextView decimalOutput, binaryOutput, hexOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oct_to_other);

        editText_oct = findViewById(R.id.editText_oct);
        Button convertButton = findViewById(R.id.button_convert);
        decimalOutput = findViewById(R.id.textView_dec_output);
        binaryOutput = findViewById(R.id.textView_bin_output);
        hexOutput= findViewById(R.id.textView_hex_output);
        Button reset = findViewById(R.id.button_reset);

        convertButton.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    static int val(char c) {
        if (c >= '0' && c <= '9')
            return (int)c - '0';
        else
            return (int)c - 'A' + 10;
    }
    static double toDeci(String str) {
        int len = str.length();
        double power = 1;
        double num = 0;
        int i;
        int point = str.indexOf(".");
        if (point == -1) {
            point = len;
        }
        for (i = point - 1; i >= 0; i--)
        {
            num += val(str.charAt(i)) * power;
            power = power * 8;
        }
        double num2=0;
        power = 8;
        for (i=point+1;i<len;i++)
        {
            num2 = num2 + val(str.charAt(i)) / power;
            power = power * 8;
        }
        return num+num2;
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
            if(v.getId()==R.id.button_convert) {
                String octal_string = editText_oct.getText().toString();
                if(octal_string.matches(""))
                {
                    Toast toast_massage_oct_error = Toast.makeText(OctToOther.this,"Please Enter Input",Toast.LENGTH_SHORT);
                    toast_massage_oct_error.setGravity(Gravity.BOTTOM,0,200);
                    toast_massage_oct_error.show();
                }
                else
                {
                    String decimal_output;
                    int intDecimal=0,decimal_integer_output;
                    double fracDecimal=0, twos = 1, decimal_double_output;
                    int length = octal_string.length();
                    char[] ch = new char[length];
                    int point = octal_string.indexOf(".");
                    if (point == -1) {
                        point = length;
                    }
                    for (int i = 0; i < length; i++) {
                        ch[i] = octal_string.charAt(i);
                    }
                    for (int i = point - 1; i >= 0; --i) {
                        intDecimal += (ch[i] - '0') * twos;
                        twos *= 8;
                    }
                    twos = 8;
                    for (int i = point + 1; i < length; ++i) {
                        fracDecimal += (ch[i] - '0') / twos;
                        twos *= 8;
                    }
                    if (fracDecimal <= 0) {
                        decimal_integer_output = intDecimal;
                        decimal_output = Integer.toString(decimal_integer_output);
                    } else {
                        decimal_double_output = intDecimal + fracDecimal;
                        decimal_output = Double.toString(decimal_double_output);
                    }
                    double result_decimal = toDeci(octal_string);
                    if(result_decimal>2147483647)
                    {
                        editText_oct.setText(null);
                        binaryOutput.setText("...........................");
                        decimalOutput.setText("...........................");
                        hexOutput.setText("...........................");
                        Toast toast_massage_oct_error = Toast.makeText(OctToOther.this,"Number Is Too Large! Overflow!",Toast.LENGTH_SHORT);
                        toast_massage_oct_error.setGravity(Gravity.BOTTOM,0,200);
                        toast_massage_oct_error.show();
                    }
                    else
                    {
                        decimalOutput.setText(decimal_output);
                        decimalOutput.setTypeface(null, Typeface.BOLD);

                        String binary = fromDeci(2,result_decimal);
                        binaryOutput.setText(binary);
                        binaryOutput.setTypeface(null, Typeface.BOLD);

                        String hexadecimal = fromDeci(16,result_decimal);
                        hexOutput.setText(hexadecimal);
                        hexOutput.setTypeface(null, Typeface.BOLD);

                    }
                }
            }
            if(v.getId()==R.id.button_reset)
            {
                editText_oct.setText(null);
                decimalOutput.setText("...........................");
                decimalOutput.setTypeface(null, Typeface.NORMAL);
                binaryOutput.setText("...........................");
                binaryOutput.setTypeface(null, Typeface.NORMAL);
                hexOutput.setText("...........................");
                hexOutput.setTypeface(null, Typeface.NORMAL);
            }

        }
        catch (Exception e) {
            Toast toast_massage_oct = Toast.makeText(OctToOther.this,"Please Enter Input",Toast.LENGTH_SHORT);
            toast_massage_oct.setGravity(Gravity.BOTTOM,0,200);
            toast_massage_oct.show();
        }
    }
}
