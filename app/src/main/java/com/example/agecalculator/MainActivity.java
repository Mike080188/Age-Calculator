package com.example.agecalculator;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.format.DateFormat;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.agecalculator.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

//    private TextView mTextViewTitle;
//    private TextView mTextViewResult;
//    private EditText mEditTextNumber;
//    private Button mButtonCheckIfPrime;

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextBirthdate;
    private Button buttonCalcAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        View mEditTextFirstName = findViewById(R.id.editTextFirstName);
        EditText mEditTextFirstName = (EditText)findViewById(R.id.editTextFirstName);
        EditText mEditTextLastName = (EditText)findViewById(R.id.editTextLastName);

        EditText mEditTextBirthdate = (EditText)findViewById(R.id.editTextBirthdate);
        Button mButtonCalcAge = findViewById(R.id.buttonCalcAge);

        mButtonCalcAge.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String firstName = mEditTextFirstName.getText().toString();
                String lastName = mEditTextLastName.getText().toString();
                String birthdate = mEditTextBirthdate.getText().toString();
                if(firstName.length() < 1 || lastName.length() < 1 ) {
                    Toast.makeText(MainActivity.this, "Please enter first and last name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!validateDate(birthdate)){
                    Toast.makeText(MainActivity.this, "Please enter a valid D.O.B with format mm/dd/yyyy", Toast.LENGTH_SHORT).show();
                    return;
                }

                String toastText = "";
                Toast.makeText(MainActivity.this, mEditTextFirstName.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
//    public static boolean isDateValid(String date, String format)
//    {
//        try {
//            DateFormat df = new SimpleDateFormat(format);
//            df.setLenient(false);
//            df.parse(date);
//            return true;
//        } catch (ParseException e) {
//            return false;
//        }
//    }
    public static boolean validateDate(String strDate)
    {
        /* Check if date is 'null' */
        if (strDate.trim().equals(""))
        {
            return false;
        }
        /* Date is not 'null' */
        else
        {
            /*
             * Set preferred date format,
             * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            dateFormat.setLenient(false);
            /* Create Date object
             * parse the string into date
             */
            try
            {
                Date javaDate = dateFormat.parse(strDate);
                System.out.println(strDate+" is valid date format");
            }
            /* Date format is invalid */
            catch (ParseException e)
            {
                System.out.println(strDate+" is Invalid Date format");
                return false;
            }
            /* Return true if date format is valid */
            return true;
        }
    }

//        mTextViewResult = findViewById(R.id.textViewResult);

//public class MainActivity extends AppCompatActivity {
//
//    private AppBarConfiguration appBarConfiguration;
//    private ActivityMainBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        setSupportActionBar(binding.toolbar);
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
}