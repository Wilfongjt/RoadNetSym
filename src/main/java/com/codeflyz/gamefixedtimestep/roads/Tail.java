package com.codeflyz.gamefixedtimestep.roads;

import java.util.ArrayDeque;

/**
 *
 * @author jameswilfong
 */
public class Tail extends ArrayDeque<Segment> {

    //@Override
    //public void addFirst(Segment segment) {

    public void addSegment(Segment segment,Driver driver) {
        //System.out.println("addFirst: 1");
        addFirst(segment);

        if(!segment.isOccupied()){// your attitude * posted speed
           // set occupied
           segment.setOccupied(true);
           segment.setTailingSpeed(driver.getAdjustedSpeed(segment.getPostedSpeed()));
        }else{ // your attitude * occupiers speed
           segment.setOccupied(true);
           segment.setTailingSpeed(driver.getAdjustedSpeed(segment.getTailingSpeed()));
        }
        
        if (this.size() > Constants.maxTailSegments) {
            this.getLast().setOccupied(false);
            this.removeLast();
        }
    }

    /**
     * get road speed when unoccupied otherwise get the tailing speed of the
     * segment
     *
     * @return
     */
     public Double getDrivingSpeed() {
        Segment seg = this.getFirst();
        if (seg.isOccupied()) {
            //System.out.println("getTailingSpeed: " + seg.getTailingSpeed());
            return seg.getTailingSpeed();
        }
        //System.out.println("getSpeed: " + seg.getSpeed());
        return seg.getPostedSpeed();
    }   


    /**
     * set all tail segment speeds
     *
     * @param speed
     */

    public Velocity getVelocity() {
        //System.out.println("getVelicity size: " + this.size());
        if (this.size() == 0) {
            //System.out.println("getVelocity 1");
            return new Velocity();
        }
        //System.out.println("getVelocity 2   seg: " + this.getFirst());
        //System.out.println("getVelocity 2 speed: " + this.getSpeed());
        return new Velocity(this.getFirst(), getFirst().getTailingSpeed() );
    }
    
}
