import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;
public class GUI extends JFrame {
    private static int BOARD_SIZE = 10;     //Max 26   //Preferred Setting 10
    private final int CELL_SIZE = 30;
    private int player1X = 0;               //Player 1's POS
    private int player1Y = 0;
    private int player2X = BOARD_SIZE - 1;  //Player 2's POS
    private int player2Y = BOARD_SIZE - 1;
    private int randomY;                    //Point Tile X and Y
    private int randomX;
    private int player1Points = 0;          //Point Totals
    private int player2Points = 0;
    private int randomPowerX;               //Power Tile X and Y
    private int randomPowerY;
    private int player1Dur;                 //How many more tiles can pick up before double points runs out
    private int player2Dur;
    private static int pointGoal;           //Win Total
    Random rand = new Random();
    boolean active = true;
    public GUI() {                          //Gameboard Constructor
        randomX = rand.nextInt(1,BOARD_SIZE);
        randomY = rand.nextInt(1,BOARD_SIZE);
        randomPowerX = rand.nextInt(1,BOARD_SIZE);
        randomPowerY = rand.nextInt(1,BOARD_SIZE);
        setTitle("Game Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320,340);
        setResizable(true);
        setLocationRelativeTo(null);

        JPanel boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
                drawPlayers(g);
                pointPanel(g);
                createPowers(g);
            }
        };
        boardPanel.setPreferredSize(new Dimension(BOARD_SIZE * CELL_SIZE, BOARD_SIZE * CELL_SIZE));
        boardPanel.setBackground(Color.WHITE);
        boardPanel.setFocusable(true);
        boardPanel.requestFocusInWindow();
        boardPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                    movePlayers(e,active);
                    boardPanel.repaint();
                }
        });

        add(boardPanel);
    }
    
    private void makeWinScreen(){           //Disposes of current screen and makes the winScreen
    JLabel label;
    JButton yesButton;
    JButton noButton;
        active = false;
        setTitle("Play Again?");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 340);

        label = new JLabel("Do you want to play again?");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        yesButton = new JButton("Yes");
        noButton = new JButton("No");

        
        yesButton.addActionListener(new ActionListener() {  //Yes Button
            public void actionPerformed(ActionEvent e) {
                GUI board = new GUI();
                board.setVisible(true);
                System.out.println("Yes button clicked");
                active = true;
                dispose(); 
            }
        });

        noButton.addActionListener(new ActionListener() {   //No Button
            public void actionPerformed(ActionEvent e) {

                System.out.println("No button clicked");
                dispose();
            }
        });
        JPanel panel = new JPanel(); //Win Screen Panel
        panel.setLayout(new GridLayout(3, 1));
        panel.add(label);
        panel.add(yesButton);
        panel.add(noButton);

        add(panel);
        setVisible(true);
    }

    private void drawBoard(Graphics g) {    //Draws the tiles on the board
        g.setColor(Color.BLACK);
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                g.drawRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
        if(randomPowerX == player1X && randomPowerY == player1Y){     //Checks if Player 1 is on a power up
            player1Dur = player1Dur + 2;
            randomPowerX = rand.nextInt(0,BOARD_SIZE);
            randomPowerY = rand.nextInt(0,BOARD_SIZE);
            pointPanel(g);
        }  
            
            
        if(randomPowerX == player2X && randomPowerY == player2Y){    //Checks if Player 2 is on a power up
            player2Dur = player2Dur + 2;
            randomPowerX = rand.nextInt(0,BOARD_SIZE);
            randomPowerY = rand.nextInt(0,BOARD_SIZE);
            pointPanel(g);
            
        }
        if(randomX == player1X && randomY == player1Y){             //Checks if Player 1 is in a point square
            if(player1Dur > 0 ){
                player1Points++;
                player1Dur--;
            }
            player1Points++;
            checkWin();
            System.out.println("Point Totals:");
            System.out.println("Player 1: " + player1Points);
            System.out.println("Player 2: " + player2Points);
            randomX = rand.nextInt(0,BOARD_SIZE);
            randomY = rand.nextInt(0,BOARD_SIZE);
            createPowers(g);
        }
        if (randomX == player2X && randomY == player2Y) {           //Checks if Player 2 is in a point square
            if(player2Dur > 0){
                player2Points++;
                player2Dur--;
            }
            player2Points++;
            checkWin();
            System.out.println("Point Totals:");
            System.out.println("Player 1: " + player1Points);
            System.out.println("Player 2: " + player2Points);
            randomX = rand.nextInt(0,BOARD_SIZE);
            randomY = rand.nextInt(0,BOARD_SIZE);
            createPowers(g);
        }
    }

    private void drawPlayers(Graphics g) {  //Draws the players: Player 1 RED||Player 2 Blue
        g.setColor(Color.RED);
        g.fillRect(player1X * CELL_SIZE, player1Y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        g.setColor(Color.BLUE);
        g.fillRect(player2X * CELL_SIZE, player2Y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    private void movePlayers(KeyEvent e,boolean active) {  //Moves player based on Arrows or WASD
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (player1Y > 0 && active == true) player1Y--;
                break;
            case KeyEvent.VK_DOWN:
                if (player1Y < BOARD_SIZE - 1 && active == true) player1Y++;
                break;
            case KeyEvent.VK_LEFT:
                if (player1X > 0 && active == true) player1X--;
                break;
            case KeyEvent.VK_RIGHT:
                if (player1X < BOARD_SIZE - 1 && active == true) player1X++;
                break;
            case KeyEvent.VK_W:
                if (player2Y > 0 && active == true) player2Y--;
                break;
            case KeyEvent.VK_S:
                if (player2Y < BOARD_SIZE - 1 && active == true) player2Y++;
                break;
            case KeyEvent.VK_A:
                if (player2X > 0 && active == true) player2X--;
                break;
            case KeyEvent.VK_D:
                if (player2X < BOARD_SIZE - 1 && active == true) player2X++;
                break;
        }
    }

    private void pointPanel(Graphics g){    //Draws a Yellow point square on the board
        g.setColor(Color.yellow);
        g.fillRect(randomX*CELL_SIZE, randomY*CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    private void createPowers(Graphics g){  //Creates a green power up square on the board
        g.setColor(Color.green);
        g.fillRect(randomPowerX*CELL_SIZE, randomPowerY*CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }
    

    private void checkWin(){                //Checks for win based on pointGoal is equal to either players pointTotal
        if (player1Points >= pointGoal || player2Points >= pointGoal) {
            makeWinScreen();

        }
    }

    public static void main(String[] args) {                //Main Method
        SwingUtilities.invokeLater(() -> {
            Scanner user = new Scanner(System.in);          //Scanner to get pointGoal and BOARD_SIZE
            System.out.println("What is the point goal?");
            pointGoal = user.nextInt();
            System.out.println("How big of a gameboard?");
                BOARD_SIZE = user.nextInt();
                GUI board = new GUI();
                //Makes the Game Play
                board.setVisible(true);
        });
    }
}
