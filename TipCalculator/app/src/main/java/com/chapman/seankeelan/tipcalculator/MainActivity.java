// Sean Keelan
// ID: 1928053
// keela101@mail.chapman.edu
// CPSC 356-01
// Assignment #0: Tip Calculator
/* Description: The following application calculates the amount of money (in US dollars) a user
  * should tip his/her server at the end of a meal. The user enters the price of the meal, selects
  * a score between 0 stars and 5 stars, which can be incremented in intervals of 0.5, and clicks
  * on the Calculate button to calculate the amount of money with which the user should tip the
  * server. This application can be used multiple times. */

package com.chapman.seankeelan.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RatingBar;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView tipAmount;
    private RatingBar ratingBar;
    private EditText editText;

    public double mealPrice;
    public double tip;
    public float score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign the following private variables to their corresponding widgets, as indicated by
        // their IDs.
        this.tipAmount = (TextView) findViewById(R.id.tipAmount);
        this.ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        this.editText = (EditText) findViewById(R.id.editText);
    }

    // Catches an unknown exception, and returns a tip amount of 0.
    private class UnknownException extends Exception {}

    /* The method getScore() calculates a float score based on the current rating indicated by the
    RatingBar widget (e.g. 2.5/5 stars = 2.5, 3.0/5 stars = 3.0). getScore() returns the value of
    ratingBar.getRating.
     */
    public float getScore() {
        this.score = ratingBar.getRating();
        return score;
    }

    /* The method getMealPrice() converts the user's input decimal number, which is introduced as
    a string, and converts it to a double. getMealPrice() returns the user input as a double.
     */
    public double getMealPrice() {
        String strPrice = editText.getText().toString();
        this.mealPrice = Double.parseDouble(strPrice);
        return mealPrice;
    }

    /* The method getTip() performs tip calculations based on a given price and score. In this case,
    * the price and score values will be equal to the values returned by getMealPrice() and
    * getScore() respectively. */
    public double getTip(double price, float score)  {
        try {
            if (score == 0) {
                return 0;
            } else if (score >= 0.5 && score <= 1.5) {
                return price * 0.10;
            } else if (score >= 2 && score <= 2.5) {
                return price * 0.13;
            } else if (score >= 3 && score <= 3.5) {
                return price * 0.17;
            } else if (score >= 4 && score <= 4.5) {
                return price * 0.20;
            } else if (score == 5) {
                return price * 0.25;
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

    /* This method sets the text in the TextView widget w/ the id tipAmount to a given String,which
    cocantenates with the variable 'tip'.
    NOTE: If the tip is equal to a value in which the digit in the hundredths place is equalto 0,
    the 0 digit will NOT be displayed. This is NOT an intended function.*/
    public void calcTip(View view) {
        DecimalFormat numberFormat = new DecimalFormat("0.00"); // Set decimal limit to two spaces
        this.tip = Double.valueOf(numberFormat.format(getTip(getMealPrice(), getScore())));
        tipAmount.setText("You should tip: $" + tip);

    }

}
