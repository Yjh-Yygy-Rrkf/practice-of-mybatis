package com.hfuu.homework;

import java.applet.Applet;
import java.awt.*;
import java.util.*;

public class AppletTest2 {

    @SuppressWarnings({ "deprecation", "serial" })
    public class pp extends Applet implements Runnable {
        int x = 0, y = 0, r = 100;
        int h, m, s;
        double rad = Math.PI / 180;

        public void init() {
            Calendar now = new GregorianCalendar();

            s = now.get(Calendar.SECOND) * 6;
            m = now.get(Calendar.MINUTE) * 6;
            h = now.get(Calendar.HOUR) * 30 + now.get(Calendar.MINUTE) / 12 * 6;
            Thread t = new Thread(this);
            t.start();
        }

        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.BLACK);
            // 画表
            g.drawOval(x, y, r * 2, r * 2);

            int x1 = (int) (90 * Math.sin(rad * s));
            int y1 = (int) (90 * Math.cos(rad * s));
            g.drawLine(r, r, r + x1, r - y1);

            x1 = (int) (80 * Math.sin(rad * m));
            y1 = (int) (80 * Math.cos(rad * m));
            g.drawLine(r, r, r + x1, r - y1);

            x1 = (int) (60 * Math.sin(rad * h));
            y1 = (int) (60 * Math.cos(rad * h));
            g.drawLine(r, r, r + x1, r - y1);

            int d = 30;
            for (int i = 1; i <= 12; i++) {
                x1 = (int) ((r - 10) * Math.sin(rad * d));
                y1 = (int) ((r - 10) * Math.cos(rad * d));
                g.drawString(i + "", r + x1, r - y1);
                d += 30;
            }

            d = 0;
            for (int i = 1; i <= 60; i++) {
                x1 = (int) ((r - 2) * Math.sin(rad * d));
                y1 = (int) ((r - 2) * Math.cos(rad * d));
                g.drawString(".", r + x1, r - y1);
                d += 6;
            }
            Calendar now1 = new GregorianCalendar();
            int a, b, c;
            a = now1.get(Calendar.HOUR_OF_DAY);
            b = now1.get(Calendar.MINUTE);
            c = now1.get(Calendar.SECOND);
            g.drawString(a + ":" + b + ":" + c, 0, 10);

        }
        // 实现Runnable
        public void run() {
            while (true) {
                try {
                    // 间隔一秒
                    Thread.sleep(1000);
                } catch (Exception ex) {
                }

                s += 6;
                if (s >= 360) {
                    s = 0;
                    m += 6;
                    if (m == 72 || m == 144 || m == 288) {
                        h += 6;
                    }
                    if (m >= 360) {
                        m = 0;
                        h += 6;
                    }
                    if (h >= 360) {
                        h = 0;

                    }
                }
                this.repaint();
            }
        }
    }
}
