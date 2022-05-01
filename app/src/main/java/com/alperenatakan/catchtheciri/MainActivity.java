package com.alperenatakan.catchtheciri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    TextView timeText;
    int score;
    ImageView ImageView0;ImageView ImageView1;ImageView ImageView2;ImageView ImageView3;ImageView ImageView4;ImageView ImageView5;ImageView ImageView6;ImageView ImageView7;ImageView ImageView8;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText=findViewById(R.id.timeText);
        scoreText=findViewById(R.id.scoreText);
        score=0;
        ImageView0=findViewById(R.id.imageView);
        ImageView1=findViewById(R.id.imageView1);
        ImageView2=findViewById(R.id.imageView2);
        ImageView3=findViewById(R.id.imageView3);
        ImageView4=findViewById(R.id.imageView4);
        ImageView5=findViewById(R.id.imageView5);
        ImageView6=findViewById(R.id.imageView6);
        ImageView7=findViewById(R.id.imageView7);
        ImageView8=findViewById(R.id.imageView8);

        imageArray=new ImageView[]{ImageView0,ImageView1,ImageView2,ImageView3,ImageView4,ImageView5,ImageView6,ImageView7,ImageView8};


        hideImages();

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
                timeText.setText("Time : "+l/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Time off!");
                handler.removeCallbacks(runnable);
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart the game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //restart
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Game Over!",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();


            }
        }.start();

    }

    public  void increaseScore(View view){
        score++;
        scoreText.setText("Score: "+score);

    }
    public void hideImages(){
        handler =new Handler();
        runnable =new Runnable() {
            @Override
            public void run() {
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,550);
            }
        };
        handler.post(runnable);


    }

}