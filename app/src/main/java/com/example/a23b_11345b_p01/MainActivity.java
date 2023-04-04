package com.example.a23b_11345b_p01;

import androidx.appcompat.app.AppCompatActivity;

//timer imports
import java.util.Timer;
import java.util.TimerTask;

//FAB imports
import com.example.a23b_11345b_p01.logic.GameManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import com.google.android.material.imageview.ShapeableImageView;
//timer imports

import java.util.Arrays;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private ShapeableImageView[] main_IMG_hearts;
    private ShapeableImageView[][] main_IMG_traffic_lights;
    private ShapeableImageView[] main_IMG_car;
    private FloatingActionButton time_FAB_left;
    private FloatingActionButton time_FAB_right;


    private int new_traffic_lights_timer = 0;
    public final int DELAY = 1000;
    long startTime = 0;
    private Timer timer;
    private GameManager gameManager;
    Random rand = new Random();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setFirstVisibility();
        startTime();
        initView();

        gameManager = new GameManager(main_IMG_hearts.length);

    }

    private void setFirstVisibility() {
        main_IMG_car[0].setVisibility(View.INVISIBLE);
        main_IMG_car[2].setVisibility(View.INVISIBLE);
        for (ShapeableImageView[] main_img_traffic_light : main_IMG_traffic_lights) {
            for (ShapeableImageView shapeableImageView : main_img_traffic_light) {
                shapeableImageView.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void initView() {
        time_FAB_left.setOnClickListener(v -> GoLeft());
        time_FAB_right.setOnClickListener(v -> GoRight());
    }

    private void GoRight() {
        if(main_IMG_car[1].getVisibility()==View.VISIBLE) {
            main_IMG_car[1].setVisibility(View.INVISIBLE);
            main_IMG_car[2].setVisibility(View.VISIBLE);
        }
        if(main_IMG_car[0].getVisibility()==View.VISIBLE) {
            main_IMG_car[0].setVisibility(View.INVISIBLE);
            main_IMG_car[1].setVisibility(View.VISIBLE);
        }
    }

    private void GoLeft() {
        if(main_IMG_car[1].getVisibility()==View.VISIBLE) {
            main_IMG_car[1].setVisibility(View.INVISIBLE);
            main_IMG_car[0].setVisibility(View.VISIBLE);
        }
        if(main_IMG_car[2].getVisibility()==View.VISIBLE) {
            main_IMG_car[2].setVisibility(View.INVISIBLE);
            main_IMG_car[1].setVisibility(View.VISIBLE);
        }

    }


    private void findViews() {
        main_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3)};

        main_IMG_traffic_lights = new ShapeableImageView[][]{{
                findViewById(R.id.traffic_light1),
                findViewById(R.id.traffic_light2),
                findViewById(R.id.traffic_light3)},{
                findViewById(R.id.traffic_light4),
                findViewById(R.id.traffic_light5),
                findViewById(R.id.traffic_light6)},{
                findViewById(R.id.traffic_light7),
                findViewById(R.id.traffic_light8),
                findViewById(R.id.traffic_light9)},{
                findViewById(R.id.traffic_light10),
                findViewById(R.id.traffic_light11),
                findViewById(R.id.traffic_light12)},{
                findViewById(R.id.traffic_light13),
                findViewById(R.id.traffic_light14),
                findViewById(R.id.traffic_light15)},{
                findViewById(R.id.traffic_light16),
                findViewById(R.id.traffic_light17),
                findViewById(R.id.traffic_light18)}};

        main_IMG_car = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_car1),
                findViewById(R.id.main_IMG_car2),
                findViewById(R.id.main_IMG_car3)};

        time_FAB_left = findViewById(R.id.time_FAB_left);
        time_FAB_right = findViewById(R.id.time_FAB_right);
    }

    private void updateTimeUI() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        for(int i = main_IMG_traffic_lights.length-1 ; i >= 0 ; i--){
            for(int j = main_IMG_traffic_lights[i].length-1 ; j >=0  ; j--){
                if(main_IMG_traffic_lights[i][j].getVisibility()==View.VISIBLE){
                    if(i < main_IMG_traffic_lights.length-1) {
                        main_IMG_traffic_lights[i][j].setVisibility(View.INVISIBLE);
                        main_IMG_traffic_lights[i + 1][j].setVisibility(View.VISIBLE);
                    }
                    else
                        main_IMG_traffic_lights[i][j].setVisibility(View.INVISIBLE);
                }
            }
        }
        if(new_traffic_lights_timer==1){
            makeNewLight();
        }
        new_traffic_lights_timer = (new_traffic_lights_timer + 1)%2;

        gameManager.checkCollision(main_IMG_traffic_lights,main_IMG_car,v,getApplicationContext());
        updateLife();
    }

    private void updateLife() {
        if(gameManager.getWrong() != 0) {
            if (!gameManager.getIsEnded())
                main_IMG_hearts[gameManager.getLife() - gameManager.getWrong()].setVisibility(View.INVISIBLE);
            else {
                // if the game is ended i set the Visibility of the hearts to VISIBLE
                // like the instruction for the project said
                for (ShapeableImageView main_img_heart : main_IMG_hearts)
                    main_img_heart.setVisibility(View.VISIBLE);
            }
        }
    }

    private void makeNewLight() {
        int where = rand.nextInt(3);
        main_IMG_traffic_lights[0][where].setVisibility(View.VISIBLE);
    }

    private void startTime() {
        if (timer == null) {
            startTime = System.currentTimeMillis();
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> updateTimeUI());
                }
            }, 0, DELAY);
        }
    }

}