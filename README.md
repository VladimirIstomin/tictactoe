# Tic-tac-toe
It's a simple tic-tac-toe game with the pseudo AI
## How to play?
To play the game you have to use CLI
- Select mode by entering special command. Some examples:
  - ```start user user``` is for 2 human game
  - ```start user easy``` is for 1 human (plays X's) and 1 easy AI (plays O's) game
  - ```start medium hard``` is for 2 AI game (medium AI plays X's, hard AI plays O's)
  - ```exit``` to quit the game

  You are free to combine ```user``` ```easy``` ```medium``` ```hard``` options, but there should be always only two of them
 - Make moves by entering the coordinates, for example, ```3 1``` 
  
    **Notes:** the first number stands for a row and the second number stands for a column of the board; numbers start from 1 
 ## Some features
- Easy AI makes random moves
- Medium AI only makes considered moves if it's 1 move to win or 1 move to loose, other moves are random
- Hard AI always maximizes the utility of the move as far as minimax algorithm is used
