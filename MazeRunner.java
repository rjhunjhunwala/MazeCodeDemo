/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package islandsofviolence;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JOptionPane;

/**
 *
 * @author Rohans
 */
public final class MazeRunner {

    /**
     * TODO FEATURES 
     * ALLOWING PICK UP OF MAP FRAGMENT FROM Fallen enemy
     * Secret map rooms containing a map of the whole maze
     * storing the sin and cos calls of an angle so they are only used once
     */
    public static final int wall = 0;
    public static final int space = 1;
    public static final int player = 2;
    public static final int enemy = 3;
    public static final int mapPieces = 4;
    public static final int supplies = 5;
public static final int exit=6;
    public static AtomicBoolean playerIsAlive=new AtomicBoolean(true);
    public static AtomicBoolean won=new AtomicBoolean(false);
    public static boolean procedurallyGenerateMap = true;
    static int[][] map;
    public static Player p;
    static boolean[][] visited;

    /**
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public static void playOneGame() throws IOException, InterruptedException {
       Greaver.killAllGreavers();
        playerIsAlive=new AtomicBoolean(true);
        won=new AtomicBoolean(false);
        GamePanel.minimapBlockSize=3;
        p=new Player();
        map=MazeHandler.make2dMaze();
         visited = new boolean[map.length][map[0].length];
        Game game = new Game();
        Thread t = new Thread(game);
        t.start();
        Greaver.floodFillMap();
        GameFrame g = new GameFrame();
        while(playerIsAlive.get()) {
            g.repaint();
        }
        if(won.get()){
          popUp("You win!");
        }
        else
        {
            popUp("You lose");
        }
    g.setVisible(false);
    TitleFrame.playing.set(false);
    }
public static void popUp(String prompt){
    JOptionPane.showMessageDialog(null, prompt);
}
}

