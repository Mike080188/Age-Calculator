package com.example.agecalculator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText mEditTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        EditText mEditTextLastName = (EditText) findViewById(R.id.editTextLastName);
        EditText mEditTextBirthdate = (EditText) findViewById(R.id.editTextBirthdate);
        Button mButtonCalcAge = findViewById(R.id.buttonCalcAge);

        mButtonCalcAge.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String firstName = mEditTextFirstName.getText().toString();
                String lastName = mEditTextLastName.getText().toString();
                String birthdate = mEditTextBirthdate.getText().toString();

                // Validate name fields are filled
                if (firstName.length() < 1 || lastName.length() < 1) {
                    Toast.makeText(MainActivity.this, "Please enter first and last name", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Validate Birthdate
                if (!validateDate(birthdate)) {
                    Toast.makeText(MainActivity.this, "Please enter a valid D.O.B with format mm/dd/yyyy", Toast.LENGTH_SHORT).show();
                    return;
                }

                int age = calcAge(birthdate);
                String toastText = firstName + " " + lastName + "'s " + "age is " + age;
                Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static boolean validateDate(String strDate) {
        /* Check if date is 'null' */
        if (strDate.trim().equals("")) {
            return false;
        }
        /* Date is not 'null' */
        else {
            /*
             * Set preferred date format */
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            dateFormat.setLenient(false);
            /* Create Date object
             * parse the string into date
             */
            try {
                Date javaDate = dateFormat.parse(strDate);
                System.out.println(strDate + " is valid date format");
            }
            /* Date format is invalid */ catch (ParseException e) {
                System.out.println(strDate + " is Invalid Date format");
                return false;
            }
            /* Return true if date format is valid */
            return true;
        }
    }

    public static int calcAge(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);

        LocalDate bDay = null;

        try {
            bDay = dateFormat.parse(strDate).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
        catch (ParseException e) {
            System.out.println("Error while parsing date: " + strDate);
        }

        LocalDate today = LocalDate.now(ZoneId.systemDefault());

        // get the years between entered bithdate and today
        int years = Period.between(bDay, today).getYears();
        return years;
    }
}

