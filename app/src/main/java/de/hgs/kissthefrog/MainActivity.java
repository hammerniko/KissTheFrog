package de.hgs.kissthefrog;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int points;
    private int round;
    private int countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_main);
        showStartFragment();
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

    private void showStartFragment(){
        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        container.removeAllViews();
        container.addView(getLayoutInflater().inflate(R.layout.fragment_start,null));
        container.findViewById(R.id.tvStart).setOnClickListener(this);

    }

    private void showGameOverFragment(){
        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        container.addView(getLayoutInflater().inflate(R.layout.fragment_gameover,null));
        container.findViewById(R.id.tvPlayAgain).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvStart){
            //startGame();

            showGameOverFragment();
            Toast.makeText(this,"Start",Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.tvPlayAgain){
            showStartFragment();
            //Toast.makeText(this,"Play Again",Toast.LENGTH_SHORT).show();
        }
    }

    private void startGame() {
        newGame();
    }
}