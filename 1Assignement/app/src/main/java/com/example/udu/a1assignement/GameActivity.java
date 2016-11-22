package com.example.udu.a1assignement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity implements View.OnClickListener  {



    TextView txt_Turn;
    Button btn_Test;
    Button btn_Undo;
    Button btn_Restart;
    private int rows = 6 ;
    private int columns =  7;
    private GameEngine gE;
    private int buttons[] = {R.drawable.red_t, R.drawable.green_t};
    private String[] s_Turns = {"Red", "Green"};
    private int win_buttons[] = { R.drawable.red_wint,R.drawable.green_wint};
    private int temp_buttons[] ={ R.drawable.empty_t,R.drawable.green_t, R.drawable.red_t};

    GridView gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);// Mis see teeb
        Intent intent = this.getIntent();
        setContentView(R.layout.activity_game);
        txt_Turn = (TextView) findViewById(R.id.txt_Turn);
        btn_Test = (Button)findViewById(R.id.btn_Test);
        btn_Undo = (Button) findViewById(R.id.btn_Undo);
        btn_Restart = (Button) findViewById(R.id.btn_Restart);
        // Register the Login button to click listener
        // Whenever the button is clicked, onClick is called
        btn_Test.setOnClickListener(this);
        btn_Test.setVisibility(View.GONE);
        btn_Undo.setOnClickListener(this);
        btn_Restart.setOnClickListener(this);


        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setNumColumns(7);
        gridview.setAdapter(new ImageAdapter(this, rows*columns));
        txt_Turn.setText(s_Turns[0]);



        // Create GameEngine
        gE = new GameEngine(rows,columns);
        gridview.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // System.out.println(gridview.getAdapter().);

                if(!gE.finished){

                    int addPosition = gE.addToColumn(position);
                    txt_Turn.setText(s_Turns[(gE.getCount()+1)%2]);
                    if(addPosition<rows*columns){
                        ImageView chosen =  (ImageView) parent.getChildAt(addPosition);
                        chosen.setImageResource(buttons[getTurn()]);
                        if(gE.finished){
                            if(!gE.win.isEmpty())
                                for(int i : gE.win){
                                chosen = (ImageView) parent.getChildAt(i);
                                chosen.setImageResource(win_buttons[getTurn()]);
                                Toast.makeText(GameActivity.this, "Game Over  "+ s_Turns[getTurn()] + " Won",
                                        Toast.LENGTH_SHORT).show();
                                btn_Undo.setEnabled(false);
                            }
                            else{
                                Toast.makeText(GameActivity.this, "Game Over Nobody Won - You both suck ",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }

                    }
                   // Toast.makeText(GameActivity.this, "" + position +" "+ gE.getRow(position) +" "+gE.getColumn(position) +" "+ gE.getRow(gE.getLastPos()) +" "+ gE.getColumn(gE.getLastPos()), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    @Override
    public void onClick(View v) {

        if(v.getId()== R.id.btn_Test){
            placeButtons();
        }
        else if(v.getId()== R.id.btn_Undo){
            btn_Undo.setEnabled(true);
            unDo();
        }
        else if(v.getId()== R.id.btn_Restart){
            txt_Turn.setText(s_Turns[0]);
            btn_Undo.setEnabled(true);
            gE.restart();
            for(int i = 0; i<rows*columns; i++){
                ImageView l = (ImageView) gridview.getChildAt(i);
                l.setImageResource(R.drawable.empty_t);
            }

        }


    }

    public void unDo(){
        if(gE.getCount()>-1){
            int pos = gE.unDo();
            ImageView v = (ImageView) gridview.getChildAt(pos);
            v.setImageResource(R.drawable.empty_t);

        }

    }


    public void placeButtons(){
        //int[][]b=  { { 0, 0, 0, 1, 1, 2, 1, },{ 0, 0, 0, 0, 2, 2, 2, },{ 0, 0, 0, 0, 1, 1, 1, },{ 0, 0, 0, 0, 0, 2, 1, },{ 0, 0, 0, 0, 0, 0, 2, },{ 0, 0, 0, 0, 0, 0, 1}};
        //int [][]b = { { 1, 2, 1, 1, 1, 0, 0, },{ 0, 1, 2, 2, 2, 0, 0, },{ 0, 0, 0, 2, 1, 0, 0, },{ 0, 0, 0, 0, 0, 0, 0, },{ 0, 0, 0, 0, 0, 0, 0, },{ 0, 0, 0, 0, 0, 0, 0}};
        int[][]b= { { 2, 1, 2, 1, 2, 1, 1, },{ 1, 1, 1, 2, 1, 1, 1, },{ 2, 1, 2, 1, 1, 1, 1, },{ 1, 1, 1, 1, 1, 1, 1 },{ 1, 1,1, 1, 1, 1, 1 },{ 1, 1, 1, 1, 1, 1, 0}};
        gE.setBoard(b,0);
        for(int i = 0; i<rows;i++){
            for(int j = 0; j<columns; j++){
                //System.out.print(gE.getPosition(i,j));
                ImageView temp = (ImageView) gridview.getChildAt(gE.getPosition(i,j));
                temp.setImageResource(temp_buttons[b[i][j]]);
                //temp.setImageResource(R.drawable.red_wint);

            }
            System.out.println("");
        }

    }

    private int getTurn(){
        return gE.getCount()%2;
    }





}
