package com.chapman.seankeelan.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public float mealPrice;
    // public float tipAmount;
    public int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    class UnknownException extends Exception {}

    public double tipAmount()  {
        mealPrice = this.mealPrice;
        score = this.score;
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
}
