package com.example.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amt;
    EditText nopax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totbill;
    TextView eachpay;
    Button split;
    Button reset;
    EditText discount;
    RadioButton cash;
    RadioButton payn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amt = findViewById(R.id.inputamt);
        nopax = findViewById(R.id.inputnopax);
        svs = findViewById(R.id.svs);
        gst = findViewById(R.id.gst);
        split = findViewById(R.id.split);
        reset = findViewById(R.id.reset);
        discount = findViewById(R.id.inputdiscount);
        totbill =  findViewById(R.id.totalbill);
        cash = findViewById(R.id.rbcash);
        payn = findViewById(R.id.rbpayn);

    split.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (amt.getText().toString().trim().length() != 0 && nopax.getText().toString().trim().length() != 0) {
                String amt1 = amt.getText().toString();
                double newamt = 0.0;
                if (svs.isChecked() && !gst.isChecked()) {
                    newamt = Double.parseDouble(amt1) * 1.1;
                    //String totaltext = Double.toString(newamt);
                    // totbill.setText(totaltext);
                } else if (!svs.isChecked() && gst.isChecked()) {
                    newamt = Double.parseDouble(amt1) * 1.07;
                } else {
                    newamt = Double.parseDouble(amt1) * 1.17;
                }

                if (discount.getText().toString().trim().length() != 0) {

                    newamt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                }
                totbill.setText("$" + String.format("%2f", newamt));
                int noppl = Integer.parseInt(nopax.getText().toString());
                if (cash.isChecked()) {
                    eachpay.setText("$" + String.format("%2f", newamt / noppl + "in cash"));
                } else {
                    eachpay.setText("$" + String.format("%2f", newamt/noppl + "via PayNow to 86881807"));
                }
                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        amt.setText("");
                        nopax.setText("");
                        svs.setChecked(false);
                        gst.setChecked(false);
                        discount.setText("");

                    }
                });

            }
        }
    });
}}

