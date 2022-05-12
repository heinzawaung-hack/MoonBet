package com.heinzawaung.moonbet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends Activity {
    double homeWinOne;
    double drawTwo;
    double awayWinThree;
    double accumulatorBet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText homeWin = findViewById(R.id.homeWin);
        EditText draw = findViewById(R.id.draw);
        EditText awayWin = findViewById(R.id.awayWin);
        TextView homeBetAmount = findViewById(R.id.homeBetAmount);
        TextView drawBetAmount = findViewById(R.id.drawBetAmount);
        TextView awayBetAmount = findViewById(R.id.awayBetAmount);
        TextView calculated = findViewById(R.id.percent);
        Button calculateButton = findViewById(R.id.calculate);
        TextView totalInvestAmount = findViewById(R.id.finalAmount);
        EditText accumulator = findViewById(R.id.accumulate);
        Button toBasketBall = findViewById(R.id.basketball);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            private final DecimalFormat decimalFormat = new DecimalFormat("0.00");
            @Override
            public void onClick(View view) {

                try {
                    homeWinOne = Double.parseDouble(homeWin.getText().toString());
                    drawTwo = Double.parseDouble(draw.getText().toString());
                    awayWinThree = Double.parseDouble(awayWin.getText().toString());
                    accumulatorBet = Double.parseDouble(accumulator.getText().toString());
                }catch (NumberFormatException e) {
                    System.out.println("Fuck");
                }

                double homeWinResult = (1 / homeWinOne) * 100;
                double drawResult = (1 / drawTwo) * 100;
                double awayWinResult = (1 / awayWinThree) * 100;

                double calculatedPercent = homeWinResult + drawResult + awayWinResult;
                calculated.setText((decimalFormat.format(calculatedPercent - 100)));

                double homeWinResultOne = homeWinResult * accumulatorBet;
                double drawResultOne = drawResult * accumulatorBet;
                double awayResultOne = awayWinResult * accumulatorBet;

                //To show total cost
                double finalInvest = homeWinResultOne + drawResultOne + awayResultOne;
                String setData = (decimalFormat.format(finalInvest));
                totalInvestAmount.setText(setData);

                String oneX3000 = (decimalFormat.format(homeWinResultOne));
                String drawX3000 = (decimalFormat.format(drawResultOne));
                String awayX3000 = (decimalFormat.format(awayResultOne));
                homeBetAmount.setText(oneX3000);
                drawBetAmount.setText(drawX3000);
                awayBetAmount.setText(awayX3000);
            }
        });

        toBasketBall.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, BasketballCalculatorActivity.class);
            startActivity(intent);
        });
    }
}