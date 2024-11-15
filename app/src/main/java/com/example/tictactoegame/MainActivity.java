package com.example.tictactoegame;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;

//DONE
public class MainActivity extends AppCompatActivity {

    //my  game variable of type TicTacToeGame
    private TicTacToeGame game;

    //now here I can declare a 2D array of type Button to represent the grid
    private Button[][] mainGrid;

    //just setting a variable equal to the toast length
    int duration = Toast.LENGTH_SHORT;


    //this is where everything will start
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.tic_tac_toe);

        //set the game variable equal to a new instance of the class
        game = new TicTacToeGame();

        //make the grid of type Button and make it the correct size.
        mainGrid = new Button[3][3];

        //reset the game button
        Button newGame = findViewById(R.id.NewGame);
        //allow button to call the restGame method here within this mainactivity class.
        newGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

       //exit the game button
        Button clear = findViewById(R.id.ExitGame);
        //set the listener for this button to exit the app.
        clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // Link the buttons from the layout xml.
        mainGrid[0][0] = findViewById(R.id.one);
        mainGrid[0][1] = findViewById(R.id.two);
        mainGrid[0][2] = findViewById(R.id.three);
        mainGrid[1][0] = findViewById(R.id.four);
        mainGrid[1][1] = findViewById(R.id.five);
        mainGrid[1][2] = findViewById(R.id.six);
        mainGrid[2][0] = findViewById(R.id.seven);
        mainGrid[2][1] = findViewById(R.id.eight);
        mainGrid[2][2] = findViewById(R.id.nine);


        // Set click listeners for each button, making sure each button plays the game with the correct row and column coordinates.
        mainGrid[0][0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playGame(0,0);
                }
        });
        mainGrid[0][1].setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  playGame(0, 1);
              }
      });
        mainGrid[0][2].setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  playGame(0, 2);
              }
      });
        mainGrid[1][0].setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  playGame(1, 0);
              }

      });
        mainGrid[1][1].setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  playGame(1, 1);
              }

      });
        mainGrid[1][2].setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  playGame(1, 2);

              }
      });
        mainGrid[2][0].setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  playGame(2, 0);
              }
      });
        mainGrid[2][1].setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  playGame(2, 1);
              }
      });
        mainGrid[2][2].setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  playGame(2, 2);
              }
      });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //main method that calls the game class methods
    private void playGame(int row, int col) {

        // Make move and update button text
        //HERE YOU NEED TO GET THE TEXT FIRST
        if (mainGrid[row][col].getText().toString().equals(" ")) {

            //play this method to know which current player needs their string value to be displayed.
            game.playTurn(row, col);

            //YOU NEED TO BE ABLE TO SET THE VALUE TO THE ACTUAL BUTTON CONTROL.
            //NOW YOU NEED TO ACTUALLY SET THE TEXT BACK TO THE UI
            mainGrid[row][col].setText(game.getCurrentPlayer());

            // Check for winner AND determine if it is TRUE
            if (game.checkWin()) {
                //send a simple Toast message.
                Toast.makeText(this, "The winner is Player " + (game.getCurrentPlayer()) + " ! Game Over!", duration).show();
                //prevent anyone to play anymore once the game is over
                GameOver();
            }
            //if the entire board is actually full, then we have a draw.
            else if (game.checkDraw())
            {
                Toast.makeText(this, "It's a draw! Game Over!", duration).show();
                //again disable the buttons.
                GameOver();
            }
            //IF NONE OF THESE, IT WIL CONTINUE TO ALLOW THE NEXT PLAYER TO PLAY.
        }
    }

    //disable the buttons once the game is over, preventing any player from clicking further.
    private void GameOver()
    {
        for (int x = 0; x < mainGrid.length; x++ ) {
            //nested for loop is for the columns.
            for (int y = 0; y < mainGrid.length; y++)
            {
                //make each grid index empty, use my variable.
                mainGrid[x][y].setEnabled(false);

            }
        }
    }

    //reset the game by enabling the buttons again and clearing the grid with empty string values.
    //Have to create this method because the TicTacToeGame does not directly interact with datatype Button.
    private void resetGame()
    {
        //need to clear any of the 2D array grid locations so that there's nothing to give it.
       game.clearGrid();
        for (int x = 0; x < mainGrid.length; x++ ) {
            //nested for loop is for the columns.
            for (int y = 0; y < mainGrid.length; y++)
            {
                //make each grid index empty, use my variable.
                mainGrid[x][y].setEnabled(true);;
                mainGrid[x][y].setText(" ");
            }
        }
    }

}