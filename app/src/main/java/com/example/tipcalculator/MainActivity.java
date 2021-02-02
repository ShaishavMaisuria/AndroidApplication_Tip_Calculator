package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

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

        textViewTip =findViewById(R.id.textViewTipValue);
        userValue=findViewById(R.id.editTextUserValue);

        totalAmount=findViewById(R.id.textViewAmountTotalValue);

        seekBar =findViewById(R.id.seekBar);
        textViewProgress=findViewById(R.id.textViewSeekBar);
        RadioGroup radioGroup =findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                double totalbilled=0;
                if(checkedId==R.id.radioButton10){

                    try {
                        textViewTip.setText(String.valueOf("10.0"));
                        Log.d("demo","10.0");
                        totalbilled=Double.parseDouble(userValue.getText().toString()) * 1.10;
                        Log.d("demo","totalBilled");
                        totalAmount.setText(String.valueOf(totalbilled) + new String(" %"));
                    }catch (Exception e){
                        Log.d(e.toString(),"Radio button 10.0");

                    }

                } if(checkedId==R.id.radioButton15){
                    try {
                        textViewTip.setText(String.valueOf("15.0 %"));

                        totalbilled = Integer.parseInt(userValue.getText().toString()) * 1.15;
                        totalAmount.setText(String.valueOf(totalbilled) + new String(" %"));
                    }
                    catch (Exception e){
                        Log.d(e.toString(),"radio buttom 15.0");
                    }
                } if(checkedId==R.id.radioButton18){
                  try{
                    textViewTip.setText(String.valueOf("18.0 %"));
                    totalbilled=Integer.parseInt(userValue.getText().toString())*1.18;
                    totalAmount.setText(String.valueOf(totalbilled) + new String(" %"));
                }
                    catch (Exception e){
                        Log.d(e.toString(),"radio buttom 18.0");
                }
                } if(checkedId==R.id.radioButtonCustom){
                    try {
                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                textViewProgress.setText(String.valueOf(progress) + new String(" %"));
                                try {
                                    double tipValue = 1 + (progress * 0.01);
                                    double total = Double.parseDouble(userValue.getText().toString()) * tipValue;
                                    totalAmount.setText(String.valueOf(total));
                                    textViewTip.setText(String.valueOf(progress) + new String(" %"));
                                } catch (Exception e) {
                                    Log.d(e.toString(), "OnProgressChanged of seek bar catch");
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
                    catch(Exception e){
                        Log.d(e.toString(),"custom buttom");
                    }
                }
            }
        });


        Button exit =findViewById(R.id.buttonExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                   finishAndRemoveTask();
                }
                System.exit(0);
            }
        });
    }
}