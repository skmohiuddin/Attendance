package com.turningpoint.attendance;

import static android.view.View.VISIBLE;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class atten extends AppCompatActivity {
    EditText edit2,edit3;
    CheckBox chk1,chk2;
    Button btn;
    private DBHandler dbHandler;
    private DBHandler2 dbHandler2;
    String a,b,d;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.atten);
        dbHandler = new DBHandler(atten.this);
        dbHandler2 = new DBHandler2(atten.this);
        edit2 = (EditText) findViewById(R.id.editTextText2);
        edit3 = (EditText) findViewById(R.id.editTextText3);
        edit2.setShowSoftInputOnFocus(false);

        edit2.setOnClickListener(new View.OnClickListener() {
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
                        atten.this,
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
                                edit2.setText(dayOfMonth2 + "/" + (monthOfYear2) + "/" + year);

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
        String date_n = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());


        if(dbHandler2.getSumValuecount8()>0) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Calendar currentCal = Calendar.getInstance();

            currentCal.add(Calendar.DATE, 1);

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = null;
            try {
                date = format.parse(dbHandler2.getSumValuecount2());
                System.out.println(date);
                String toDate = dateFormat.format(currentCal.getTime());
                edit2.setText(toDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }



        }else{
            edit2.setText(date_n);
        }

        chk1 = (CheckBox) findViewById(R.id.checkBox);
        chk2 = (CheckBox) findViewById(R.id.checkBox2);
        btn = (Button) findViewById(R.id.button3);

        chk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setEnabled(true);
                chk1.setChecked(true);
                chk2.setChecked(false);
            }
        });
        chk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setEnabled(true);
                chk1.setChecked(false);
                chk2.setChecked(true);
            }
        });
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    double work;
    public void sav(View view) throws ParseException {
        a = edit2.getText().toString();
        d = edit3.getText().toString();
        if(chk1.isChecked()){
            work=0.5;
        }else if(chk2.isChecked()){
            work=1;
        }


        b=String.valueOf(work*dbHandler.getSumValuecount3());
        if(dbHandler2.getSumValuecount7(a)>0){
        Toast.makeText(atten.this, "তারিখ ইতিমধ্যে আছে", Toast.LENGTH_SHORT).show();
        }else{
            buildRecyclerView(a,b,String.valueOf(work),d);
        }

        startActivity(new Intent(atten.this, view.class));

    }
    private void buildRecyclerView(String a, String b, String c,String d) throws ParseException {
        dbHandler2.addNewCourse(a,b,c,d);

    }
    public void next(View view) throws ParseException {
        startActivity(new Intent(atten.this, view.class));
    }
    public static final String PREFS_NAME = "MyPrefsFile";
    public void reset(View view) throws ParseException {
        dbHandler.reset_table();
        dbHandler2.reset_table();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("firstRun", true); // It is no longer the first run
        editor.commit();
    }
}
