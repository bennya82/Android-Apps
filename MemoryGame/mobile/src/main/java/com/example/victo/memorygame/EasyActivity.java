package com.example.victo.memorygame;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.CountDownTimer;
import android.os.Handler;


public class EasyActivity extends AppCompatActivity {

    final String CANDY  = "candy";
    final String COFFEE = "coffee";

    String username;
    Boolean coffee_pressed=false;
    Boolean candy_pressed=false;
    int number_of_matches = 0;
    final Handler handler_e = new Handler();

    TextView username_display;
    TextView timer_display;
    TextView won_textView;
    ImageButton button1_coffee;
    ImageButton button2_candy;
    ImageButton button3_coffee;
    ImageButton button4_candy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        //hiding winning message
        won_textView = (TextView)findViewById(R.id.won_e_textView);
        won_textView.setVisibility(View.INVISIBLE);

        //getting content from bundle
        Bundle extras_e = getIntent().getExtras();
        username = extras_e.getString("EXTRA_USERNAME");

        //initializing components
        timer_display = (TextView)findViewById(R.id.timer_textView);
        username_display = (TextView)findViewById(R.id.username_e_textview);
        button1_coffee = (ImageButton)findViewById(R.id.e_button1);
        button2_candy = (ImageButton)findViewById(R.id.e_button2);
        button3_coffee = (ImageButton)findViewById(R.id.e_button3);
        button4_candy = (ImageButton)findViewById(R.id.e_button4);
        username_display.setText(username);

        //announcing number of cards
        Toast.makeText(getApplicationContext(), "Easy level: 4 cards in 30 seconds", Toast.LENGTH_LONG).show();

        //countdown timer
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer_display.setText("" + millisUntilFinished / 1000);
            }
            public void onFinish() {
                timer_display.setText("Done");
                Toast.makeText(getApplicationContext(), "You did not make it in time", Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        }.start();


        //buttons clicked
        button1_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { buttonClickHandler(button1_coffee,COFFEE); }
        });
        button2_candy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { buttonClickHandler(button2_candy,CANDY); }
        });
        button3_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { buttonClickHandler(button3_coffee,COFFEE); }
        });
        button4_candy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { buttonClickHandler(button4_candy,CANDY); }
        });
    }

    public void buttonClickHandler(ImageButton clicked_button, String value){
        final ImageButton item = clicked_button;//needs to be declared like this because 'item' used in delay
        item.setEnabled(false);
        if(value.equals(COFFEE)){
            item.setImageResource(R.drawable.coffee_icon);
            if(coffee_pressed){
                //Toast.makeText(getApplicationContext(), "Same images were clicked!", Toast.LENGTH_LONG).show();
                coffee_pressed=false;
                checkGameEnd();
            }else if(!candy_pressed){
                coffee_pressed = true;
            }else{
                coffee_pressed=false;
                candy_pressed=false;
                //add delay here
                handler_e.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do this after 1000ms=1sec
                        item.setImageResource(R.drawable.q_mark_blue);
                        item.setEnabled(true);
                        button2_candy.setImageResource(R.drawable.q_mark_blue);
                        button2_candy.setEnabled(true);
                        button4_candy.setImageResource(R.drawable.q_mark_blue);
                        button4_candy.setEnabled(true);
                    }
                }, 1000);


            }
        }
        else{ //if(value.equals(CANDY))
            item.setImageResource(R.drawable.candy_icon);
            if(candy_pressed){
                //Toast.makeText(getApplicationContext(), "Same images were clicked!", Toast.LENGTH_LONG).show();
                candy_pressed=false;
                checkGameEnd();
            }else if(!coffee_pressed){
                candy_pressed = true;
            }else{
                coffee_pressed=false;
                candy_pressed=false;
                //add delay here
                handler_e.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do this after 1000ms=1sec
                        item.setImageResource(R.drawable.q_mark_blue);
                        item.setEnabled(true);
                        button1_coffee.setImageResource(R.drawable.q_mark_blue);
                        button1_coffee.setEnabled(true);
                        button3_coffee.setImageResource(R.drawable.q_mark_blue);
                        button3_coffee.setEnabled(true);
                    }
                }, 1000);


            }
        }
    }

    public void checkGameEnd(){
        number_of_matches++;
        if(number_of_matches==2){
            won_textView.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "YOU WON THE GAME!", Toast.LENGTH_LONG).show();
            onBackPressed();
            //finish();
        }

    }
}
