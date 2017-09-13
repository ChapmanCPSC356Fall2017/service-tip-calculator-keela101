package com.chapman.seankeelan.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tipAmount;
    private RatingBar ratingBar;
    private EditText editText;

    public double mealPrice;
    public double tip;
    public int score;

    //TextView tv = (TextView) findViewById(R.id.textView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tipAmount = (TextView) findViewById(R.id.tipAmount);
        this.ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        this.editText = (EditText) findViewById(R.id.editText);
    }

    private class UnknownException extends Exception {}

    public int getScore() {
        this.score = ratingBar.getNumStars();
        return score;
    }

    public double getMealPrice() {
        String strPrice = editText.getText().toString();
        this.mealPrice = Double.parseDouble(strPrice);
        return mealPrice;
    }

    public double tip()  {
        // mealPrice = this.mealPrice;
        // score = this.score;
        try {
            if (score == 0) {
                return 0;
            } else if (score >= 1 && score <= 3) {
                return mealPrice * 0.10;
            } else if (score >= 4 && score <= 5) {
                return mealPrice * 0.13;
            } else if (score >= 6 && score <= 7) {
                return mealPrice * 0.17;
            } else if (score >= 8 && score <= 9) {
                return mealPrice * 0.20;
            } else if (score == 10) {
                return mealPrice * 0.25;
            }
            else {
                throw new UnknownException();
            }
        }
        catch (UnknownException e) {
            System.out.println("Unknown error found.");
            return 0;
        }
    }

    public void calcTip(View view) {
        String finalAmount = Double.toString(tip());
        tipAmount.setText("You should tip: " + finalAmount);
    }

}
