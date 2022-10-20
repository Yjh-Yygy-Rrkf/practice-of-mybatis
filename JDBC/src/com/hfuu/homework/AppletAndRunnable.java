package com.hfuu.homework;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class AppletAndRunnable {

    @SuppressWarnings({ "serial", "deprecation" })
    public class AppletTest extends Applet {
        int index;
        Image[] imgs =new Image[6];
        public void init(){
            addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e) {
                    index=++index%6;
                    repaint();
                }
            });
            for (int i=0; i<6; i++)
                imgs[i]=getImage(getCodeBase(),"image/花"+(i+1)+".gif");
        }
        public void paint(Graphics g){
            if (imgs[index]!=null)
                g.drawImage(imgs[index],60,20,this);
        }
    }
}
