package com.turningpoint.attendance;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText edit,edit2,edit3,edit4;
    private DBHandler dbHandler;
    private DBHandler2 dbHandler2;
    String a,b,c,d;
    public static final String PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        dbHandler = new DBHandler(MainActivity.this);
        dbHandler2 = new DBHandler2(MainActivity.this);
        TextView title = (TextView) findViewById(R.id.textView2);

        edit2 = (EditText) findViewById(R.id.editTextText);
        edit3 = (EditText) findViewById(R.id.editTextDate);

        edit3.setShowSoftInputOnFocus(false);
        edit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                String dayOfMonth2;
                                if ( dayOfMonth < 10 ){
                                    dayOfMonth2 = "0" + dayOfMonth;
                                }else{
                                    dayOfMonth2 = "" + dayOfMonth;
                                }


                                String monthOfYear2;
                                if ( (monthOfYear + 1) < 10){
                                    monthOfYear2 = "0"+ ( monthOfYear +1 ) ;
                                }else {
                                    monthOfYear2 = ""+ ( monthOfYear + 1) ;
                                }
                                // on below line we are setting date to our edit text.
                                edit3.setText(dayOfMonth2 + "/" + (monthOfYear2) + "/" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); // Get preferences file (0 = no option flags set)
        boolean firstRun = settings.getBoolean("firstRun", true); // Is it first run? If not specified, use "true"

        if (firstRun) {
            Log.w("activity", "first time");


            SharedPreferences.Editor editor = settings.edit(); // Open the editor for our settings
            editor.putBoolean("firstRun", false); // It is no longer the first run
            editor.commit(); // Save all changed settings
        } else {
            Log.w("activity", "second time");
            startActivity(new Intent(MainActivity.this, atten.class));
            finish();
        }
        String date_n = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        edit3.setText(date_n);
    }
    public void sub(View view) throws ParseException {

        a = edit2.getText().toString();
        b = edit3.getText().toString();
         buildRecyclerView(a,b);
        startActivity(new Intent(MainActivity.this, atten.class));
        finish();
    }
    public void next(View view) throws ParseException {

        startActivity(new Intent(MainActivity.this, atten.class));
    }
    private void buildRecyclerView(String a, String b) throws ParseException {
        dbHandler.addNewCourse(a,b);

    }

}