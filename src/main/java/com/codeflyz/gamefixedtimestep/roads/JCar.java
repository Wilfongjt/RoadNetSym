package com.codeflyz.gamefixedtimestep.roads;

import com.codeflyz.roadnet.Car;
import com.codeflyz.roadnet.Coordinate;
import com.codeflyz.roadnet.Driver;
import com.codeflyz.roadnet.RoadList;
import com.codeflyz.roadnet.RoadNetwork;
import com.codeflyz.roadnet.Segment;
import java.awt.Color;
import java.awt.Graphics;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;
import static javax.swing.UIManager.get;

/**
 *
 * @author jameswilfong
 */
public class JCar extends Car implements Drawable {

    int lastDrawX, lastDrawY;

    private Coordinate lastLoc = null;
    private double interpolation;
    private Color color = Color.GRAY;

    public JCar(int idx, Driver driver, RoadNetwork roadNetwork) {
        super(idx, driver, roadNetwork);
        this.lastLoc = new Coordinate(this.get(0).x, this.get(0).y);

        this.lastDrawX = (int) this.get(0).x;

        this.lastDrawY = (int) this.get(0).y;
    }

    public Car setInterpolation(double interp) {
        interpolation = interp;
        return this;
    }

    public Color getColour() {
        return this.color;
    }

    public void setColour(Color color) {
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;

    }

    public double getInterpolation() {
        return interpolation;
    }

    public void update() {
 
        this.lastLoc = new Coordinate(this.get(0).x, this.get(0).y); // stash current point

        // update curren point
        //System.out.println("update size: " + this.getTail().size());
        Coordinate nextCoord = new Coordinate(
                get(0).x + getVelocity().getVX(),// calculate next X
                get(0).y + getVelocity().getVY()// calculate next Y
        );
        //System.out.println("update next coord: " + nextCoord);
        //System.out.println("update velocity: " + getVelocity());
        // assume we are continuing on the same segment at same velocity

        // get segment
        //Segment tseg = getCurrentRoad().getSegment(carCoord);
        //System.out.println("car: " + carCoord + " IN " + tseg);
        Segment nextSeg = null; // placeholder for next segment
        Coordinate estCurrentCoordinate = null; // place holder for car coord
        switch (getUpdateStatus(nextCoord)) {
            case 0: // full stop in place
                //System.out.println("0 full stop");
                //setVelocity(new Velocity()); // full stop
                setColour(Color.RED);   // Dead in the Road RED

                break;
            case 1: // update location on current segment
                //System.out.println("1 update location on current segment");
                setColour(Color.GRAY); // steady as you go GRAY
                this.get(0).assign(nextCoord);
                break;
            case 2: // inner road segment with overshoot aka next segment
                //System.out.println("2 inner road segment with overshoot aka next segment");
                // get next segment
                // update speed hence velocity
                int nextSegIdx = getTail().getFirst().getIdx() + 1;
                nextSeg = this.getCurrentRoad().get(nextSegIdx);

                //Segment nextSeg = getCurrentRoad().getSegment(nextCoord);
                // occupied
                // unoccupied
                if (nextSeg.isOccupied()) {
                    //setColor(Color.ORANGE); // caution orange

                } else {
                    //setColor(Color.GRAY); // steady as you go GRAY
                    //nextSeg.setOccupied(this.getSpeed()); 
                }

                addTailSegment(nextSeg, getDriver());
                //getTail().addFirst(nextSeg);
                //setSpeedVelocityAndTail(nextSeg); // current road speed (actual or occupied)

                // adjust location
                // estimate next coord
                estCurrentCoordinate = new Coordinate(
                        getTail().getFirst().get(0).x + getVelocity().getVX(),
                        getTail().getFirst().get(0).y + getVelocity().getVY()
                );
                this.get(0).assign(estCurrentCoordinate);

                break;
            case 3: // extra road, extra segment but still has more segments
                System.out.println("3 extra road, extra segment but still has more segments");
                break;
            case 4: // extra road segement overshoot 
                //System.out.println("4 extra road segement overshoot ");
                //System.out.println("cur coord:            " + this.get(0));
                setColour(Color.BLACK);
                // get next road
                // adjust the location to new seg direction and velocity 
                RoadList nextRoadList = getNextRoadDecision(getCurrentRoad());
                nextSeg = nextRoadList.get(0); // get first segment
                // occupied
                // unoccupied
                if (nextSeg.isOccupied()) {
                    //setColor(Color.ORANGE); // caution orange
                } else {
                    //setColor(Color.GRAY); // steady as you go GRAY
                    //nextSeg.setOccupied(this.getSpeed());
                    //nextSeg.setOccupied(true); 
                }
                setCurrentRoad(nextRoadList);
                addTailSegment(nextSeg, getDriver());
                //getTail().addFirst(nextSeg);
                //setSpeedVelocityAndTail(nextSeg);  // current road speed (actual or occupied)

                // adjust location
                // estimate next coord
                estCurrentCoordinate = new Coordinate(
                        getTail().getFirst().get(0).x + getVelocity().getVX(),
                        getTail().getFirst().get(0).y + getVelocity().getVY()
                );
                // System.out.println("estCurrentCoordinate: " + estCurrentCoordinate);
                this.get(0).assign(estCurrentCoordinate);

                break;

        }

    }

    public void draw(Graphics g) {
        //System.out.println("  car draw: " + this.get(0)); 
        //g.setColor(getBackground());
        //g.setColor(Color.lightGray);
        //g.fillOval(lastDrawX - 1, lastDrawY - 1, width + 2, height + 2);// shadow
        //g.fillRect(5, 0, 75, 30);

        int drawX = (int) ((get(0).x - this.lastLoc.x) * getInterpolation() + this.lastLoc.x - getWidth() / 2);
        int drawY = (int) ((get(0).y - this.lastLoc.y) * getInterpolation() + this.lastLoc.y - getHeight() / 2);

        lastDrawX = drawX;
        lastDrawY = drawY;

        g.setColor(getColor());
        g.fillOval(drawX, drawY, getWidth(), getHeight());
        /*
        String show
                = "" + this.getIdx()
                + "(rs:" // road speed 
                + trimFloat(this.getSpeed())
                + ", a: " // attentiveness 
                + trimFloat(this.driver.speedPsyc)
                + ")"

                ;
        g.drawString(
                show,
                drawX, drawY);
         */
        //System.out.println("car: " + this.toString());
    }
}
