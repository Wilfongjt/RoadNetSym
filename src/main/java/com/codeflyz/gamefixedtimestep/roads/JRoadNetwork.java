/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeflyz.gamefixedtimestep.roads;

import com.codeflyz.roadnet.RoadList;
import com.codeflyz.roadnet.RoadNetwork;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jameswilfong
 */
public class JRoadNetwork extends RoadNetwork implements Drawable {

    public void update() {

    }

    public void draw(Graphics g) {
        //g.setColor(color);
        //getMBR().draw(g);  // draw roadnetwork mbr
        for (RoadList f : this) {
            ((JRoadList)f).draw(g);
        }
        g.drawString("cars: " + this.size() + " roads: " + this.size(), 20, 20);
    }


    public void setColour(Color color) {
        // color is not defined at network level
    }
}
