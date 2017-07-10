package com.codeflyz.gamefixedtimestep.roads;

/**
 *
 * @author jameswilfong
 */
public class Velocity {

    double vx = 0;
    double vy = 0;

    //double driverPyscSpeed = 1;
    public Velocity() {
    }
    public Velocity(Segment roadSegment, double carSpeed) {
        //this.driverPyscSpeed = psycSpeed;
        double dx = roadSegment.get(1).x - roadSegment.get(0).x;
        double dy = roadSegment.get(1).y - roadSegment.get(0).y;
        
        this.vx = carSpeed * (dx / (Math.abs(dx) + Math.abs(dy)));
        this.vy = carSpeed * (dy / (Math.abs(dx) + Math.abs(dy)));
    }
/*
    public Velocity(Segment roadSegment, double carSpeed, double psycSpeed) {
        //this.driverPyscSpeed = psycSpeed;
        double dx = roadSegment.get(1).x - roadSegment.get(0).x;
        double dy = roadSegment.get(1).y - roadSegment.get(0).y;
        //System.out.println("speedMax: " + speedMax + "  psycSpeed: " + psycSpeed);
        this.vx = carSpeed * psycSpeed * (dx / (Math.abs(dx) + Math.abs(dy)));
        this.vy = carSpeed * psycSpeed * (dy / (Math.abs(dx) + Math.abs(dy)));
    }
*/
    public double getVX() {

        return vx;
    }

    public double getVY() {

        return vy;
    }

    public String toString() {
        // {"vx":[vx],"vy":[vy]}
        String rc = "{\"vx\":[vx],\"vy\":[vy]}";

        rc = rc.replace("[vx]", "" + vx)
                .replace("[vy]", "" + vy);
        return rc;
    }

}
