package de.hgs.kissthefrog;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    private int points;
    private int round;
    private int countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);


        setContentView(R.layout.activity_main);
    }

    private void newGame(){
        points=0;
        round =1;
        initRound();
    }

    private void initRound() {
        countdown = 10;
        update();
    }

    private void fillTextView(int id, String text){
        TextView tv = (TextView) findViewById(id);
        tv.setText(text);

    }

    private void update(){
        fillTextView(R.id.textViewPoints,Integer.toString(points));
        fillTextView(R.id.textViewRounds,Integer.toString(round));
        fillTextView(R.id.textViewCountdown,Integer.toString(countdown*1000));
    }
}