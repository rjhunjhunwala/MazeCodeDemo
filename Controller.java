/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package islandsofviolence;

import static islandsofviolence.Controller.firing;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rohans
 */
public class Controller implements KeyListener {
public static AtomicBoolean firing=new AtomicBoolean(false);
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Speed in blocks per event
     */
public final double SPEED=.2;
    @Override
    public void keyPressed(KeyEvent e) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    double c=-1;
				double d=-1;
				double s=SPEED;	
				switch(e.getKeyChar()){
					case 'W':
						s*=2;
					case 'w':
         c=MazeRunner.p.x+Math.cos(MazeRunner.p.angle)*s;
        d=MazeRunner.p.y-Math.sin(MazeRunner.p.angle)*s;
        collisionCheck(c,d);
								break;
					case 'A':
					case 'a':
            MazeRunner.p.angle+=Math.PI/36;
            break;
					case 'S':
						s*=2;
					case 's':
          c=MazeRunner.p.x-Math.cos(MazeRunner.p.angle)*s;
        d=MazeRunner.p.y+Math.sin(MazeRunner.p.angle)*s;
								collisionCheckBackward(c,d);
            break;
					case 'D':   
					case 'd':
          MazeRunner.p.angle-=Math.PI/36;
            break;
        case 'q':
        case 'Q':
            GamePanel.minimapBlockSize--;
            break;
        case 'e':
        case 'E':
            GamePanel.minimapBlockSize++;
            break;
								case 'Z':
									s*=2;
								case 'z':
          c=MazeRunner.p.x-Math.cos(MazeRunner.p.angle-Math.PI/2)*s;
        d=MazeRunner.p.y+Math.sin(MazeRunner.p.angle-Math.PI/2)*s;
        if(MazeRunner.map[(int) c][(int) MazeRunner.p.y]==1&&MazeRunner.map[(int) (c-Math.cos(MazeRunner.p.angle-Math.PI/2)*.35)][(int) MazeRunner.p.y]==1){
            MazeRunner.p.x=c;
        }   
        if(MazeRunner.map[(int) MazeRunner.p.x][(int) d]==1&&MazeRunner.map[(int) MazeRunner.p.x][(int) (d+Math.sin(MazeRunner.p.angle-Math.PI/2)*.35)]==1){
            MazeRunner.p.y=d;
        }
    break;
								case 'C':
									s*=2;
        case 'c':
          c=MazeRunner.p.x+Math.cos(MazeRunner.p.angle-Math.PI/2)*s;
        d=MazeRunner.p.y-Math.sin(MazeRunner.p.angle-Math.PI/2)*s;
      if(MazeRunner.map[(int) c][(int) MazeRunner.p.y]==1&&MazeRunner.map[(int) (c+Math.cos(MazeRunner.p.angle-Math.PI/2)*.35)][(int) MazeRunner.p.y]==1){
            MazeRunner.p.x=c;
        }   
        if(MazeRunner.map[(int) MazeRunner.p.x][(int) d]==1&&MazeRunner.map[(int) MazeRunner.p.x][(int) (d-Math.sin(MazeRunner.p.angle-Math.PI/2)*.35)]==1){
            MazeRunner.p.y=d;
        }
								break;
            case 'f':
            case 'F':
    firing.set(true);
new Thread(new Lagger()).start();
                double bulletX=MazeRunner.p.x;
    double bulletY=MazeRunner.p.y;
    for(double rayDist=0;rayDist<15;rayDist+=GamePanel.getReasonableStep(bulletX,bulletY,MazeRunner.p.angle)){
                     bulletX = (MazeRunner.p.x + Math.cos(MazeRunner.p.angle) * rayDist);
                bulletY = (MazeRunner.p.y - Math.sin(MazeRunner.p.angle) * rayDist);
    for(Greaver g:Greaver.greavers){
        if(g!=null){
    if((int) bulletX==g.x&&(int) bulletY==g.y){
        g.die();
    }      
        }
    }
				
    }
				}
				
            
    collideAndVisitSquares();
    
				}
    @Override
    public void keyReleased(KeyEvent e) {
   //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void collideAndVisitSquares() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    for(int i=(int) (MazeRunner.p.x-1);i<=(MazeRunner.p.x+1);i++){
       for(int j=(int) (MazeRunner.p.y-1);j<=(MazeRunner.p.y+1);j++){
        MazeRunner.visited[i][j]=true;
    }     
    }
    if(MazeRunner.p.getXBlock()==MazeHandler.size-2&&MazeRunner.p.getYBlock()==MazeHandler.size-2){
        MazeRunner.won.set(true);
        MazeRunner.playerIsAlive.set(false);
    }
    }
public static void collisionCheck(double c, double d){
	        if(MazeRunner.map[(int) c][(int) MazeRunner.p.y]==1&&MazeRunner.map[(int) (c+Math.cos(MazeRunner.p.angle)*.35)][(int) MazeRunner.p.y]==1){
            MazeRunner.p.x=c;
        }   
        if(MazeRunner.map[(int) MazeRunner.p.x][(int) d]==1&&MazeRunner.map[(int) (MazeRunner.p.x)][(int) (d-Math.sin(MazeRunner.p.angle)*.35)]==1){
            MazeRunner.p.y=d;
        }
}
public static void collisionCheckBackward(double c, double d){
	        if(MazeRunner.map[(int) c][(int) MazeRunner.p.y]==1&&MazeRunner.map[(int) (c-Math.cos(MazeRunner.p.angle)*.35)][(int) MazeRunner.p.y]==1){
            MazeRunner.p.x=c;
        }   
        if(MazeRunner.map[(int) MazeRunner.p.x][(int) d]==1&&MazeRunner.map[(int) (MazeRunner.p.x)][(int) (d+Math.sin(MazeRunner.p.angle)*.35)]==1){
            MazeRunner.p.y=d;
        }
}
public static class Lagger implements Runnable{

	@Override
	public void run(){

				try {
						Thread.sleep(100);
					} catch (InterruptedException ex) {
						Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
					}
firing.set(false);			

	
}
    
}
}