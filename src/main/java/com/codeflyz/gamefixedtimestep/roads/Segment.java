/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeflyz.gamefixedtimestep.roads;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jameswilfong
 */
public class Segment extends Feature {

    // color, speed
    //private boolean occupied = false; // is car on this segment
    // public double speedMax = 5;
    //private boolean occupied = false;
    private int occupied = 0; // 0 = false; 0 > true
    private double postedSpeed = 0; //speed limit posted from the road network
    private double tailingSpeed = 0; // set when occupied is set
    //public int length = 30;    // car length
    //private Color color = Color.LIGHT_GRAY;

    public Segment(int idx, double x, double y, double x2, double y2) {

        setIdx(idx);

        this.add(new Coordinate(x, y));
        this.add(new Coordinate(x2, y2));

        setColour(Color.LIGHT_GRAY);

    }

    public Double getPostedSpeed() {

        return postedSpeed;
    }

    public Segment setPostedSpeed(double roadSpeed) {
        this.postedSpeed = roadSpeed;
        return this;
    }

    public Double getTailingSpeed() {

        return tailingSpeed;
    }

    public void setTailingSpeed(double occupiedSpeed) {
        if(occupiedSpeed < 1.0){
           this.tailingSpeed = 1.0;
        }else{
          this.tailingSpeed = occupiedSpeed;
        }
      
    }

    public Coordinate getFirst() {
        return get(0);
    }

    public boolean isOccupied() {
        return occupied > 0;
    }

    /**
     * this is magic ... be careful... handles more than one occupier at a time
     *
     * @param carSpeed
     */
    public void setOccupied(boolean occupied) {
        if (occupied) {
            this.occupied++;
        } else {
            this.occupied--;
        }
    }

    public Segment setColour(Color color) {
        setColor(color);
        return this;
    }

    public boolean contains(Coordinate coord) {
        if (coord == null) {
            return false;
        }
        return getMBR().contains(coord);
    }

    public boolean contains(Feature feature) {
        if (feature == null) {
            return false;
        }
        return getMBR().contains(feature);
    }

    public void update(GamePanel gp) {
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

    public String toString() {
        // {"idx":[idx],"feature":[coordinates],"color":[color],"speed":[speed],"}
        String rc = "{\"idx\":[idx],\"speed\":[speed],\"tailingspeed\":[tailingspeed],\"occupied\":[occupied],\"coordinates\":[[features]],\"color\":\"[color]\"}";
        rc = rc.replace("[idx]", "" + getIdx())
                .replace("[speed]", "" + this.getPostedSpeed())
                .replace("[tailingspeed]", "" + this.getTailingSpeed())
                .replace("[occupied]", "" + this.isOccupied())
                .replace("[features]", super.toString())
                .replace("[color]", "" + getColor().getRGB());

        return rc;
    }

}
