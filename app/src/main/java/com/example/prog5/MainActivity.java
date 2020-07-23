package com.example.prog5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is the first screen of the BMI app and contains all the options the user will select in order
 * to calculate their BMI and get advice based on the BMI
 * @author Dhruv Patel, Shahil Patel
 */

public class MainActivity extends AppCompatActivity {
    String units = "";
    double bmi_cur = 0.0;

    /**
     * Launches the main screen of the app
     * @param savedInstanceState This is the app's state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup eng_or_metric = findViewById(R.id.radioButtons);
        Button bmi_calc = findViewById(R.id.bmiButton);
        Button advice = findViewById(R.id.adviceButton);
        final TextView bmi = findViewById(R.id.bmiCalculation);
        final EditText weight = findViewById(R.id.weight);
        final EditText height = findViewById(R.id.height);
        eng_or_metric.clearCheck();

        eng_or_metric.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            /**
             * Handles the user inputs for the Height, Weight, and English/Metric System
             * @param group this is the group of the two radio buttons
             * @param checkedId This is the ID we are checking in order to know which
             *                  Radio Button was clicked
             */
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if(checkedId == R.id.english)
                {
                    weight.setHint("Enter in pounds");
                    height.setHint("Enter in inches");
                    units = "eng";
                }
                else if(checkedId == R.id.metric)
                {
                    weight.setHint("Enter in kilograms");
                    height.setHint("Enter in meters");
                    units = "met";
                }
            }
        });

        bmi_calc.setOnClickListener(new View.OnClickListener()
        {
            /**
             * Handles the onclick feature of the Calculate BMI button
             * @param view is the view of the app
             */
            @Override
            public void onClick(View view)
            {
                //Checks if all fields are entered correctly with data
                //returns Toast error message
                if(weight.length() < 1 || height.length() < 1 || units.length() < 1)
                {
                    toast("You must enter the weight, height, and select the units", Toast.LENGTH_SHORT);
                    return;
                }

                //Calculate the BMI here
                String calcWeight = String.valueOf(weight.getText());
                String calcHeight = String.valueOf(height.getText());

                double bmiVal = 0.0;
                double w = Double.parseDouble(calcWeight);
                double h = Double.parseDouble(calcHeight);

                if(units.equals("eng"))
                {
                    bmiVal = (double) ((w * 703) / (h * h));
                }
                else if (units.equals("met"))
                {
                    bmiVal = (double) (w/ (h * h));
                }
                else{
                    bmiVal = -1;
                }
                bmiVal = Math.round(bmiVal*100.0) / 100.0;
                bmi_cur = bmiVal;

                String bmi_output = String.valueOf(bmiVal);
                bmi.setText(bmi_output);
                weight.setText("");
                height.setText("");
            }
        });
    }

    /**
     * @param bmi Reads the bmi parameter that was previously calculated
     * @return String msg as to what their BMI corresponds to
     */
    private String adviceHelper(double bmi)
    {
       String adviceMsg = "";
       if(bmi < 18.5)
       {
           adviceMsg += "Underweight";
       }
       else if(bmi >= 18.5 && bmi <= 24.9)
       {
           adviceMsg += "Normal";
       }
       else if(bmi >= 25.0 && bmi <= 29.9)
       {
           adviceMsg += "Overweight";
       }
       else if(bmi >= 30.0){
           adviceMsg += "Obese";
       }
       return adviceMsg;
    }

    /**
     * Handles the click feature of the advice button and opens the second screen which is
     * the second activity
     * @param v The app's view
     */
    public void giveAdvice(View v)
    {
        if(units.length() < 1 || bmi_cur < 0.0)
        {
            toast("You must enter the weight, height, and select the units", Toast.LENGTH_SHORT);
            return;
        }
        Intent ad_intent = new Intent(this, MainActivity2.class);
        String advice_msg = adviceHelper(bmi_cur);
        ad_intent.putExtra("Advice_to_send", advice_msg);
        startActivity(ad_intent);
    }

    /**
     * Helper method to create the Toast Error Message
     * @param msg The message to be outputed to the screen
     * @param len The duration of the message
     */
    private void toast(String msg, int len)
    {
        Context c = getApplicationContext();
        CharSequence error_msg = msg;
        Toast t = Toast.makeText(c, error_msg, len);
        t.show();
    }
}
