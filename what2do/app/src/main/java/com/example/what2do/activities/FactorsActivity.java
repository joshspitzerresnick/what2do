package com.example.what2do.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.what2do.R;

public class FactorsActivity extends Activity implements View.OnClickListener {
    private Button readyButton;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button continueButton, backButton;
    private SeekBar priceSeek, distanceSeek;
    private EditText priceField, distanceField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factors);

        readyButton = findViewById(R.id.factors_done);
        readyButton.setOnClickListener(this);

        priceField = findViewById(R.id.editTextPriceField);
        priceField.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    if (s!=null) {
                        float priceText = Float.parseFloat(s.toString());
                        int value = (int) priceText;
                        priceSeek.setProgress(value);
                        /*priceField.setText("");
                        priceField.append(s);*/
                    }
                }
                catch (Exception exception) {
                    priceSeek.setProgress(0);
                    exception.printStackTrace();
                }
            }
        });

        distanceField = findViewById(R.id.editTextDistanceField);
        distanceField.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    if (s!=null) {
                        float priceText = Float.parseFloat(s.toString());
                        int value = (int) priceText;
                        distanceSeek.setProgress(value);
                        /*distanceField.setText("");
                        distanceField.append(s);*/
                    }
                }
                catch (Exception exception) {
                    distanceSeek.setProgress(0);
                    exception.printStackTrace();
                }
            }
        });

        priceSeek = findViewById(R.id.PriceSlider);
        priceSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String newText = "" + progress;
                priceField.setText(newText);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        distanceSeek = findViewById(R.id.DistanceSlider);
        distanceSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String newText = "" + progress;
                distanceField.setText(newText);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    @Override
    public void onClick(View view) {
        //Log.d("what2do", "uhh HELLO?");
        if(view.getId() == R.id.factors_done) {
            createNewPopUp();
        }
    }

    public void createNewPopUp(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View factorsPopUp = getLayoutInflater().inflate(R.layout.factorspopup, null);

        continueButton = (Button) factorsPopUp.findViewById(R.id.popUpContinueButton);
        backButton = (Button) factorsPopUp.findViewById(R.id.popUpBackButton);

        dialogBuilder.setView(factorsPopUp);
        dialog = dialogBuilder.create();
        dialog.show();

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                dialog.dismiss();
                setResult(RESULT_OK);
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                dialog.dismiss();
            }
        });


    }



}
