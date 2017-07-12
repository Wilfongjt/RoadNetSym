/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeflyz.gamefixedtimestep.roads;

import com.codeflyz.roadnet.Segment;
import java.awt.Color;
import java.awt.Graphics;
import static javax.swing.UIManager.get;

/**
 *
 * @author jameswilfong
 */
public class JSegment extends Segment implements Drawable {

    private Color color = Color.LIGHT_GRAY;

    public JSegment(int idx, double x, double y, double x2, double y2) {
        super(idx, x, y, x2, y2);
        setColour(Color.LIGHT_GRAY);
    }

    public void update() {
        
    }

    public void draw(Graphics g) {
        //System.out.println("draw segment");
        //getMBR().draw(g);  // draw segment mbr
        //g.setColor(color);
        //if (getIdx() == 0) {
        //  g.setColor(Color.BLACK);
        //}
        if (this.isOccupied()) {
            // draw turd
            /*g.setColor(Color.YELLOW);
            g.fillOval((int) get(0).x, (int) get(0).y, 5, 5);
             */
            //g.setColor(Color.BLACK);
            //g.drawString("(" + this.getSpeed().toString().substring(0, 4) + ")", (int) get(0).x, (int) get(0).y);

            g.setColor(Color.RED);
            //System.out.println("Occupied segment " + this.occupied);

        } else {

            g.setColor(getColor());

        }

        g.drawLine((int) get(0).x, (int) get(0).y, (int) get(1).x, (int) get(1).y);
        //Coordinate c = this.getCentroid();
        //g.drawString(""+this.getIdx(), (int)c.x, (int)c.y);

    }

    /*public Color getColour() {
        return this.color;
    }*/

    public JSegment setColour(Color color) {
        setColor( color );
        return this;
    }
    public void setColor(Color color){
       this.color=color;
    
    }
}
