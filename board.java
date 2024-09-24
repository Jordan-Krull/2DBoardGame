
import java.util.Random;
public class board{
    //Varaiables
    static int[] gameBoard = new int[64];       // 8X8 Gameboard
    public static int currPlayer = 1;
    public static int player1Pos = 0;           //Starting Player 1
    public static int player2Pos = 63;          //Starting Player 2
    public static int player1Points = 0;        //Starting Player 1 Points
    public static int player2Points = 0;        //Starting Player 2 Points
    static Random rand = new Random();          //Generates the rand
    static int points[] = {rand.nextInt(2,63),rand.nextInt(2,63),rand.nextInt(2,63),rand.nextInt(2,63),rand.nextInt(2,63),rand.nextInt(2,63)};
    //Functions
    public static void resetBoard(){            //Sets the board 0's

        for(int i = 0; i < 64; i++){
            gameBoard[i] = 0;
        }
        for(int i =0; i < 5; i++){
            gameBoard[points[i]] = 3;
        }
        gameBoard[player1Pos]= 1;
        gameBoard[player2Pos] = 2;
        player1Points = 0;
        player2Points = 0;
    }
    public static void findPoints(){            //Finds the Points on the board
        for(int i = 0; i < 64; i++){
            if (gameBoard[i] == 3) {
                
            }
        }
    }
    public static void showBoard(){             //Shows Board by setting 0's in gameboard to X's and 1's to 1 and 2's to 2 and 3's to O
        for(int i = 0; i < 64; i++){
            if(i % 8 == 0){
                System.out.println();
            }
            if (gameBoard[i] == 0) {        //Spots
                System.out.print(" X ");
            }
            if (gameBoard[i] == 1) {        //Player 1
                System.out.print(" 1 ");
            }
            if (gameBoard[i] == 2) {        //Player 2
                System.out.print(" 2 ");
            }
            if(gameBoard[i] == 3){          //Points
                System.out.print(" O ");
            }
        }
        
    }
    public static void updateBoard(){           //Updates board to move the player Pos
        gameBoard[player1Pos] = 1;              
        gameBoard[player2Pos] = 2;
        
    }
}