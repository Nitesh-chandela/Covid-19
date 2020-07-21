package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        splashscreens sp=new splashscreens();
        sp.start();

    }
    public class splashscreens extends Thread{

        public void run()
        {
            try{
                sleep(1500);
            }catch (InterruptedException e){
                e.getStackTrace();
            }
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            com.example.covid_19.splashscreen.this.finish();
        }

    }
}
