package com.base_converter.base_converter;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hotchemi.android.rate.AppRate;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button dec_to_other_button = findViewById(R.id.dec_to_other);
        Button bin_to_other_button = findViewById(R.id.bin_to_other);
        Button oct_to_other_button = findViewById(R.id.oct_to_other);
        Button hex_to_other_button = findViewById(R.id.hex_to_other);
        Button any_to_any_button = findViewById(R.id.any_to_any);
        Button bin_to_gray_button = findViewById(R.id.bin_to_gray);
        Button gray_to_binary_button = findViewById(R.id.gray_to_bin);

        dec_to_other_button.setOnClickListener(this);
        bin_to_other_button.setOnClickListener(this);
        oct_to_other_button.setOnClickListener(this);
        hex_to_other_button.setOnClickListener(this);
        any_to_any_button.setOnClickListener(this);
        bin_to_gray_button.setOnClickListener(this);
        gray_to_binary_button.setOnClickListener(this);

        AppRate.with(this)
                .setInstallDays(1)
                .setLaunchTimes(3)
                .setRemindInterval(2)
                .monitor();
        AppRate.showRateDialogIfMeetsConditions(this);
    }



    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.dec_to_other)
        {
            Intent intent_dec = new Intent(MainActivity.this,DecToOther.class);
            startActivity(intent_dec);
        }
        if(v.getId()==R.id.bin_to_other)
        {
            Intent intent_bin = new Intent(MainActivity.this,BinToOther.class);
            startActivity(intent_bin);
        }

        if(v.getId()==R.id.oct_to_other)
        {
            Intent intent_oct = new Intent(MainActivity.this,OctToOther.class);
            startActivity(intent_oct);
        }
        if(v.getId()==R.id.hex_to_other)
        {
            Intent intent_hex = new Intent(MainActivity.this,HexToOther.class);
            startActivity(intent_hex);
        }
        if(v.getId()==R.id.any_to_any)
        {
            Intent intent_any_to_any = new Intent(MainActivity.this,anyBaseToAnyBase.class);
            startActivity(intent_any_to_any);
        }
        if(v.getId()==R.id.bin_to_gray)
        {
            Intent intent_bin_to_gray = new Intent(MainActivity.this,binaryToGray.class);
            startActivity(intent_bin_to_gray);
        }
        if(v.getId()==R.id.gray_to_bin)
        {
            Intent intent_gray_to_bin = new Intent(MainActivity.this,grayToBinary.class);
            startActivity(intent_gray_to_bin);
        }


    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialogbuilder;
        alertDialogbuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogbuilder.setTitle(R.string.alert_title);
        alertDialogbuilder.setMessage(R.string.alert_massage);
        alertDialogbuilder.setCancelable(false);

        alertDialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogbuilder.create();
        alertDialog.show();
    }
}
