package com.example.fitnessfreak;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class information extends AppCompatActivity {
    KeyValueDB db;
    private EditText mUseridEditText, age, weight;
    private EditText mEmailEditText;
    private RadioGroup goal, gender;
    private RadioButton male, female, fatloss, muscle;
    private String existingKey = "";
    private Button save, cancel;
    private boolean exist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        age=findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        male = findViewById(R.id.male);

        female = findViewById(R.id.female);
        fatloss = findViewById(R.id.fatloss);
        muscle = findViewById(R.id.muscle);
        goal=findViewById(R.id.goal);
        gender=findViewById(R.id.gender);

        save = findViewById(R.id.save_button);
        cancel = findViewById(R.id.cancel_button);



        save.setOnClickListener(v -> {
            //Collect info..
            String user_age = age.getText().toString().trim();
            String user_weight = weight.getText().toString().trim();

            int selectedId1 = gender.getCheckedRadioButtonId();
            RadioButton radioButton1 = (RadioButton) findViewById(selectedId1);
            String radio1 = radioButton1.getText().toString();


            int selectedId2 = goal.getCheckedRadioButtonId();
            RadioButton radioButton2 = (RadioButton) findViewById(selectedId2);
            String radio2 = radioButton2.getText().toString();


            if (user_age.isEmpty() || user_weight.isEmpty() || radio1.isEmpty() || radio2.isEmpty()) {
                // give notice of error..
                Toast.makeText(information.this, "FilL all the forms", Toast.LENGTH_SHORT).show();
            }
            else{
                String Value = user_age + "--" + user_weight + "--" + radio1 + "--" + radio2 ;
                if(existingKey.length()>0){
                    KeyValueDB db = new KeyValueDB(information.this);
                    db.updateValueByKey(existingKey,Value);
                    db.close();
                    finish();
                }
                else{
                    //procced for saving into database..
                    String Key =  user_age + System.currentTimeMillis();
                    KeyValueDB db = new KeyValueDB(information.this);
                    db.insertKeyValue(Key,Value);
                    db.close();

                    Intent show = new Intent(this,planandinfo.class);
                    startActivity(show);


                    finish();
                }
            }

        });

        cancel.setOnClickListener(v -> startActivity(new Intent(this, planandinfo.class)));

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

                age.setText(value[0]);
                weight.setText(value[1]);

            if(value[3].contains("Muscle Build")) muscle.setChecked(true);
            else if(value[3].contains("Fatloss")) fatloss.setChecked(true);
            if(value[2].contains("Male")) male.setChecked(true);
            else if(value[2].contains("Female")) female.setChecked(true);



        }
        db.close();

    }

    }


