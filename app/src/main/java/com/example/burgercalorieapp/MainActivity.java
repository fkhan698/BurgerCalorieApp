package com.example.burgercalorieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Declare UI objects to be referenced
    private RadioGroup pattyRG;
    private CheckBox proscuittoCBX;
    private RadioGroup cheeseRG;
    private SeekBar sauceSBR;
    private TextView caloriesTV;

    //Variable for computing calories
    private Burger burger;
    private OnClickListener baconListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Objects and Variables
        burger = new Burger();
        initialize();

        // Register change listeners
        registerChangeListener();
    }
    private void initialize(){
        pattyRG = findViewById(R.id.radioGroup1);
        proscuittoCBX = findViewById(R.id.cb_proscuitto);
        cheeseRG = findViewById(R.id.RadioGroup2);
        sauceSBR = findViewById(R.id.seekBar);
        caloriesTV = findViewById(R.id.tv_sauce);
        displayCalories();
    }

    private void displayCalories() {
        String calorieText = "Calories: " + burger.getTotalCalories();
        caloriesTV.setText(calorieText);
    }


    private void registerChangeListener() {
        pattyRG.setOnCheckedChangeListener(foodListener);
        proscuittoCBX.setOnClickListener(baconListener);
        cheeseRG.setOnCheckedChangeListener(foodListener);
        sauceSBR.setOnSeekBarChangeListener(sauceListener);
    }

 private RadioGroup.OnCheckedChangeListener foodListener = new RadioGroup.OnCheckedChangeListener(){
        public void onCheckedChanged(RadioGroup rbGroup,int radioID){
            RadioButton rb = findViewById(radioId);
            String btn = rb.getText().toString();

            switch (btn) {
                case "Beef Patty": // BEEF PATTY
                    burger.setPattyCalories(Burger.BEEF);
                    break;
                case "Lamb Patty": // LAMB PATTY
                    burger.setPattyCalories(Burger.LAMB);
                    break;
                case "Ostrich Patty": // OSTRICH PATTY
                    burger.setPattyCalories(Burger.OSTRICH);
                    break;
                case "Asiago Cheese": // ASIAGO CHEESE
                    burger.setCheeseCalories(Burger.ASIAGO);
                    break;
                case "Creme Fraiche": // CREME FRAICHE
                    burger.setCheeseCalories(Burger.CREME_FRAICHE);
        }
        displayCalories();
    }

     private OnClickListener baconListener = new OnClickListener() {
         public void onClick(View v) {
             if (((CheckBox) v).isChecked())
                 burger.setProsciuttoCalories(Burger.PROSCIUTTO);
             else
                 burger.clearProsciuttoCalories();

             displayCalories();
         }
     };

     private OnSeekBarChangeListener sauceListener = new OnSeekBarChangeListener() {
         public void onProgressChanged(SeekBar seekBar, int progress,
                                       boolean fromUser) {
             burger.setSauceCalories(seekBar.getProgress());
             // sauceCal = seekBar.getProgress();
             displayCalories();
         }

         public void onStartTrackingTouch(SeekBar seekBar) {
         }

         public void onStopTrackingTouch(SeekBar seekBar) {
         }
     };

     private void displayCalories() {

         // CONSTRUCT AN OUTPUT STRING AND DISPLAY IN THE TEXTVIEW
         String calorieText = "Calories: " + burger.getTotalCalories();
         caloriesTV.setText(calorieText);
     }








     public boolean onCreateOptionsMenu(Menu menu) {
         // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.my, menu);
         return true;
     }


     public boolean onOptionsItemSelected(MenuItem item) {
         // Handle action bar item clicks here. The action bar will
         // automatically handle clicks on the Home/Up button, so long
         // as you specify a parent activity in AndroidManifest.xml.
         int id = item.getItemId();
         if (id == R.id.action_settings) {
             return true;
         }
         return super.onOptionsItemSelected(item);
     }

    };
};