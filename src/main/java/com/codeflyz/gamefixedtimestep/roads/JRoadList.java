/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeflyz.gamefixedtimestep.roads;

import com.codeflyz.roadnet.Coordinate;
import com.codeflyz.roadnet.Feature;
import com.codeflyz.roadnet.RoadList;
import com.codeflyz.roadnet.Segment;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jameswilfong
 */
public class JRoadList extends RoadList implements Drawable {

    private Color color = Color.LIGHT_GRAY;

    public JRoadList() {
        super();
    }

    public JRoadList(int idx) {
        super(idx);
    }

    public void update() {

    }

    public void draw(Graphics g) {
        //System.out.println("draw roadlist");
        // draw MBR
        //g.setColor(color);
        //getMBR().draw(g); // draw roadlist mbr

        // draw road segment
        //g.setColor(color);
        Coordinate p1 = this.get(0).getCentroid();

        for (Segment f : this) {// draw segments

            ((JSegment) f).draw(g);
            // g.setColor(Color.red);
        }
        // draw sign
        Segment seg = (Segment) this.getLast();
        Coordinate c1 = seg.get(0);
        Coordinate c2 = seg.get(1);

        int width = 10;
        int height = 10;

        g.setColor(Color.RED);
        //g.fillRect(lastDrawX - 1, lastDrawY - 1, width + 2, height + 2);
        //g.fillRect(5, 0, 75, 30);

        /* Coordinate pp = MathMethods.calculate_perp_point(c1, c2, 15);
        g.drawOval((int)pp.x - (width/2), (int)pp.y- (height/2), width, height);
         */
        g.drawString("" + getIdx(), (int) p1.x, (int) p1.y);
    }

    public JRoadList setColour(Color color) {
        setColor(color);
        return this;
    }

    public void setColor(Color color) {
        this.color = color;

    }
}
