package com.codeflyz.gamefixedtimestep.networks;

import com.codeflyz.gamefixedtimestep.roads.Coordinate;
import com.codeflyz.gamefixedtimestep.roads.Link;
import com.codeflyz.gamefixedtimestep.roads.RoadList;
import com.codeflyz.gamefixedtimestep.roads.RoadNetwork;
import com.codeflyz.gamefixedtimestep.roads.Segment;
import java.awt.Color;

/**
 *
 * @author jameswilfong
 *
 *
 */
public class DiamondNetworkGenerator extends RoadNetwork implements NetworkGenerator {

    double segmentLength = 15;

    public DiamondNetworkGenerator(double segmentLength) {
        this.segmentLength = segmentLength;
        run();
    }

    private class Direction {
       double x = 1;
       double y = 1;
       public Direction(double x, double y){
          this.x = x;
          this.y = y;
       }
    }
    @Override
    public void run() {

        load();

    }

    @Override
    public void load() {

        //System.out.println("load roads");
        // left to right
        //int segmentLength = 60;
        //Link transLink = new Link();

        double x = (double) 50.0;
        double y = (double) 150.0;
        int segs = 3;
        Direction direction;
        //////
        // Horizontal
        ////
        x = (double) x;//+ (double) (3.0 * segmentLength);
        y = (double) y;//150.0;//+ (double) (3.0 * segmentLength);
        direction = new Direction(1,1);
        for (int idx = 0; idx < 3; idx++) {
            // lower steps
            Coordinate startPoint = new Coordinate(x, y);
            //direction *= -1;
            RoadList roadList = this.getHorizontal(startPoint, segs, direction);
            this.add(roadList);
            x = this.getLast().getLast().getLast().x;
            y = this.getLast().getLast().getLast().y;

        }
        
        ////////////////
        // diagonal left to right down
        //////
        direction =  new Direction(1,1);
        for (int idx = 0; idx < 3; idx++) {

            Coordinate startPoint = new Coordinate(x, y);

            //RoadList roadList = getHorizontal(startPoint, segs, 1);
            RoadList roadList = getDiagonal(startPoint, segs, direction);
            this.add(roadList);

            x = this.getLast().getLast().getLast().x;
            y = this.getLast().getLast().getLast().y;

        } 
       
        x = this.getLast().getLast().getLast().x;
        y = this.getLast().getLast().getLast().y;
        segs = 3;
        ////////////////
        // diagonal 
        //////
        direction =  new Direction(-1,1);
        for (int idx = 0; idx < 3; idx++) {

            Coordinate startPoint = new Coordinate(x, y);

            //RoadList roadList = getHorizontal(startPoint, segs, 1);
            RoadList roadList = getDiagonal(startPoint, segs, direction);
            this.add(roadList);

            x = this.getLast().getLast().getLast().x;
            y = this.getLast().getLast().getLast().y;

        }  
         x = this.getLast().getLast().getLast().x;
        y = this.getLast().getLast().getLast().y;
        segs = 3;
        ////////////////
        // diagonal 
        //////
        direction =  new Direction(-1,-1);
        for (int idx = 0; idx < 3; idx++) {

            Coordinate startPoint = new Coordinate(x, y);

            //RoadList roadList = getHorizontal(startPoint, segs, 1);
            RoadList roadList = getDiagonal(startPoint, segs, direction);
            
            this.add(roadList);

            x = this.getLast().getLast().getLast().x;
            y = this.getLast().getLast().getLast().y;

        } 
        ////////////////
        // diagonal 
        //////
        direction =  new Direction(1,-1);
        for (int idx = 0; idx < 3; idx++) {

            Coordinate startPoint = new Coordinate(x, y);

            //RoadList roadList = getHorizontal(startPoint, segs, 1);
            RoadList roadList = getDiagonal(startPoint, segs, direction);
            this.add(roadList);

            x = this.getLast().getLast().getLast().x;
            y = this.getLast().getLast().getLast().y;

        }         
        
        if(1==1){return;}
        ////////////////////////
        // alternating verticals
        //////////

        x = (double) 50.0;//+ (double) (3.0 * segmentLength);
        y = (double) 150.0;//+ (double) (3.0 * segmentLength);
        direction = new Direction(1,1);
        for (int idx = 0; idx < 3; idx++) {
            // lower steps
            Coordinate startPoint = new Coordinate(x, y);
            //direction *= -1;
            RoadList roadList = getVertical(startPoint, segs, direction);
            this.add(roadList);
            x = this.getLast().getLast().getLast().x;
            y = this.getLast().getLast().getLast().y;

        }
        ////////////////////////
        // alternating verticals
        //////////

        x = (double) 50.0;//+ (double) (3.0 * segmentLength);
        y = (double) this.getLast().getLast().getLast().y;//150.0;//+ (double) (3.0 * segmentLength);
        direction = new Direction(1,1);
        for (int idx = 0; idx < 3; idx++) {
            // lower steps
            Coordinate startPoint = new Coordinate(x, y);
            //direction *= -1;
            RoadList roadList = this.getHorizontal(startPoint, segs, direction);
            this.add(roadList);
            x = this.getLast().getLast().getLast().x;
            y = this.getLast().getLast().getLast().y;

        }
        ////////////////////////
        // alternating verticals
        //////////

        x = (double) this.getLast().getLast().getLast().x;//+ (double) (3.0 * segmentLength);
        y = (double) this.getLast().getLast().getLast().y;//+ (double) (3.0 * segmentLength);
        direction = new Direction(-1,-1);
        for (int idx = 0; idx < 3; idx++) {
            // lower steps
            Coordinate startPoint = new Coordinate(x, y);
            //direction *= -1;
            RoadList roadList = getVertical(startPoint, segs, direction);
            this.add(roadList);
            x = this.getLast().getLast().getLast().x;
            y = this.getLast().getLast().getLast().y;

        }
        /////////
        //
        ////
        x = (double) this.getLast().getLast().getLast().x;//+ (double) (3.0 * segmentLength);
        y = (double) this.getLast().getLast().getLast().y;
        direction = new Direction(-1,-1);
        for (int idx = 0; idx < 3; idx++) {
            // lower steps
            Coordinate startPoint = new Coordinate(x, y);
            //direction *= -1;
            RoadList roadList = this.getHorizontal(startPoint, segs, direction);
            this.add(roadList);
            x = this.getLast().getLast().getLast().x;
            y = this.getLast().getLast().getLast().y;

        }
       
    }

    public RoadList getVertical(Coordinate startPoint, int segments, Direction direction) {
        RoadList roadList = new RoadList();
        //System.out.println("vertical: " + segmentLength);
        roadList.add((Segment) new Segment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x,
                startPoint.y + (double)(segmentLength * direction.y)
        ).setColour(Color.BLACK).setPostedSpeed((double) 15)
        );
        
        for (int i = 1; i < segments; i++) {

            roadList.add((Segment) new Segment(
                    i,
                    startPoint.x,
                    startPoint.y + (double) (i * segmentLength * direction.y),
                    startPoint.x,
                    startPoint.y + (double) (((i + 1)) * segmentLength * direction.y)
            ).setColour(Color.LIGHT_GRAY).setPostedSpeed((double) 15)
            );
        }

        return roadList;
    }

    public RoadList getHorizontal(Coordinate startPoint, int segments, Direction direction) {
        RoadList roadList = new RoadList();
        //System.out.println("horizontal: " + segmentLength);
        roadList.add((Segment) new Segment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x + (double)(segmentLength * direction.x),
                startPoint.y
        ).setColour(Color.BLACK).setPostedSpeed((double) 15)
        );
        for (int i = 1; i < segments; i++) {

            roadList.add((Segment) new Segment(
                    i,
                    startPoint.x + (double) (i * segmentLength * direction.x),
                    startPoint.y,
                    startPoint.x + (double) (((i + 1)) * segmentLength * direction.x),
                    startPoint.y
            ).setColour(Color.LIGHT_GRAY).setPostedSpeed((double) 15)
            );
        }
        return roadList;
    }

    public RoadList getDiagonal(Coordinate startPoint, int segments, Direction direction) {
        RoadList roadList = new RoadList();
        //System.out.println("diagonal: " + segmentLength);
        roadList.add((Segment) new Segment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x + (double)(segmentLength * direction.x),
                startPoint.y + (double)(segmentLength * direction.y)
        ).setColour(Color.BLACK).setPostedSpeed((double) 15)
        );
        for (int i = 1; i < segments; i++) {

            roadList.add((Segment) new Segment(
                    i,
                    startPoint.x + (double) (i * segmentLength * direction.x),
                    startPoint.y + (double) (i * segmentLength * direction.y),
                    startPoint.x + (double) (((i + 1)) * segmentLength * direction.x),
                    startPoint.y + (double) (((i + 1)) * segmentLength * direction.y)
            ).setColour(Color.LIGHT_GRAY).setPostedSpeed((double) 15)
            );
        }
        return roadList;
    }

}
