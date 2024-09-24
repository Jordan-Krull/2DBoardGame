import java.util.Scanner;
public class actions extends player {
    public static void collectPoint(){               //Checks the board if the currPlayer is on a O spot and adds points
        for(int i = 0; i < points.length; i++){
            if (findPlayerPos(currPlayer) == points[i]) {   //See's if the current player is on a point
                int[] temp = {-1,-1,-1,-1,-1,-1};
                for (int j = 0; j < points.length; j++) {
                    if (points[j] != points[i]) {
                        temp[j] = points[j];
                    }
                    points = temp;                          //Reassigns Array
                }
                System.out.println("Point Collected for player "+currPlayer);
                if(currPlayer == 1){
                    player1Points++;
                }
                else{
                    player2Points++;
                }
            }
        }
    }
    public static void promptMove(){                 //Activates the Scanner input for current players move
        Scanner movementScanner = new Scanner(System.in);
        System.out.println("Player"+currPlayer+"'s Move");
        String act = movementScanner.nextLine();
        move(act);
    }
    public static void move(String select){          //Moves the player
        if(select.equals("d")){             //Moves Right
            if(findPlayerPos(currPlayer) == 7 || findPlayerPos(currPlayer) == 15 || findPlayerPos(currPlayer) == 23 || findPlayerPos(currPlayer) == 31 || findPlayerPos(currPlayer) == 39 || findPlayerPos(currPlayer) == 47 || findPlayerPos(currPlayer) == 55 || findPlayerPos(currPlayer) == 63){                     //7,15,23,31,39,47,55,63
                System.out.println("Please Select a Valid Move");
                promptMove();
            }
            else{
                if(currPlayer == 1){
                    gameBoard[player1Pos] = 0;
                    player1Pos = player1Pos + 1;
                    collectPoint();
                }
                else{
                    gameBoard[player2Pos] = 0;
                    player2Pos = player2Pos + 1;
                    collectPoint();
                }
            }
        }
        if (select.equals("s")) {           //Moves Down
            if(findPlayerPos(currPlayer) > 55){
                System.out.println("Please Select a Valid Move");
                promptMove();
            }
            else{
                if(currPlayer == 1){
                    gameBoard[player1Pos] = 0;
                    player1Pos = player1Pos + 8;
                    collectPoint();
                }
                else{
                    gameBoard[player2Pos] = 0;
                    player2Pos = player2Pos + 8;
                    collectPoint();
                }
            }
        }
        if(select.equals("a")){             //Moves Left 
            if (findPlayerPos(currPlayer) == 0 || findPlayerPos(currPlayer) % 8 == 0) {
                System.out.println("Please Select a Valid Move");
                promptMove();
                }
            else{
                if(currPlayer == 1){
                    gameBoard[player1Pos] = 0;
                    player1Pos = player1Pos - 1;
                    collectPoint();
                }
                else{
                    gameBoard[player2Pos] = 0;
                    player2Pos = player2Pos -1;
                    collectPoint();
                }
            }
        }
        if (select.equals("w")) {           //Moves Up
            if (findPlayerPos(currPlayer) - 8 < 0 ) {
                System.out.println("Please Select a Valid Move");
                promptMove();
            }
            else{
                if (currPlayer == 1) {
                    gameBoard[player1Pos] = 0;
                    player1Pos = player1Pos - 8;
                    collectPoint();
                }
                else{
                    gameBoard[player2Pos] = 0;
                    player2Pos = player2Pos - 8;
                    collectPoint();
                }

            }
        }
        updateBoard();
        changePlayer();
        System.out.println("Player 1 Points: " + player1Points);
        System.out.println("Player 2 Points: " +player2Points);
        showBoard();
        promptMove();
        /*if(checkWin() == true){
            System.out.println("Player "+ currPlayer+ " Wins");
        }
        else{
            promptMove();
        }
        */
    }
}
