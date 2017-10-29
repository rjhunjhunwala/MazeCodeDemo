package islandsofviolence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rohans
 */
public class Player {
    double x=MazeHandler.size/2;
    double y=MazeHandler.size/2;
    double angle=0;
    public int getXBlock(){
        return (int) x;
    
    }
public int getYBlock(){
    return (int) y;
}
}
