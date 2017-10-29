package islandsofviolence;


import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rohans
 */
public class Game implements Runnable {
  static int time=7;
  static boolean isAM=true;
static int mobSpawningRate=3;
public Game(){
    time=7;
    isAM=true;
    mobSpawningRate=3;
}
  @Override
    public void run(){
        int counter=0;
        for(;MazeRunner.playerIsAlive.get();){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
counter++;
Greaver.processGreavers();
if((counter%=20)==0){
    time++;    
    if(time==12){
            isAM=!isAM;
        }
        if(time ==13){
            time =1;
        }
        if(time==8&&!isAM){
            closeGates();
        }
        if(time==6&&isAM){
            openGates();
        }

        spawnMobs();
        Greaver.ageAllGreavers();
        }

        }
        
    }

    private void closeGates() {
       System.out.println("The gates are closing");
       mobSpawningRate=25;
    }

    private void openGates() { 
    System.out.println("The gates are open");
    mobSpawningRate = 3;
    }

    private void spawnMobs() {
      
      for(int i=0;i<mobSpawningRate;i++){
          int x=(int) (Math.random()*MazeRunner.map.length);
          int y= (int) (Math.random() *MazeRunner.map[0].length);
      if(MazeRunner.map[x][y]==1){
          if(Greaver.navigationMap[x][y]>6){
              if((x<MazeHandler.size/2-10||x>MazeHandler.size/2+10)||(y<MazeHandler.size/2-10||y>MazeHandler.size/2+10)){
                  new Greaver(x,y);
              }
          }
      }
      }
    }
    }
