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

public class grayToBinary extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_gray_input;
    private TextView binary_output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gray_to_binary);
        editText_gray_input=findViewById(R.id.editText_gray_code_input);
        Button convertButton = findViewById(R.id.button_convert);
        Button reset = findViewById(R.id.button_reset);
        binary_output=findViewById(R.id.textView_bin_output);

        convertButton.setOnClickListener(this);
        reset.setOnClickListener(this);
    }
    public static char flip(char c)
    {
        return (c == '0') ? '1' : '0';
    }

    @Override
    public void onClick(View v) {
        try {

            if(v.getId()==R.id.button_convert)
            {
                String gray_code_string = editText_gray_input.getText().toString();
                if(gray_code_string.matches(""))
                {
                    Toast toast_massage_gray_error = Toast.makeText(grayToBinary.this,"Please Enter Input",Toast.LENGTH_SHORT);
                    toast_massage_gray_error.setGravity(Gravity.BOTTOM,0,200);
                    toast_massage_gray_error.show();
                }
                else if(gray_code_string.length()>=32)
                {
                    Toast toast_massage_gray_error = Toast.makeText(grayToBinary.this,"Oops! Input is too large!",Toast.LENGTH_SHORT);
                    toast_massage_gray_error.setGravity(Gravity.BOTTOM,0,200);
                    toast_massage_gray_error.show();
                }
                else
                {
                    StringBuilder binary = new StringBuilder();
                    binary.append(gray_code_string.charAt(0));
                    for (int i = 1; i < gray_code_string.length(); i++) {
                        if (gray_code_string.charAt(i) == '0')
                            binary.append(binary.charAt(i - 1));
                        else
                            binary.append(flip(binary.charAt(i - 1)));
                    }
                    binary_output.setText(binary.toString());
                    binary_output.setTypeface(null, Typeface.BOLD);

                }
            }
            if(v.getId()==R.id.button_reset)
            {
                editText_gray_input.setText(null);
                binary_output.setText("...........................");
                binary_output.setTypeface(null, Typeface.NORMAL);
            }
        }
        catch (Exception e)
        {
            Toast toast_massage_gray_to_bin = Toast.makeText(grayToBinary.this,"Please Enter Input",Toast.LENGTH_SHORT);
            toast_massage_gray_to_bin.setGravity(Gravity.BOTTOM,0,200);
            toast_massage_gray_to_bin.show();
        }
    }
}
