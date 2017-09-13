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
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView tipAmount;
    private RatingBar ratingBar;
    private EditText editText;

    public double mealPrice;
    public double tip;
    public float score;

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

    public float getScore() {
        this.score = ratingBar.getRating();
        return score;
    }

    public double getMealPrice() {
        String strPrice = editText.getText().toString();
        this.mealPrice = Double.parseDouble(strPrice);
        return mealPrice;
    }

    public double getTip()  {
        // mealPrice = this.mealPrice;
        // score = this.score;
        try {
            if (getScore() == 0) {
                return 0;
            } else if (getScore() >= 0.5 && getScore() <= 1.5) {
                return getMealPrice() * 0.10;
            } else if (getScore() >= 2 && getScore() <= 2.5) {
                return getMealPrice() * 0.13;
            } else if (getScore() >= 3 && getScore() <= 3.5) {
                return getMealPrice() * 0.17;
            } else if (getScore() >= 4 && getScore() <= 4.5) {
                return getMealPrice() * 0.20;
            } else if (getScore() == 5) {
                return getMealPrice() * 0.25;
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
        // String finalAmount = Double.toString(tip());
        DecimalFormat numberFormat = new DecimalFormat("0.00"); // Set decimal limit to two spaces
        this.tip = Double.valueOf(numberFormat.format(getTip()));
        tipAmount.setText("You should tip: $" + tip);
        /* This method sets the text in the TextView widget w/ the id tipAmount to a given String,
        which cocantenates with the variable 'tip'.
        NOTE: If the tip is equal to a value in which the digit in the second decimal place is equal
        to 0, the 0 digit will NOT be displayed. */
    }

}
