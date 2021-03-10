package com.base_converter.base_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class binaryToGray extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_binary_input;
    private TextView gray_output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_to_gray);
        editText_binary_input=findViewById(R.id.editText_bin_code_input);
        Button convertButton = findViewById(R.id.button_convert);
        Button reset = findViewById(R.id.button_reset);
        gray_output=findViewById(R.id.textView_gray_output);

        convertButton.setOnClickListener(this);
        reset.setOnClickListener(this);

    }
    public static char xor_c(char a, char b)
    {
        return (a == b) ? '0' : '1';
    }

    @Override
    public void onClick(View v) {
        try {

            if(v.getId()==R.id.button_convert)
            {
                String binary_code_string = editText_binary_input.getText().toString();
                if(binary_code_string.matches(""))
                {
                    Toast toast_massage_bin_error = Toast.makeText(binaryToGray.this,"Please Enter Input",Toast.LENGTH_SHORT);
                    toast_massage_bin_error.setGravity(Gravity.BOTTOM,0,200);
                    toast_massage_bin_error.show();
                }
                else if(binary_code_string.length()>=32)
                {
                    Toast toast_massage_bin_error = Toast.makeText(binaryToGray.this,"Oops! Input is too large!",Toast.LENGTH_SHORT);
                    toast_massage_bin_error.setGravity(Gravity.BOTTOM,0,200);
                    toast_massage_bin_error.show();
                }
                else
                {
                    StringBuilder gray = new StringBuilder();
                    gray.append(binary_code_string.charAt(0));
                    for (int i = 1; i < binary_code_string.length(); i++) {
                        gray.append(xor_c(binary_code_string.charAt(i - 1),
                                binary_code_string.charAt(i)));
                    }
                    gray_output.setText(gray.toString());
                }
            }
            if(v.getId()==R.id.button_reset)
            {
                editText_binary_input.setText(null);
                gray_output.setText("...........................");
            }
        }
        catch (Exception e)
        {
            Toast toast_massage_bin_to_gray = Toast.makeText(binaryToGray.this,"Please Enter Input",Toast.LENGTH_SHORT);
            toast_massage_bin_to_gray.setGravity(Gravity.BOTTOM,0,200);
            toast_massage_bin_to_gray.show();
        }

    }
}
