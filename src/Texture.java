package islandsofviolence;


import islandsofviolence.GamePanel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rohans
 */
public class Texture {
    public static final int size=256;//GamePanel.screenheight;
    Color[][][] texture=new Color[3*size+1][size][];
    public Texture(BufferedImage b){
        //size  zero texture
        texture[0]=new Color[1][1];
        texture[0][0][0]=GamePanel.SKY_BLUE;
        //end unused size zer texture
        texture[size]=new Color[size][size];
      for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                texture[size][i][j]=new Color(b.getRGB(i, j),true);
            }
        }
        //System.out.println(Arrays.deepToString(texture[size]));
        //System.out.println("Initial image loaded");
        for(int height=1;height<size;height++){
            double scale=size/(height/1.0);
            for(int col=0;col<size;col++){
                texture[height][col]=new Color[height];
                for(int y=0;y<texture[height][col].length;y++){
                   texture[height][col][y]=texture[size][col][(int) (y*scale)];
                }
              //  System.out.println("One line Loaded");
            }
            //System.out.println("One stage loaded");
        }
    }
}
