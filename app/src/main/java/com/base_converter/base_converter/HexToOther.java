package com.base_converter.base_converter;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HexToOther extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_hex;
    private TextView decimalOutput, binaryOutput, octalOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hex_to_other);

        editText_hex = findViewById(R.id.editText_hex);
        Button convertButton = findViewById(R.id.button_convert);
        decimalOutput = findViewById(R.id.textView_dec_output);
        binaryOutput = findViewById(R.id.textView_bin_output);
        octalOutput= findViewById(R.id.textView_oct_output);
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
            power = power * 16;
        }
        double num2=0;
        power = 16;
        for (i=point+1;i<len;i++)
        {
            num2 = num2 + val(str.charAt(i)) / power;
            power = power * 16;
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
                String hex_string = editText_hex.getText().toString();
                if(hex_string.matches(""))
                {
                    Toast toast_massage_hex = Toast.makeText(HexToOther.this,"Please Enter Input",Toast.LENGTH_SHORT);
                    toast_massage_hex.setGravity(Gravity.BOTTOM,0,200);
                    toast_massage_hex.show();
                }
                else
                {
                    String decimal_output;
                    int intDecimal = 0, decimal_integer_output;
                    double fracDecimal = 0, twos = 1, decimal_double_output;
                    int length = hex_string.length();
                    char[] ch = new char[length];
                    int point = hex_string.indexOf(".");
                    if (point == -1) {
                        point = length;
                    }
                    for (int i = 0; i < length; i++) {
                        ch[i] = hex_string.charAt(i);
                    }
                    for (int i = point - 1; i >= 0; --i) {
                        if(ch[i]=='A')
                        {
                            intDecimal += 10 * twos;
                            twos *= 16;
                        }
                        else if(ch[i]=='B')
                        {
                            intDecimal += 11 * twos;
                            twos *= 16;
                        }
                        else if(ch[i]=='C')
                        {
                            intDecimal += 12 * twos;
                            twos *= 16;
                        }
                        else if(ch[i]=='D')
                        {
                            intDecimal += 13 * twos;
                            twos *= 16;
                        }
                        else if(ch[i]=='E')
                        {
                            intDecimal += 14 * twos;
                            twos *= 16;
                        }
                        else if(ch[i]=='F')
                        {
                            intDecimal += 15 * twos;
                            twos *= 16;
                        }
                        else
                        {
                            intDecimal += (ch[i] - '0') * twos;
                            twos *= 16;
                        }
                    }
                    twos = 16;
                    for (int i = point + 1; i < length; ++i) {
                        if(ch[i]=='A')
                        {
                            fracDecimal += 10 / twos;
                            twos *= 16;
                        }
                        else if(ch[i]=='B')
                        {
                            fracDecimal += 11 / twos;
                            twos *= 16;
                        }
                        else if(ch[i]=='C')
                        {
                            fracDecimal += 12 / twos;
                            twos *= 16;
                        }
                        else if(ch[i]=='D')
                        {
                            fracDecimal += 13 / twos;
                            twos *= 16;
                        }
                        else if(ch[i]=='E')
                        {
                            fracDecimal += 14 / twos;
                            twos *= 16;
                        }
                        else if(ch[i]=='F')
                        {
                            fracDecimal += 15 / twos;
                            twos *= 16;
                        }
                        else
                        {
                            fracDecimal += (ch[i] - '0') / twos;
                            twos *= 16;
                        }
                    }
                    if (fracDecimal <= 0) {
                        decimal_integer_output = intDecimal;
                        decimal_output = Integer.toString(decimal_integer_output);
                    } else {
                        decimal_double_output = intDecimal + fracDecimal;
                        decimal_output = Double.toString(decimal_double_output);
                    }
                    double result_decimal = toDeci(hex_string);
                    if(result_decimal>2147483647)
                    {
                        editText_hex.setText(null);
                        decimalOutput.setText("...........................");
                        binaryOutput.setText("...........................");
                        octalOutput.setText("...........................");
                        Toast toast_massage_hex = Toast.makeText(HexToOther.this,"Number Is Too Large! Overflow!",Toast.LENGTH_SHORT);
                        toast_massage_hex.setGravity(Gravity.BOTTOM,0,200);
                        toast_massage_hex.show();
                    }
                    else
                    {
                        decimalOutput.setText(decimal_output);
                        decimalOutput.setTypeface(null, Typeface.BOLD);

                        String binary = fromDeci(2,result_decimal);
                        binaryOutput.setText(binary);
                        binaryOutput.setTypeface(null, Typeface.BOLD);

                        String octal = fromDeci(8,result_decimal);
                        octalOutput.setText(octal);
                        octalOutput.setTypeface(null, Typeface.BOLD);
                    }
                }

            }
            if(v.getId()==R.id.button_reset)
            {
                editText_hex.setText(null);
                decimalOutput.setText("...........................");
                decimalOutput.setTypeface(null, Typeface.NORMAL);
                binaryOutput.setText("...........................");
                binaryOutput.setTypeface(null, Typeface.NORMAL);
                octalOutput.setText("...........................");
                octalOutput.setTypeface(null, Typeface.NORMAL);
            }

        }
        catch (Exception e)
        {
            Toast toast_massage_hex = Toast.makeText(HexToOther.this,"Please Enter Input",Toast.LENGTH_SHORT);
            toast_massage_hex.setGravity(Gravity.BOTTOM,0,200);
            toast_massage_hex.show();
        }

    }
}
