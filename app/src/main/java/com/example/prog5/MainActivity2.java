package com.example.prog5;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

/**
 * This activity is the 2nd screen of the app for the advice offered
 * @author Dhruv Patel, Shahil Patel
 */
public class MainActivity2 extends AppCompatActivity
{
    private String advice = "Normal";
    private ImageView image;
    private TextView bmiTranslate;
    private TextView advice_output;
    private HashMap<String, String> all_advices = new HashMap<String, String>();

    /**
     * Creates the second activity screen
     * @param savedInstanceState state of the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String advice_from_bmi = getIntent().getStringExtra("Advice_to_send");
        advice = advice_from_bmi;
        image = findViewById(R.id.imageView);
        bmiTranslate = findViewById(R.id.text);
        advice_output = findViewById(R.id.text2);

        all_advices.put("Underweight", "You should try to gain a little more weight");
        all_advices.put("Normal", "You're at a good stable weight for your height");
        all_advices.put("Overweight", "You should try to lose some weight to reduce BMI");
        all_advices.put("Obese", "You should try to lose a lot of weight to reduce BMI");

        if(advice.equals("Underweight"))
        {
            image.setImageResource(R.drawable.underweight);
            bmiTranslate.setText(advice);
            advice_output.setText(all_advices.get(advice));
        }
        else if(advice.equals("Normal"))
        {
            image.setImageResource(R.drawable.normal);
            bmiTranslate.setText(advice);
            advice_output.setText(all_advices.get(advice));
        }
        else if(advice.equals("Overweight"))
        {
            image.setImageResource(R.drawable.overweight);
            bmiTranslate.setText(advice);
            advice_output.setText(all_advices.get(advice));
        }
        else if(advice.equals("Obese"))
        {
            image.setImageResource(R.drawable.obese);
            bmiTranslate.setText(advice);
            advice_output.setText(all_advices.get(advice));
        }
        else{
            image.setImageResource(R.drawable.normal);
            bmiTranslate.setText("Normal");
        }
    }
}
