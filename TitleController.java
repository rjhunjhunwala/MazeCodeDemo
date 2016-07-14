package islandsofviolence;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
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
public class TitleController implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
 if(!TitleFrame.playing.get()){
     switch(e.getKeyChar()){
         case 'r':
         case 'R':
             TitleFrame.TitlePanel.onHelpScreen=false;
             break;
         case 'l':
         case 'L':
             TitleFrame.TitlePanel.onHelpScreen=true;
             break;           
         case 'e':
         case 'E':
      if(!TitleFrame.TitlePanel.onHelpScreen)
             TitleFrame.playing.set(true);

     break;
     }
 }
    }
    
}
