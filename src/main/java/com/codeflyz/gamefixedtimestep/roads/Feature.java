package com.codeflyz.gamefixedtimestep.roads;

import com.codeflyz.gamefixedtimestep.RoadNetSym;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author jameswilfong
 */
// Feature is a list of Coordinates: idx, visible, color, interpolation
public class Feature extends ArrayList<Coordinate> {

    private int idx = -1;
    private boolean visible = true;
    private Color color = Color.RED;

    public double interpolation = 0;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void update(GamePanel gp) {

    }

    public void draw(Graphics g) {

    }

    public Coordinate getLast() {
        return get(size() - 1);
    }

    public Coordinate getCentroid() {
        MBR mbr = getMBR();
        double cx = (mbr.get(0).x + mbr.get(1).x) / (double) 2.0; // min x
        double cy = (mbr.get(0).y + mbr.get(1).y) / (double) 2.0; // min y
        return new Coordinate(cx, cy);
    }

    public MBR getMBR() {
        MBR mbr = new MBR();
        for (Coordinate c : this) {

            mbr.add(c);
        }
        return mbr;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String toString() {
        // {"feature":[coordinates]}
        String rc = "[coordinates]";
        String lst = "";

        for (Coordinate c : this) {
            if (lst.length() > 0) {
                lst += ",";
            }
            lst += c.toString();
        }
        rc = rc.replace("[coordinates]", lst);
        return rc;
    }

    public boolean equals(Feature feature) {
        if(feature == null){return false;}
        boolean rc = true;
        for (int i = 0; i < size(); i++) {
            if (!this.get(i).equals(feature.get(i))) {
                rc = false;
            }
        }
        return rc;
    }

}
