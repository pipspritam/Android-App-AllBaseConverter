package com.base_converter.base_converter;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class anyBaseToAnyBase extends AppCompatActivity implements View.OnClickListener {

    String[] bases_options;
    private Spinner initial_base_spinner, destination_base_spinner;
    private EditText number_input;
    private TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_any_base_to_any_base);

        bases_options = getResources().getStringArray(R.array.bases);

        initial_base_spinner = findViewById(R.id.initial_base_spinner_id);
        destination_base_spinner = findViewById(R.id.destination_base_spinner_id);
        number_input = findViewById(R.id.editText_any_base_number_input);
        Button convert = findViewById(R.id.button_convert);
        Button reset = findViewById(R.id.button_reset);
        result = findViewById(R.id.textView_any_to_any_output);

        ArrayAdapter<String> adapter_options = new ArrayAdapter<>(this, R.layout.base_options, R.id.base_options_text_view, bases_options);
        initial_base_spinner.setAdapter(adapter_options);
        destination_base_spinner.setAdapter(adapter_options);

        convert.setOnClickListener(this);
        reset.setOnClickListener(this);

    }
    static int val(char c) {
        if (c >= '0' && c <= '9')
            return (int)c - '0';
        else
            return (int)c - 'A' + 10;
    }
    static double toDeci(String str, int base) {
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
            if (val(str.charAt(i)) >= base)
            {
                return -1;
            }
            num += val(str.charAt(i)) * power;
            power = power * base;
        }
        double num2=0;
        power = base;
        for (i=point+1;i<len;i++)
        {
            if (val(str.charAt(i)) >= base)
            {
                return -1;
            }
            num2 = num2 + val(str.charAt(i)) / power;
            power = power * base;
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

    static String fromDeci(int base1, double inputNum)
    {
        StringBuilder s = new StringBuilder();
        int Integral = (int) inputNum;
        double fractional = inputNum - Integral;
        while (Integral > 0)
        {
            s.append(reVal(Integral % base1));
            Integral /= base1;
        }
        StringBuilder ix = new StringBuilder();
        ix.append(s);
        StringBuilder output1 = new StringBuilder(new String(ix.reverse()));
        if (fractional > 0)
        {
            output1.append('.');
        }
        while (fractional > 0)
        {
            fractional *= base1;
            int fractional_bit = (int) fractional;
            output1.append(reVal(fractional_bit));
            fractional -= fractional_bit;
        }
        return output1.toString();
    }

    @Override
    public void onClick(View v) {

        try {
            if(v.getId()==R.id.button_convert)
            {
                String input_string = number_input.getText().toString();
                    String initial_base_string = initial_base_spinner.getSelectedItem().toString();
                    String destination_base_string = destination_base_spinner.getSelectedItem().toString();
                    int initial_base_int = Integer.parseInt(initial_base_string);
                    int destination_base_int = Integer.parseInt(destination_base_string);
                    if(input_string.equals(""))
                    {
                        Toast toast_massage_any_to_any = Toast.makeText(anyBaseToAnyBase.this,"Please Enter Input",Toast.LENGTH_SHORT);
                        toast_massage_any_to_any.setGravity(Gravity.BOTTOM,0,200);
                        toast_massage_any_to_any.show();
                    }
                    else
                    {
                        double result_decimal = toDeci(input_string, initial_base_int);
                        if(result_decimal==-1)
                        {
                            Toast toast_massage_any_to_any_error = Toast.makeText(anyBaseToAnyBase.this,"Oops! Invalid Input!!",Toast.LENGTH_SHORT);
                            toast_massage_any_to_any_error.setGravity(Gravity.BOTTOM,0,200);
                            toast_massage_any_to_any_error.show();
                            number_input.setText(null);
                            result.setText("...........................");
                        }
                        else if(result_decimal>2147483647)
                        {
                            Toast toast_massage_any_to_any_error = Toast.makeText(anyBaseToAnyBase.this,"Oops! Number is too large!",Toast.LENGTH_SHORT);
                            toast_massage_any_to_any_error.setGravity(Gravity.BOTTOM,0,200);
                            toast_massage_any_to_any_error.show();
                            number_input.setText(null);
                            result.setText("...........................");
                        }
                        else
                        {
                            if(destination_base_int==10)
                            {
                                String output_final_10 = Double.toString(result_decimal);
                                result.setText(output_final_10);
                            }
                            String output_final = fromDeci(destination_base_int,result_decimal);
                            result.setText(output_final);
                            result.setTypeface(null, Typeface.BOLD);
                        }
                    }


                }
            if(v.getId()==R.id.button_reset)
            {
                number_input.setText(null);
                result.setText("...........................");
                result.setTypeface(null, Typeface.NORMAL);
            }
        }
        catch (Exception e5)
        {
            Toast toast_massage_any_to_any = Toast.makeText(anyBaseToAnyBase.this,"Please Enter Input",Toast.LENGTH_SHORT);
            toast_massage_any_to_any.setGravity(Gravity.BOTTOM,0,200);
            toast_massage_any_to_any.show();
        }
    }
}
