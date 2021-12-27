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

    private TextView tvPoints;
    private TextView tvRound;
    private TextView tvCountdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_main);

        tvPoints = findViewById(R.id.textViewPoints);
        tvRound = findViewById(R.id.textViewRounds);
        tvCountdown = findViewById(R.id.textViewCountdown);

        showStartFragment();
    }

    private void newGame(){
        points=0;
        round =1;
        initRound();
    }

    private void initRound() {
        countdown = 10;
        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        container.removeAllViews();
        WimmelView wv = new WimmelView(this);
        container.addView(wv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        wv.setImageCount(8*(10+round));
        update();
    }



    private void update(){
        tvPoints.setText(Integer.toString(points));
        tvRound.setText(Integer.toString(round));
        tvCountdown.setText(Integer.toString(countdown));
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
            startGame();
        }
        else if(v.getId() == R.id.tvPlayAgain){
            showStartFragment();
         }
    }

    private void startGame() {

        newGame();
    }
}