package islandsofviolence;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rohans
 */
public class TitleFrame extends JFrame {
       public static AtomicBoolean playing=new AtomicBoolean(false);
    public static class TitlePanel extends JPanel{
          public static boolean onHelpScreen=false;
       public static BufferedImage title;
        static{
              try {
                  title=ImageIO.read(new File("TitleScreen.gif"));
             // System.out.println(title);
              }
              catch (IOException ex) {
                  Logger.getLogger(TitleFrame.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
        public static BufferedImage help;
        static{
              try {
                  help=ImageIO.read(new File("howToPlay.gif"));
             // System.out.println(title);
              }
              catch (IOException ex) {
                  Logger.getLogger(TitleFrame.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
        @Override
          public Dimension getPreferredSize() {
            return new Dimension(768,768);
        }
          @Override
 public void paintComponent(Graphics g){
     //super.paintComponent(g);
  g.drawImage(onHelpScreen?help:title, 0,0,this);
 }
    }
   public TitleFrame(){
               super("Maze Runner");

     TitlePanel   p = new TitlePanel();

        this.add(p);

        this.pack();

 

        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.addKeyListener(new TitleController());
    this.setLocationRelativeTo(null);
 this.repaint();
   }
   public static TitleFrame t;
 public static void main(String[] args) throws Exception{
     Thread.sleep(2000);

     t=new TitleFrame();
while(true){
     while(!playing.get()){
    t.repaint();
}
     MazeRunner.playOneGame();
while(playing.get()){
    Thread.sleep(10);
}
 t.setVisible(true);
 }
 }
    
}
