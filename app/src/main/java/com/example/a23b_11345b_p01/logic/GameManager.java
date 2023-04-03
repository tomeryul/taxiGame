package com.example.a23b_11345b_p01.logic;


import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;


public class GameManager {

    private final int life;
    private int wrong;
    private boolean isEnded;


    public GameManager(int life) {
        this.life = life;
        this.wrong = 0;
        this.isEnded = false;
    }

    public boolean getIsEnded() {
        return isEnded;
    }
    public int getWrong() {
        return wrong;
    }

    public int getLife() {return life;}

    public void checkCollision(ShapeableImageView[][] main_img_traffic_lights, ShapeableImageView[]
            main_img_car, Vibrator v, Context context) {
        if (!isEnded) {
            int changed = 0;
            if(main_img_traffic_lights[5][0].getVisibility() == View.VISIBLE &&
                    main_img_car[0].getVisibility() == View.VISIBLE){
                Toast.makeText(context, "Collision occurred!!", Toast.LENGTH_LONG).show();
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                changed = 1;
            } else if (main_img_traffic_lights[5][1].getVisibility() == View.VISIBLE &&
                    main_img_car[1].getVisibility() == View.VISIBLE) {
                Toast.makeText(context, "Collision occurred!!", Toast.LENGTH_LONG).show();
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                changed = 1;
            } else if (main_img_traffic_lights[5][2].getVisibility() == View.VISIBLE &&
                    main_img_car[2].getVisibility() == View.VISIBLE) {
                Toast.makeText(context, "Collision occurred!!", Toast.LENGTH_LONG).show();
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                changed = 1;
            }
            if(changed==1){
                wrong += 1;
                if (wrong == 3) {
                    isEnded = true;
                }
            }
//            if ((main_img_traffic_lights[5][0].getVisibility() == View.VISIBLE &&
//                    main_img_car[0].getVisibility() == View.VISIBLE) ||
//                    (main_img_traffic_lights[5][1].getVisibility() == View.VISIBLE &&
//                            main_img_car[1].getVisibility() == View.VISIBLE) ||
//                    (main_img_traffic_lights[5][2].getVisibility() == View.VISIBLE &&
//                            main_img_car[2].getVisibility() == View.VISIBLE)) {
//                Toast.makeText(context, "Collision occurred!!", Toast.LENGTH_LONG).show();
//                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
//                wrong += 1;
//                if (wrong == 3) {
//                    isEnded = true;
//                }
//            }
        }
    }


}