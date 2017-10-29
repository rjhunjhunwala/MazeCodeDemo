package islandsofviolence;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rohans
 */
public class Greaver {
	public static ArrayList<Greaver> greavers = new ArrayList(100);
	/**
	 * We are using an integer matrix to represent the distance each spot if
	 * from the player The players position is represented by 1 Non reachable
	 * positions or walls or represented by 0 The reset of the positions
	 * represent the distance needed to travel in the maze this is to be used
	 * for efficient navigation
	 */
	static int[][] navigationMap;

    public static void floodFillMap() {
        navigationMap = new int[MazeRunner.map.length][MazeRunner.map[0].length];
markOpen(MazeRunner.p.getXBlock(),MazeRunner.p.getYBlock(),1);
    }

    public static void markOpen(int x, int y, int num) {
        navigationMap[x][y] = num;
       

            if ((navigationMap[x + 1][y] == 0||num+1<navigationMap[x+1][y]) && MazeRunner.map[x + 1][y] == 1) {
                markOpen(x + 1, y, num+1);
            }
        
            if ((navigationMap[x - 1][y] == 0||num+1<navigationMap[x-1][y]) && MazeRunner.map[x - 1][y] == 1) {
                markOpen(x - 1, y, num+1);
            }
        
    
            if ((navigationMap[x][y + 1] == 0 ||num+1<navigationMap[x][y + 1]) && MazeRunner.map[x][y + 1] == 1) {
                markOpen(x, y + 1, num+1);
            }
        
    
            if ((navigationMap[x][y - 1] == 0||num+1<navigationMap[x][y - 1]) && MazeRunner.map[x][y - 1] == 1) {
                markOpen(x, y - 1, num+1);
            }
        
    }

    public static boolean isValid(int x, int y) {
     return x >= 0 && y >= 0 && y < navigationMap[0].length && x < navigationMap.length;
   // return true;
    }

    static void ageAllGreavers() {
        greavers.stream().filter((g) -> (g!=null)).forEach((g) -> {
            g.ageInHours++;
        });
    }

	public static void processGreavers() {
		floodFillMap();
		greavers.stream().filter((g) -> (g != null && g.alive.get())).forEach((Greaver greaver) -> {
			greaver.move();
			if (navigationMap[greaver.getXBlock()][greaver.getYBlock()] == 1) {
				System.out.println("The greaver ate you!");
				//todo allow the user to play again...
				//for now
				MazeRunner.playerIsAlive.set(false);
			}
			//kill it if its too old
			if (greaver.ageInHours > 4) {
				greaver.die();
			}
		});
	}

	public static void killAllGreavers() {
		greavers = new ArrayList(100);
	}
    AtomicBoolean alive = new AtomicBoolean(true);
    int ageInHours = 0;
    int x;
				int y;
				double angle = 0;
				
				public Greaver(int inX, int inY) {
        x = inX;
        y = inY;
        MazeRunner.map[getXBlock()][getYBlock()] = MazeRunner.enemy;
        greavers.add(this);
    }

    public void die() {
        this.alive.set(false);
        MazeRunner.map[x][y]=1;
//   greavers.remove(this);
    }

    public final int getXBlock() {
        return (int) x;

    }

    public final int getYBlock() {
        return (int) y;
    }

    public void move() {
        //System.err.println("FOO");
        int oldX = x;
        int oldY = y;
        int[] movementCoords = {oldX, oldY};
        int bestSquareValue = 99999;

        if (navigationMap[oldX + 1][oldY] != 0) {
            int potential = navigationMap[oldX + 1][oldY];
            if (potential < bestSquareValue) {
                bestSquareValue = potential;
                movementCoords[0] = oldX + 1;
                movementCoords[1] = oldY;
            }
        }

        if (navigationMap[oldX - 1][oldY] != 0) {
            int potential = navigationMap[oldX - 1][oldY];
            if (potential < bestSquareValue) {
                bestSquareValue = potential;
                movementCoords[0] = oldX - 1;
                movementCoords[1] = oldY;
            }
        }

        if (navigationMap[oldX][oldY + 1] != 0) {
            int potential = navigationMap[oldX][oldY + 1];
            if (potential < bestSquareValue) {
                bestSquareValue = potential;
                movementCoords[0] = oldX;
                movementCoords[1] = oldY + 1;
            }
        }

        if (navigationMap[oldX][oldY - 1] != 0) {
            int potential = navigationMap[oldX][oldY - 1];
            if (potential < bestSquareValue) {
                movementCoords[0] = oldX;
                movementCoords[1] = oldY - 1;
            }
        }

        if (alive.get()) {
            //System.out.println("FOO");
            MazeRunner.map[oldX][oldY] = 1;
            MazeRunner.map[movementCoords[0]][movementCoords[1]] = 3;
            x = movementCoords[0];
            y = movementCoords[1];
        }
    }
}
