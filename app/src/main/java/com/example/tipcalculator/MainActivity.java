package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textViewProgress;
    EditText userValue;
    TextView textViewTip;
    TextView totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTip = findViewById(R.id.textViewTipValue);
        userValue = findViewById(R.id.editTextUserValue);

        totalAmount = findViewById(R.id.textViewAmountTotalValue);

        seekBar = findViewById(R.id.seekBar);
        textViewProgress = findViewById(R.id.textViewSeekBar);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        userValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                double totalbilled = 0;
                String userV = userValue.getText().toString();
                if (userV.isEmpty()) {
                    //clear total value
                    //set text to tip empty string
                    // show the toast
                    totalAmount.setText("");
                    textViewTip.setText("");

                    Toast.makeText(MainActivity.this, getResources().getString(R.string.toastValue), Toast.LENGTH_SHORT).show();
                } else {

                    if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton10) {

                        try {
                            textViewTip.setText("10");

                            totalbilled = Double.parseDouble(userV) * 1.10;

                            totalAmount.setText(String.valueOf(Math.round(totalbilled)));


                        } catch (Exception e) {

                            totalAmount.setText("");
                            textViewTip.setText("");
                        }

                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton15) {
                        try {
                            textViewTip.setText("15");

                            totalbilled = Integer.parseInt(userV) * 1.15;
                            totalAmount.setText(String.valueOf(Math.round(totalbilled)));
                        } catch (Exception e) {

                            totalAmount.setText("");
                            textViewTip.setText("");
                        }
                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton18) {
                        try {
                            textViewTip.setText("18");
                            totalbilled = Integer.parseInt(userV) * 1.18;
                            totalAmount.setText(String.valueOf(Math.round(totalbilled)));
                        } catch (Exception e) {

                            totalAmount.setText("");
                            textViewTip.setText("");
                        }
                    } else {
                        try {

                            int temp = seekBar.getProgress();
                            double tipValue = 1 + (temp * 0.01);
                            double total = Double.parseDouble(userV) * tipValue;
                            totalAmount.setText(String.valueOf(Math.round(total)));
                            textViewTip.setText(String.valueOf(temp));
                        } catch (Exception e) {

                            totalAmount.setText("");
                            textViewTip.setText("");
                        }
                    }


                }
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                double totalbilled = 0;
                if (checkedId == R.id.radioButton10) {

                    try {
                        textViewTip.setText("10");

                        totalbilled = Double.parseDouble(userValue.getText().toString()) * 1.10;

                        totalAmount.setText(String.valueOf(Math.round(totalbilled)));

                    } catch (Exception e) {

                        totalAmount.setText("");
                        textViewTip.setText("");

                    }

                } else if (checkedId == R.id.radioButton15) {
                    try {
                        textViewTip.setText("15");

                        totalbilled = Integer.parseInt(userValue.getText().toString()) * 1.15;
                        totalAmount.setText(String.valueOf(Math.round(totalbilled)));
                    } catch (Exception e) {
                        Log.d(e.toString(), "radio buttom 15.0");
                        totalAmount.setText("");
                        textViewTip.setText("");

                    }
                } else if (checkedId == R.id.radioButton18) {
                    try {
                        textViewTip.setText("18");
                        totalbilled = Integer.parseInt(userValue.getText().toString()) * 1.18;
                        totalAmount.setText(String.valueOf(Math.round(totalbilled)));
                    } catch (Exception e) {

                        totalAmount.setText("");
                        textViewTip.setText("");
                    }
                } else {

                    try {

                        int temp = seekBar.getProgress();
                        double tipValue = 1 + (temp * 0.01);
                        double total = Double.parseDouble(userValue.getText().toString()) * tipValue;
                        totalAmount.setText(String.valueOf(Math.round(total)));
                        textViewTip.setText(String.valueOf(temp));
                    } catch (Exception e) {
                        Log.d(e.toString(), "OnProgressChanged of seek bar catch");
                        totalAmount.setText("");
                        textViewTip.setText("");
                    }

                }
            }
        });


        Button exit = findViewById(R.id.buttonExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAndRemoveTask();
                }
                System.exit(0);
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewProgress.setText(String.valueOf(progress));
                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonCustom) {

                    try {
                        double tipValue = 1 + (progress * 0.01);
                        double total = Double.parseDouble(userValue.getText().toString()) * tipValue;


                        totalAmount.setText(String.valueOf(Math.round(total)));
                        textViewTip.setText(String.valueOf(progress));
                    } catch (Exception e) {
                        Log.d(e.toString(), "OnProgressChanged of seek bar catch");
                        totalAmount.setText("");
                        textViewTip.setText("");
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}