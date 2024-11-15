package com.example.tictactoegame;
import java.util.Objects;
import java.util.Random;

//NOTE TO TONY: I attempted the computer player and that logic. However, I kept running into problems with the computer's turn, but I think I was close.
//In order to hand in a working game, I switched it to a second human player. I kept my computer player code in this class for you to see my thinking process.

public class TicTacToeGame {
        /*create my constant variables.
       Static = belong to the class itself, not to an instance.
       Final = variable values cannot be changed once set.
       Use datatype char because we are only using one character.*/
        private static final String PLAYER_X = "X";
        private static final String PLAYER_O = "O";

        //COMPUTER PLAYER LOGIC.
        //private static final String COMPUTER_PLAYER  = "O";
        //private Random random =  new Random();
        //variables to hold the computer's coordinate values
        //private int computer_row;
        //private int computer_col;

        private static final String EMPTY = " ";

        //keep track of who is the current player
        private static String currentPlayer;

        //HERE IS MY PRACTICE WITH THE SINGLETON
        //I did try to make this Singleton give us a Button array, but it would not work for some reason. Stuck with a String array to
        //provide some framework for the game logic without interacting with the buttons themselves.
        //grab the instance that only exists
        Grid gridInstance = Grid.getINSTANCE();
        String[][] grid = gridInstance.getGrid();

        //constructor, just set the current player_X.
        public TicTacToeGame()
        {
            //set my current player as Player X right away. They will always begin the game.
            currentPlayer = PLAYER_X;
        }

        //METHOD TO GRAB THE VARIABLE CURRENT PLAYER FOR TOAST MESSAGES.
        public String getCurrentPlayer()
        {
            return currentPlayer;
        }

        //switch players when a person's turn is made.
        public void switchPlayer() {
            if (currentPlayer.equals(PLAYER_X)) {
                currentPlayer = PLAYER_O;
                //currentPlayer = COMPUTER_PLAYER;

            } else {
                currentPlayer = PLAYER_X;
            }
        }

        //COMPUTER PLAYER LOGIC
        //these parametes would be supplied by the random generator
//        public void playComputerTurn()
//        {
//            int computerRow = random.nextInt(3);
//            int computerCol = random.nextInt(3);
//
//            //I also need to check if any of the places are filled or otherwise I need to check if they're EMPTY FIRST!!
//            if(!grid[computerRow][computerCol].equals(EMPTY))
//            {
//                //PLACE THE COMPUTER'S CHAR IN THAT LOCATION.
//                grid[computerRow][computerCol] = COMPUTER_PLAYER;
//                switchPlayer();
//
//            }
//            //if that location IS TAKEN, we need to allow the computer to keep looking for another place.
//            else
//            {
//                playComputerTurn();;
//            }
//        }

        //I need a row and a column index to play the current player's turn.
        public void playTurn(int row, int col) {
            //I have the row and the col, need to ensure that the button is empty first before I can place the character of the current player.
            if (grid[row][col].equals(EMPTY))
            {
                //now put the char of the current player into that grid location
                grid[row][col] = currentPlayer;
                //YOU NEED THAT PLAYER SWITCH NOW
                switchPlayer();
            }
            else
            {
                System.out.println("This grid location is already taken! Please try again.");
            }
        }

        //need to return a boolean to see if someone has won yet or not.
        public boolean checkWin() {

            //check for column win combinations
            if (!Objects.equals(grid[0][0], EMPTY) && grid[0][0].equals(grid[1][0]) && grid[0][0].equals(grid[2][0]))
            {
                //need to return our winner who will be whatever marking is in that grid location
                return true;
            }
            else if (!Objects.equals(grid[0][1], EMPTY) && grid[0][1].equals(grid[1][1]) && grid[0][1].equals( grid[2][1]))
            {
                return true;
            }
            else if (!Objects.equals(grid[0][2], EMPTY) && grid[0][2].equals(grid[1][2]) && grid[0][2].equals(grid[2][2]))
            {
                return true;
            }

            //check the row win combinations
            if(!grid[0][0].equals(EMPTY) && grid[0][0].equals(grid[0][1]) && grid[0][0].equals(grid[0][2]))
            {
                return true;
            }
            else if (!Objects.equals(grid[1][0], EMPTY) && grid[1][0].equals(grid[1][1]) && grid[1][0].equals(grid[1][2]))
            {
                return true;
            }
            else if (!Objects.equals(grid[2][0], EMPTY) && grid[2][0].equals(grid[2][1]) && grid[2][0].equals(grid[2][2]))
            {
                return true;
            }

            //check the diagonal win combinations
            if(!grid[0][0].equals(EMPTY) && grid[0][0].equals(grid[1][1]) && grid[0][0].equals(grid[2][2]))
            {
                return true;
            }
            else if (!grid[0][2].equals(EMPTY) && grid[0][2].equals(grid[1][1]) && grid[0][2].equals(grid[2][0]))
            {
                return true;
            }

            //if there are no wins return false.
            return false;
        }

        //need to check for any possible draws. That is when the board is full.
        public boolean checkDraw()
        {
            //I originally had this as a nested for loop, but then realized I needed a foreach loop
            //since the app was always calling it a draw whenever I clicked on the button located in [0][0],
            //I opted for a foreach loop to solve that problem.
            for (String row[] : grid)
            {
                for (String col: row)
                {
                    if (col.equals(EMPTY))
                    {
                        //not a draw.
                        return false;
                    }
                }
            }
            return true;
        }

        //just clear the grid method.
        public void clearGrid() {
            //loop through each grid location
            for (int x = 0; x < grid.length; x++ ) {
                //nested for loop is for the columns.
                for (int y = 0; y < grid.length; y++)
                {
                    //make each grid index empty, use my variable.
                    grid[x][y] = EMPTY;
                }
            }
            //get the current player set up for the next game!
            currentPlayer = PLAYER_X;
        }

}