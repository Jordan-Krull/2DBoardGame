public class player extends board  {
    public static int currentPlayer(){              //Returns currPlayer

        return currPlayer;
    }
    public static void changePlayer(){              //Changes currPlayer to opp
        if (currPlayer == 1) {
            currPlayer =2;
        }
        else{
        currPlayer = 1;
        }
    }
    public static int findPlayerPos(int player){    //Finds the current players pos in the board
        for(int i = 0; i < 63;){
            if (player == 1) {
                return player1Pos;
            }
            if(player == 2){
                return player2Pos;
            }
            while(player != gameBoard[i]){
                i++;
            }
        }
        return player;
    }
    public static boolean checkWin(){
        if(player1Points >= 3 || player2Points >= 3){
            if(currPlayer == 1){
                System.out.println("Player 1 Wins");
                return true;
            }
            if(currPlayer == 2){
                System.out.println("Player 2 Wins");
                return true;
            }
        }
        return false;
    }

}
