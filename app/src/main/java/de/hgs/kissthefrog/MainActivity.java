package de.hgs.kissthefrog;

import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            countdown();
        }
    };




    private static final int FROG_ID = 212121;
    private ImageView frog;
    private int points;
    private int round;
    private int countdown;

    private TextView tvPoints;
    private TextView tvRound;
    private TextView tvCountdown;
    private Random rnd = new Random();
    private Handler handler = new Handler();

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


        frog = new ImageView(this);
        frog.setId(FROG_ID);
        frog.setImageResource(R.mipmap.frog);
        frog.setScaleType(ImageView.ScaleType.CENTER);
        float scale = getResources().getDisplayMetrics().density;
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(Math.round(64*scale), Math.round(61*scale));
        lp.gravity = Gravity.TOP+Gravity.LEFT;
        lp.leftMargin = rnd.nextInt(container.getWidth()-64);
        lp.topMargin =rnd.nextInt(container.getHeight()-61);
        frog.setOnClickListener(this);
        container.addView(frog,lp);

        update();
        handler.postDelayed(runnable, 1000-round*50);
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
        else if (v.getId()==FROG_ID){
            handler.removeCallbacks(runnable);
            Toast.makeText(this, R.string.kissed, Toast.LENGTH_SHORT).show();
            points+=countdown*1000;
            round++;
            initRound();
        }
    }

    private void startGame() {

        newGame();
    }


    private void countdown(){
        countdown--;
        update();
        if (countdown<=0){
            frog.setOnClickListener(null);
            showGameOverFragment();
        }
        else{
            handler.postDelayed(runnable, 1000-round*50);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}