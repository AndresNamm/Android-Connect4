package com.example.udu.a1assignement;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    //Define the class variable inside MainActivity Class
    Button btn_Game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //System.out.println("Vajutasid Nupule");

        btn_Game = (Button)findViewById(R.id.btn_Game);

        // Register the Login button to click listener
        // Whenever the button is clicked, onClick is called
        btn_Game.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v.getId()== R.id.btn_Game){
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("Alusta", true);
            startActivity(intent);
        }
    }
}
