package com.turningpoint.attendance;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class view extends AppCompatActivity {
    private DBHandler2 dbHandler;
    private ArrayList<CourseModel3> courseModelArrayList3;
    public static CourseAdapter4 adapter;
    static RecyclerView spinnerMobile;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view);
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        spinnerMobile = findViewById(R.id.spinner1);
        dbHandler = new DBHandler2(view.this);
        TextView title = (TextView) findViewById(R.id.textView7);
        TextView title2 = (TextView) findViewById(R.id.textView6);
        TextView title3 = (TextView) findViewById(R.id.textView11);
        TextView title4 = (TextView) findViewById(R.id.textView14);
        title.setText(String.valueOf(dbHandler.getSumValuecount4()));
        title2.setText(String.valueOf(dbHandler.getSumValuecount3()+" টাকা"));
        title3.setText(String.valueOf(dbHandler.getSumValuecount5()));
        title4.setText(String.valueOf(dbHandler.getSumValuecount3()-dbHandler.getSumValuecount6()+" টাকা"));

        buildRecyclerView2();
    }
    private void buildRecyclerView2() {

        courseModelArrayList3 = new ArrayList<>();

        dbHandler = new DBHandler2(view.this);


        // getting our course array
        // list from db handler class.
        courseModelArrayList3 = dbHandler.readCourses();

        // on below line passing our array list to our adapter class.
        adapter = new CourseAdapter4(courseModelArrayList3, view.this);
        spinnerMobile = findViewById(R.id.spinner1);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.this, RecyclerView.VERTICAL, false);
        spinnerMobile.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        spinnerMobile.setAdapter(adapter);

    }

}
