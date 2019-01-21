package com.bola.bolarebotando;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {



    SensorManager sensorMgr;
    Sensor sensor;

    // Control de la velocitat
    float velocitatX = 30.0f;
    float velocitatY = 30.0f;
    float iniciX, iniciY;

    // Mides per fer càlculs
    int statusBar, width, height;

    ArrayList<Bola> bolas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.RelativeLayout);
        bolas = new ArrayList();

        // Obtenim les dimensions de la pantalla
        DisplayMetrics display = this.getBaseContext().getResources().getDisplayMetrics();
        width = display.widthPixels;
        height = display.heightPixels;

        for (int x = 0; x < 4;x++){
            Random random = new Random();
            ImageView img = new ImageView(getApplicationContext());
            img.setImageDrawable(getDrawable(R.drawable.pokeball));
            int posX;
            int posY;
            if(x%2==0){
                posY = random.nextInt((height/2) - 500 ) + 500;
                posX = random.nextInt((width/2) - 500 ) + 500;
                velocitatX= random.nextInt(50-20)+0;
                velocitatY= random.nextInt(50-20)+0;
            }else{
                posY = random.nextInt((height/2) - 500 ) + 100;
                posX = random.nextInt((width/2) - 500 ) + 100;
                velocitatX= random.nextInt(50-20)+0;
                velocitatY= random.nextInt(50-20)+0;
            }


            //Log.v("pos", Integer.toString(n));

            velocitatX = random.nextInt(80 - 65)+65;
            Log.v("pos", (velocitatX+" "+velocitatY));

            img.setX(posX);
            img.setY(posY);

            rl.addView(img);

            bolas.add(new Bola(posX, posY, velocitatX, velocitatY, img, height, width));
        }


        // La bola
        // img = (ImageView) findViewById(R.id.imageView);



        MyTimerTask myTask = new MyTimerTask();
        Timer myTimer = new Timer();

        myTimer.schedule(myTask, 0, 100);

        // Mida de l'statusBar per calcular l'alçada de l'aplicació
        statusBar = getResources().getDimensionPixelSize(getResources().getIdentifier("status_bar_height", "dimen", "android"));

    }

    class MyTimerTask extends TimerTask {
        public void run() {

            for (Bola bola:bolas) {
                bola.move();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
