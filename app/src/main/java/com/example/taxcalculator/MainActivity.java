package com.example.taxcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private EditText num;
    private TextView txt;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = findViewById(R.id.number);
        txt = findViewById(R.id.txt);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (num.getText().toString().isEmpty())
                {
                    num.setError("Please enter your salary");
                }

                else
                {
                    txt.setText("");
                    int salary = Integer.parseInt(num.getText().toString());

                    double yearlySalary = getYearlySalary(salary);

                    if (yearlySalary>0 && yearlySalary<=200000)
                    {
                        double totalTaxAmount = getFirstTax(yearlySalary);
                        txt.setText("Your yearly tax amount is: "+totalTaxAmount);
                    }

                    else if (yearlySalary>200000 && yearlySalary<=350000)
                    {
                        // creating salary amount to find the 15% tax
                        double secondAmount = yearlySalary - 200000;

                        // creating salary amount to find the 1% tax of (200000)
                        double firstAmount = yearlySalary - secondAmount;

                        // getting tax amounts both 1% and 15%
                        double taxAmount1 = getFirstTax(firstAmount);
                        double taxAmount2 = getSecondTax(secondAmount);
                        double totalTaxAmount =  taxAmount1 + taxAmount2;
                        txt.setText("Your yearly tax amount is: "+totalTaxAmount);
                    }

                    else
                    {
                        //creating salary amount to find the 25% tax
                        double thirdAmount  = yearlySalary - 200000 - 150000;

                        // creating salary amount to find the 15% tax of (150000)
                        double secondAmount = yearlySalary - 200000 - thirdAmount;

                        // creating salary amount to find the 1% tax of (200000)
                        double firstAmount = yearlySalary - secondAmount - thirdAmount;

                        // getting tax amounts both 1% and 15% and 25%
                        double taxAmount1 = getFirstTax(firstAmount);
                        double taxAmount2 = getSecondTax(secondAmount);
                        double taxAmount3 = getThirdtax(thirdAmount);
                        double totalTaxAmount  = taxAmount1 + taxAmount2 + taxAmount3;
                        txt.setText("Your yearly tax amount is: "+totalTaxAmount);
                    }

                }
            }
        });
    }

    private double getYearlySalary(double monthlySalary)
    {
        return monthlySalary*12;
    }

    private double getFirstTax(double salary)
    {
        return (salary*1)/100;
    }

    private double getSecondTax(double salary)
    {
        return (salary*15)/100;
    }

    private double getThirdtax(double salary)
    {
        return (salary*25)/100;
    }
}
