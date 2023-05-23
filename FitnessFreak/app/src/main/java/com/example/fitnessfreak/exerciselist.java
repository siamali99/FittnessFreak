package com.example.fitnessfreak;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class exerciselist extends AppCompatActivity {

    private LinearLayout ex1,ex2,ex3,ex4,ex5,ex6,ex7,ex8,ex9,ex10,ex11,ex12,ex13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exerciselist);
        ex1=findViewById(R.id.ex1);
        ex2=findViewById(R.id.ex2);
        ex3=findViewById(R.id.ex3);
        ex4=findViewById(R.id.ex4);
        ex5=findViewById(R.id.ex5);
        ex6=findViewById(R.id.ex6);
        ex7=findViewById(R.id.ex7);
        ex8=findViewById(R.id.ex8);
        ex9=findViewById(R.id.ex9);
        ex10=findViewById(R.id.ex10);
        ex11=findViewById(R.id.ex11);
        ex12=findViewById(R.id.ex12);
        ex13=findViewById(R.id.ex13);




    }

    protected void onStart() {
        super.onStart();

        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_pairs");
        if (rows.getCount() == 0) {
            return;
        }
        while (rows.moveToNext()) {
            String key = rows.getString(0);
            String eventData = rows.getString(1);
            String value[] = eventData.split("--");
            if(value[3].contains("Muscle Build")){
                ex1.setVisibility(View.GONE);
                ex5.setVisibility(View.GONE);
                ex4.setVisibility(View.GONE);
                ex11.setVisibility(View.VISIBLE);
                ex12.setVisibility(View.VISIBLE);
                ex13.setVisibility(View.VISIBLE);

            }
            else if(value[3].contains("Fatloss")){
                ex1.setVisibility(View.VISIBLE);
                ex5.setVisibility(View.VISIBLE);
                ex4.setVisibility(View.VISIBLE);
                ex11.setVisibility(View.GONE);
                ex12.setVisibility(View.GONE);
                ex13.setVisibility(View.GONE);

            }





        }
        db.close();

    }
}

