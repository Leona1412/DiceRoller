package com.example.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewDice;
    private Random random = new Random();
    private int lastDir;
    private boolean spinning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewDice = findViewById(R.id.image_view_dice);

        imageViewDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //define what we want to do if we click on image

                rollDice();
            }
        });
    }

    private void rollDice() {
        int randint = random.nextInt(6) + 1;
        spinDice(imageViewDice);
        switch (randint) {
            case 1:
                imageViewDice.setImageResource(R.drawable.dice1);
                //image is in drawable folder
                break;
            case 2:
                imageViewDice.setImageResource(R.drawable.dice2);
                break;
            case 3:
                imageViewDice.setImageResource(R.drawable.dice3);
                break;
            case 4:
                imageViewDice.setImageResource(R.drawable.dice4);
                break;
            case 5:
                imageViewDice.setImageResource(R.drawable.dice5);
                break;
            case 6:
                imageViewDice.setImageResource(R.drawable.dice6);
                break;
        }
    }

    public void spinDice(View view) {
        //view is our image
        //generate direction to spin
        if (!spinning) { //if spinning is false
            int newDirection = random.nextInt(1800);
            //get middle of out imageView
            float pivotX = imageViewDice.getWidth() / 2; //middle width
            float pivotY = imageViewDice.getHeight() / 2;
            Animation rotate = new RotateAnimation(lastDir, newDirection, pivotX, pivotY);
            rotate.setDuration(500);
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            lastDir = newDirection; //dice sometimes spins clockwise, sometimes anticlockwise
            imageViewDice.startAnimation(rotate);
        }
    }
}