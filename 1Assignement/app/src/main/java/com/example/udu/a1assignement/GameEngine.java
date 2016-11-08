package com.example.udu.a1assignement;

import java.util.ArrayList;

/**
 * Created by UDU on 06.11.2016.
 */

//Here I reference ARRAYS from 0

public final class GameEngine {

    private int[][] board;
    private int columns ;
    private int rows;
    private int count=-1;
    private int[] addPos;
    private ArrayList<Integer> al_addPos;
    private int errorNum = 99999;
    public int[] gameEnd;
    public ArrayList<Integer> win;

    public boolean finished=false;


//    public static GameEngine getInstance(int rows, int columns) {
//        if (instance == null) {
//            instance = new GameEngine(rows, columns);
//        }
//        instance.flushBoard();
//        return instance;
//    }

    public GameEngine(int rows, int columns){
        board = new int[rows][columns];
        //board = {}
        this.columns = columns;
        this.rows = rows;
        addPos = new int[rows*columns];
        al_addPos = new ArrayList<Integer>();
        win = new ArrayList<Integer>();
    };

    public void restart(){
        count = -1;
        board = new int[rows][columns];
        addPos = new int[rows*columns];
        al_addPos.clear();
        win.clear();
        finished =false;
    }




    public int unDo(){
        int temp = al_addPos.get(count);
        board[getRow(temp)][getColumn(temp)] =0;
        al_addPos.remove(count);
        count--;
        return temp;
    }

    public int addToColumn(int position){
        int column = getColumn(position);
        for(int i = 0; i<rows; i++){
            if(board[i][column] == 0){
                this.count++;
                System.out.println(count);
                board[i][column]=getTurn();
                addPos[count] = getPosition(i,column);
                al_addPos.add(getPosition(i,column));
                checkWin(position);
                return getPosition(i,column);
            }
        }
        return errorNum;
    }


    public void setBoard(int[][] b, int count){
        board = b; this.count = count;
    }


    private void checkWin(int position) {


        ArrayList<Integer> inRow = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();

        int row = getRow(getLastPos());
        int column = getColumn(position);
        int turn = getTurn();
        int cnt = 0;
        //Horizontal check

        if(count > rows*columns-2){
            finished = true;
        }

        printBoard();
        System.out.println("Horizontal Check");
        for (int i = 0; i < columns; i++) {
            if (board[row][i] == turn) {
                inRow.add(getPosition(row, i));
            } else {
                array.add((ArrayList<Integer>) inRow.clone());
                inRow.clear();
            }
        }

        array.add((ArrayList<Integer>) inRow.clone());
        inRow.clear();

        System.out.println("Vertical check");


        //Vertical check
        for (int i = 0; i < rows; i++) {
            if (board[i][column] == turn) {
                inRow.add(getPosition(i,column));
            } else {
                array.add((ArrayList<Integer>) inRow.clone());
                inRow.clear();
            }
        }
        array.add((ArrayList<Integer>) inRow.clone());
        inRow.clear();


        //Diagonal check
        //board[row][column];

        //System.out.println("diagonal check");
        int cur_row = row;
        int cur_col = column;

        while (cur_col != 0 && cur_row != 0) {
            cur_col--;
            cur_row--;
        }
        //System.out.println(cur_col +" "+ cur_row);
        while (cur_col < columns && cur_row < rows){
            //System.out.println(cur_col +" "+ cur_row);
            if (board[cur_row][cur_col] == turn){
                inRow.add(getPosition(cur_row,cur_col));
            } else {
                array.add((ArrayList<Integer>) inRow.clone());
                inRow.clear();
            }
            cur_col++;
            cur_row++;
        }
        array.add((ArrayList<Integer>) inRow.clone());
        inRow.clear();


        //Diagonal check 2
        //board[row][column];

        //System.out.println("diagonal check");
        cur_row = row;
        cur_col = column;

        while (cur_col != 0 && cur_row != rows-1) {
            cur_col--;
            cur_row++;
        }
        //System.out.println(cur_col +" "+ cur_row);
        while (cur_col < columns && cur_row >= 0){
            //System.out.println(cur_col +" "+ cur_row);
            if (board[cur_row][cur_col] == turn){
                inRow.add(getPosition(cur_row,cur_col));

            } else {
                array.add((ArrayList<Integer>) inRow.clone());
                inRow.clear();
            }
            cur_col++;
            cur_row--;
        }

        array.add((ArrayList<Integer>) inRow.clone());
        inRow.clear();

        System.out.println("Win check");
        for(ArrayList<Integer> i : array ){
            System.out.println(i);
            if(i.size()>3){
                finished = true;
                for(Integer j : i){
                    win.add(j);
                }
            }
        }







    }



    public int getPosition(int row, int column){ return columns * row + column ;}
    public int getRow(int position){
        return position / columns;
    }
    public int getColumn(int position){
        return position % columns;
    }
    public int getCount(){return count;}
    public int getTurn(){return this.count%2+1;}
    public int getLastPos(){return addPos[count];}


    public void printBoard(){

        String s = "";
        s+="{ ";
        for(int i =0; i<rows; i++){
            s+="{ ";
            for(int j = 0; j<columns; j++){
                s+= board[i][j];
                if(getPosition(i,j)!=rows*columns-1){
                    s+=", ";
                }
            } s+="}";
            if(getPosition(i,6)!=rows*columns-1){
                s+=",";
            }
        }
        s+="}";
        System.out.println(s);

    }

}
