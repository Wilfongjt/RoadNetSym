package com.codeflyz.gamefixedtimestep.roads;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

/**
 * car starts off at default speed. push speed into stack.
 *
 * car drives down the road eating segements and pooping them out on the road.
 *
 * Other cars cannot drive on segments while car is digesting them.
 *
 * At first segment, push segment into
 *
 * @author jameswilfong
 */
/**
 * Car is a Feature: RoadNetwork, RoadList, RoadSegment, Driver, width, height,
 * xVelocity, yVelocity, speed,
 */
public class Car extends Feature {

    private RoadNetwork roadNetwork = null;
    private RoadList currRoad = null;
    private Tail tail = new Tail(); // FIFO for segments which the car is driving over
    //private Stack<Double> speeds = new Stack<Double>();

    private Coordinate lastLoc = null;
    int lastDrawX, lastDrawY;
    int width = 10, height = 10;
    //private Velocity velocity = null;

    private double interpolation;
    private Driver driver = new Driver();// init a driver for this car
    int lastLinkIdx = 0;  // stash next when waiting

    //public Car(int idx, double x, double y, double speed, int w, int h) {
    public Car(int idx, Driver driver, RoadNetwork roadNetwork) {
        //System.out.println("======== new Car");
        setIdx(idx);
        setDriver(driver);
        //getTail().setDriver(driver);
        this.roadNetwork = roadNetwork; // network car is in
        RoadList firstRoad = roadNetwork.get(idx);
        //System.out.println("firstRoad: " + firstRoad);
        Segment firstSegment = firstRoad.getFirst();
        //System.out.println("firstSegment: " + firstSegment);
        Coordinate carCoord = new Coordinate().assign(firstSegment.getFirst());
        //System.out.println("carCoord: " + carCoord);
        add(carCoord);

        setCurrentRoad(firstRoad);

        //this.getTail().addFirst(firstSegment);
        addTailSegment(firstSegment, driver);

        // set speed will update the velocity
        this.lastLoc = new Coordinate(this.get(0).x, this.get(0).y);

        this.lastDrawX = (int) this.get(0).x;

        this.lastDrawY = (int) this.get(0).y;

    }

    public void addTailSegment(Segment seg, Driver driver) {

        getTail().addSegment(seg, this.driver);

    }

    public RoadNetwork getRoadNetwork() {
        return roadNetwork;
    }

    public void setRoadNetwork(RoadNetwork roadNetwork) {
        this.roadNetwork = roadNetwork;
    }

    public Velocity getVelocity() {
        return this.getTail().getVelocity();
    }

    /*
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }
     */
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Tail getTail() {

        return this.tail;
    }

    public void setCurrentRoad(RoadList road) {

        this.currRoad = road;
        //this.getTail().addFirst((Segment) road.get(0));
        //this.updateSpeedAndVelocity(this.tail.getFirst().getSpeed());
        //this.updateSpeedAndVelocity(this.tail.getFirst().getSpeed());
    }

    public RoadList getCurrentRoad() {
        return this.currRoad;
    }

    //public Car updateSpeedAndVelocity(Double speed) {
    /*
    public Car setSpeedVelocityAndTail(Segment segment) {
        //this.speed = speed;
        //this.speeds.push(speed*this.driver.speedPsyc);

        if (!segment.isOccupied()) {
            // tailing speed
            //setSpeed(segment.getSpeed());
            setSpeed(segment);
            segment.setTailingSpeed(
                    this.getSpeed()
            );

        } else {

            System.out.println("is occupied @ " + segment.getTailingSpeed());
            //setSpeed(segment.getTailingSpeed());
            setSpeed(segment);
        }

        this.getTail().addFirst(segment);

        this.setVelocity(
          this.getTail().getVelocity()
        );
        


        return this;
    }*/

 /*public Double getSpeed() {

        return this.speeds.peek(); // feedback speed

    }*/
 /*
    public Double getSpeed(Driver driver) {

        //return this.speeds.peek() * driver.speedPsyc; // feedback speed
        return this.speeds.peek(); // feedback speed
    }
     */
    public Double getSpeed() {

        return this.getTail().getDrivingSpeed();
    }

    /*
    public void setSpeed(Double speed) {
        for (int i = this.speeds.size(); i > 1; i--) {
            this.speeds.pop();
        }
        this.speeds.push(speed);
    }*/
 /*
    public void setSpeed(Segment segment) {
        if (segment.isOccupied()) {
            this.speeds.clear();
            this.speeds.push(getDriver().getAdjustedSpeed(segment.getSpeed()));
            this.speeds.push(getDriver().getAdjustedSpeed(segment.getTailingSpeed()));
        } else {
            // clear speeds
            // push new road speed
            this.speeds.clear();
            this.speeds.push(getDriver().getAdjustedSpeed(segment.getSpeed()));
            segment.setTailingSpeed(getDriver().getAdjustedSpeed(segment.getSpeed()));
        }

    }*/
    //public void addRoadSegment(Segment roadsegment) {
    //}
    public MBR getMBR() {
        MBR mbr = new MBR();
        double dw = (double) width / (double) 2.0;
        double dh = (double) height / (double) 2.0;
        mbr.add(new Coordinate(this.get(0).x - dw, this.get(0).y - dh));
        mbr.add(new Coordinate(this.get(0).x + dw, this.get(0).y + dh));
        return mbr;
    }

    public Car setInterpolation(double interp) {
        interpolation = interp;
        return this;
    }

    public double getInterpolation() {
        return interpolation;
    }

    public Car setDimensions(int w, int h) {
        this.width = w;
        this.height = h;
        return this;
    }

    public String toString() {
        // {"idx":[idx],"feature":[feature],"speed":[speed],"width":[width],"height":[height],"color":[color]}
        // {"idx":[idx],"x":[x],"y":[y],"speed":[speed],"width":[width],"height":[height],"color":[color]}
        String rc = "{\"idx\":[idx],\"mbr\":[[mbr]],\"coordinates\":[[coordinates]],\"velocity\":[velocity],\"speed\":[speed],\"width\":[width],\"height\":[height],\"color\":[color]}";
        rc = rc.replace("[idx]", "" + getIdx())
                .replace("[coordinates]", super.toString())
                .replace("[velocity]", getVelocity().toString())
                .replace("[mbr]", getMBR().toString())
                .replace("[speed]", "" + this.getSpeed())
                .replace("[width]", "" + width)
                .replace("[height]", "" + height)
                .replace("[color]", "" + getColor().getRGB());
        return rc;
    }

    /**
     * randomly pick next road to drive
     *
     * if next road's first segment is blocked stop and wait
     *
     * @param prevRoadList
     * @return
     */
    public RoadList getNextRoadDecision(RoadList prevRoadList) {
        RoadList rl = null;
        int nextIdx = 0;
        int randomLinkIdx = 0;

        switch (prevRoadList.getLinkIndices().size()) {
            case 0: /// no more road then blocked
                //blocked = Constants.blocked;// 
                return null;
            //break;
            case 1:// pick first cause it is only choice
                randomLinkIdx = 0;
                break;
            default:
                // more than one road
                // select random index
                /* if (blocked == Constants.blocked) {
                    randomLinkIdx = this.lastLinkIdx; // selected on last pass
                } else {
                 */
                int max = prevRoadList.getLinkIndices().size();
                //
                randomLinkIdx = ThreadLocalRandom
                        .current()
                        .nextInt(0, max); // random index
            /*}*/
        }
        // need to add random selection here
        // if (prevRoadList.getLinkIndices().size() > 0) {
        //   randomIdx = 0;
        //}
        nextIdx = prevRoadList
                .getLinkIndices()
                .get(randomLinkIdx);// go to 0 index

        Link link = roadNetwork.getLinks().get(nextIdx); // next link

        if (link.next > -1) {

            rl = roadNetwork.get(link.next);

        }
        return rl;
    }

    /*
    public void setOccupied(Double occupidedSpeed) {
        for (Segment roadseg : this.tail) {
            roadseg.setOccupiedSpeed(occupidedSpeed);
        }
    }*/
    /**
     * x - car position
     *
     * y - car position
     *
     * velocity (dx, dy)
     *
     * road
     *
     * segment
     *
     *
     *
     * @param gp
     *
     * https://docs.google.com/document/d/1PjaGqXIm0JjpqkjoTnzQ4eem2aR5tzx37Rl-80g-r3o/edit#
     */
    /*
      Use Cases
      Legend
        V is a vertex
          
        . point of evaluation
        x = in segment, in road  
        o = out of segment, out of road, more segments (visual reference only)
        s = overshoot of segment
        e = out of segment, out of road, no more segments in road
        +---+ 
        |   |  MBR
        +---+

      BACKGROUND
       A Segment, two points, unidirectional 
    
         seg in point --> V         V = vertex
                           x        x = coordinate on the segment
                            x
                             V  <-- seg out point 
    
       A Road, multiple segments, unidirectional, one in point, one out point  
          
                                   V 
                                  x x                     V   <-- out point 
                                 x   VxxxVxxxxV         x 
       road in point -->  V     x             x       x       
                           x   x              x     x     
                            x x               x   x       
                             V                x x         
                                              V  
    
       A Road with MBR
                          +--------V----------------------+   
                          |       x x                     V   <-- out point 
                          |      x   VxxxVxxxxV         x |
       road in point -->  V     x             x       x   |    
                          |x   x              x     x     |
                          | x x               x   x       |
                          |  V                x x         |
                          +-------------------V-----------+      
    
       A Road with Overshoots 
                                      o
                                     o                          e
                                    o                         e
                          +--------V----------------------+ e  
                          |       x x                     V   <-- out point 
                          |      x   VxxxVxxxxVsss      x |
       road in point -->  V     x     s       x       x   |    
                          |x   x       s      x     x     |
                          | x x         s     x   x       |
                          |  V                x x         |
                          +---s---------------V-----------+  
                               o              o
                                o             o
                                              o
      CASES
        (1)  point IN road, IN current segement, and MORE segments
        (2)  point IN road, NOT In current segment, and MORE segments 
        (3)  point NOT IN road, NOT IN current segment, and MORE segments
        (4)  point NOT IN road, NOT IN current segment, and NO MORE segments  
    
    Case 1: point IN road, IN current segement, and MORE segments
                                      o
                                     o                          e
                                    o                         e
                          +--------V----------------------+ e  
                          |       x x                     V    
                          |      x   VxxxVxxxxVsss      x |
                       -  V     x     s       x       x   |    
       current segment |  |.(1)x       s      x     x     |
                       |  | x x         s     x   x       |
                       -  |  V                x x         |
                          +---s---------------V-----------+  
                               o              o
                                o             o
                                              o
    Case 2: point IN road, NOT In current segment, and MORE segments 
    
                                      o
                           CS        o                          e
                          |--|      o                         e
                          +--------V----------------------+ e  
                          |       x x                     V    
                          |      x   VxxxVxxxxVsss      x |
                       -  V     x     s       x       x   |    CS = current segment
                    CS |  |x   x       s      x     x     |
                       |  | x x         s     x   x       |
                       -  |  V                x x         |
                          +---.(2)------------V-----------+  
                               o              o
                                o             o
                                              o    
    
    Case 3: point NOT IN road, NOT IN current segment, and MORE segments
                                      o
                               CS    .  (3)                     e
                             |-----|o                         e
                       -  +--------V----------------------+ e  
                       |  |       x x                     V    
                   CS  |  |      x   VxxxVxxxxVsss      x |
                       |  V     x     s       x       x   |    
                       |  |x   x       s      x     x     |
                       |  | x x         s     x   x       |
                       -  |  V                x x         |
                          +---s---------------V-----------+  
                               o              o
                                o             o
                                              o        
    
    
    Case 4: point NOT IN road, NOT IN current segment, and NO MORE segments
                                      o
                                     o              CS          e
                                    o         |-----------|   . (4)
                          +--------V----------------------+ e  
                          |       x x                     V  - 
                          |      x   VxxxVxxxxVsss      x |  |
                          V     x     s       x       x   |  |  
                          |x   x       s      x     x     |  |  CS
                          | x x         s     x   x       |  |
                          |  V                x x         |  |
                          +---s---------------V-----------+  -
                               o              o
                                o             o
                                              o            
        
    
     */
    public short getUpdateStatus(Coordinate nextLocation) {
        // full stop
        // still in the same segment
        // segment or road has changed
        // full stop
        boolean inCurrentRoad = this.getCurrentRoad().getMBR().contains(nextLocation);
        boolean inCurrentSegment = getTail().getFirst().contains(nextLocation);

        if (inCurrentRoad && inCurrentSegment) { // same segment
            return 1; // same segment
        }

        int nextSegmentIndex = getTail().getFirst().getIdx() + 1;
        boolean moreSegs = (nextSegmentIndex < getCurrentRoad().size());

        if (inCurrentRoad && !inCurrentSegment) {// in road, not in seg
            if (moreSegs) {  // next segment with overshoot
                return 2; // overshoot segment
            }
        }

        if (!inCurrentRoad && !inCurrentSegment) {// out of road, not in seg
            if (moreSegs) { // next segment with overshoot
                return 3;// out of road but more segments
            }
        }
        // extra road segment overshoot
        if (!inCurrentRoad && !inCurrentSegment) { // out of road, not in seg
            if (!moreSegs) { // no more segments
                return 4; // out of road and no more segments
            }
        }

        return 0; // full stop
    }

    public void update(GamePanel gp) {
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
                setColor(Color.RED);   // Dead in the Road RED

                break;
            case 1: // update location on current segment
                //System.out.println("1 update location on current segment");
                setColor(Color.GRAY); // steady as you go GRAY
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

                addTailSegment(nextSeg, driver);
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
                setColor(Color.BLACK);
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
                addTailSegment(nextSeg, driver);
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

        int drawX = (int) ((get(0).x - this.lastLoc.x) * getInterpolation() + this.lastLoc.x - width / 2);
        int drawY = (int) ((get(0).y - this.lastLoc.y) * getInterpolation() + this.lastLoc.y - height / 2);

        lastDrawX = drawX;
        lastDrawY = drawY;

        g.setColor(getColor());
        g.fillOval(drawX, drawY, width, height);
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
        System.out.println("car: " + this.toString());
    }

    public String trimFloat(double speed) {
        String rc = "" + speed;
        if (rc.length() > 6) {
            rc = rc.substring(0, 5);
        }
        return rc;
    }

}
