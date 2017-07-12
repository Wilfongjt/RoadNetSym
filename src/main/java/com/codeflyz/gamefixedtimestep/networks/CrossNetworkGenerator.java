package com.codeflyz.gamefixedtimestep.networks;

import com.codeflyz.gamefixedtimestep.roads.JRoadNetwork;
import com.codeflyz.gamefixedtimestep.roads.JSegment;

import com.codeflyz.roadnet.Coordinate;
import com.codeflyz.roadnet.JRoadList;

import java.awt.Color;

/**
 *
 * @author jameswilfong
 *
 *
 */
public class CrossNetworkGenerator extends JRoadNetwork implements NetworkGenerator {

    double segmentLength = 15;

    public CrossNetworkGenerator(double segmentLength) {
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

       // System.out.println("load roads");
        // left to right
        //int segmentLength = 60;
        //Link transLink = new Link();

        double x = (double) 50.0;
        double y = (double) 150.0;
        int segs = 3;
        ////////////////
        // diagonal left to right down
        //////
        Direction direction =  new Direction(1,1);
        for (int idx = 0; idx < 3; idx++) {

            Coordinate startPoint = new Coordinate(x, y);

            //JRoadList roadList = getHorizontal(startPoint, segs, 1);
            JRoadList roadList = getDiagonal(startPoint, segs, direction);
            this.add(roadList);

            x = this.getLast().getLast().getLast().x;
            y = this.getLast().getLast().getLast().y;

        } 
        
        x = (double) 50.0;
        y = this.getLast().getLast().getLast().y;
        segs = 3;
        ////////////////
        // diagonal 
        //////
        direction =  new Direction(1,-1);
        for (int idx = 0; idx < 3; idx++) {

            Coordinate startPoint = new Coordinate(x, y);

            //JRoadList roadList = getHorizontal(startPoint, segs, 1);
            JRoadList roadList = getDiagonal(startPoint, segs, direction);
            this.add(roadList);

            x = this.getLast().getLast().getLast().x;
            y = this.getLast().getLast().getLast().y;

        } 
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
            JRoadList roadList = getVertical(startPoint, segs, direction);
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
            JRoadList roadList = this.getHorizontal(startPoint, segs, direction);
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
            JRoadList roadList = getVertical(startPoint, segs, direction);
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
            JRoadList roadList = this.getHorizontal(startPoint, segs, direction);
            this.add(roadList);
            x = this.getLast().getLast().getLast().x;
            y = this.getLast().getLast().getLast().y;

        }
       
    }

    public JRoadList getVertical(Coordinate startPoint, int segments, Direction direction) {
        JRoadList roadList = new JRoadList();
        //System.out.println("vertical: " + segmentLength);
        JSegment seg = new JSegment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x,
                startPoint.y + (double)(segmentLength * direction.y)
        );
        seg.setColour(Color.BLACK);
        seg.setPostedSpeed((double) 15);
        
        roadList.add(seg);
        
        for (int i = 1; i < segments; i++) {

            roadList.add(  new JSegment(
                    i,
                    startPoint.x,
                    startPoint.y + (double) (i * segmentLength * direction.y),
                    startPoint.x,
                    startPoint.y + (double) (((i + 1)) * segmentLength * direction.y)
            ).setPostedSpeed((double) 15)
            );
        }

        return roadList;
    }

    public JRoadList getHorizontal(Coordinate startPoint, int segments, Direction direction) {
        JRoadList roadList = new JRoadList();
        //System.out.println("horizontal: " + segmentLength);
        
        
        
        roadList.add(  new  JSegment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x + (double)(segmentLength * direction.x),
                startPoint.y
        ).setColour(Color.BLACK).setPostedSpeed((double) 15)
        );
        for (int i = 1; i < segments; i++) {

            roadList.add(  new  JSegment(
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

    public JRoadList getDiagonal(Coordinate startPoint, int segments, Direction direction) {
        JRoadList roadList = new JRoadList();
        //System.out.println("diagonal: " + segmentLength);
        roadList.add(  new  JSegment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x + (double)(segmentLength * direction.x),
                startPoint.y + (double)(segmentLength * direction.y)
        ).setColour(Color.BLACK).setPostedSpeed((double) 15)
        );
        for (int i = 1; i < segments; i++) {

            roadList.add(  new  JSegment(
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
