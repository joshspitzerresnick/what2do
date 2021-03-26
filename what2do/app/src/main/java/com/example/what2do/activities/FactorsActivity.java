package com.example.what2do.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.what2do.R;

public class FactorsActivity extends Activity implements View.OnClickListener {
    private Button readyButton;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button continueButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factors);

        readyButton = findViewById(R.id.factors_done);
        readyButton.setOnClickListener(this);

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
