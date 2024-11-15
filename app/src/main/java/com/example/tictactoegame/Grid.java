package com.example.tictactoegame;

//I learned the Singleton design pattern in Java Part 2 and I wanted to practice it here :)
public class Grid {

    //instance stored as a private static variable
    private static Grid INSTANCE;

    //variable that will hold the grid that needs to be returned
    private String[][] grid;


    //constructor needs to be private to prevent instantiation
    private Grid()
    {
        //set grid equal the size of grid we desire
        grid = new String[3][3];
    }


    //method for the singleton pattern
    public static Grid getINSTANCE()
    {
        if (INSTANCE == null) {
            //allow the instance TO EQUAL THE CLASS WHICH WILL GET THE CONSTRUCTOR
            INSTANCE = new Grid();
        }
        else
        {
            System.out.println("An Instance of the class Grid could not be created.");
        }

        return INSTANCE;
    }


   //getter
    public String[][] getGrid()
    {
        return grid;
    }


    //grid setter
   public void setGrid(String[][] grid)
   {
       this.grid = grid;
   }

}