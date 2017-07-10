/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class FishBoneNetworkGenerator extends RoadNetwork implements NetworkGenerator {

    double segmentLength = 30;

    public FishBoneNetworkGenerator(double segmentLength) {
        this.segmentLength = segmentLength;
        run();
    }

    @Override
    public void run() {

        load();

    }

   
    public void load() {

        //System.out.println("load roads");
        // left to right
        //int segmentLength = 30;
        //Link transLink = new Link();

        double x = (double) 50.0;
        double y = (double) 150.0;
        int segs = 3;
        ////////////////
        // diagonal
        //////
        for (int idx = 0; idx < 6; idx++) {

            Coordinate startPoint = new Coordinate(x, y);

            //RoadList roadList = getHorizontal(startPoint, segs, 1);
            RoadList roadList = getDiagonal(startPoint, segs, 1);
            this.add(roadList);

            x = this.getLast().getLast().getLast().x;
            y = this.getLast().getLast().getLast().y;

        }
       
        ////////////////////////
        // alternating verticals
        //////////
        
        x = (double) 50.0 ;//+ (double) (3.0 * segmentLength);
        y = (double) 150.0 ;//+ (double) (3.0 * segmentLength);
        int direction = 1;
        for (int idx = 0; idx < 2; idx++) {
            // lower steps
            Coordinate startPoint = new Coordinate(x, y);

            //direction *= -1;
            RoadList roadList = getVertical(startPoint, (segs*3), direction);
            this.add(roadList);

            //direction *= -1;
            this.add(getHorizontal(roadList.getLast().getLast(), (segs*3), direction) );
            
            //this.add(getDiagonal(roadList.getLast().getLast(), segs, direction) );
           
            // move down the diag
            x += (segs * segmentLength) * 3;
            y += (segs * segmentLength) * 3;
        }
       
        x = (double) 50.0 + (double) (3.0 * segmentLength);
        y = (double) 150.0 + (double) (3.0 * segmentLength);
        //x = this.getLast().getLast().getLast().x; //(double) 50.0 + (double) (3.0 * segmentLength);
        //y = this.getLast().getLast().getLast().y; //(double) 150.0 + (double) (3.0 * segmentLength);
        direction = -1;
        for (int idx = 0; idx < 6; idx++) {
            // upper steps
            Coordinate startPoint = new Coordinate(x, y);

            //direction *= -1;
            RoadList roadList = getVertical(startPoint, segs, direction);
            this.add(roadList);

            //direction *= -1;
            this.add(getHorizontal(roadList.getLast().getLast(), segs, direction) );
           
            // move down the diag
            x += segs * segmentLength;
            y += segs * segmentLength;
        }
        
        if(1==1){return;}
        // lower diaginal
      
        x = (double) 50.0 ;
        y = (double) 150.0 + (double) (3.0 * segmentLength);
        direction = 1;
        for (int idx = 0; idx < 4; idx++) {
            // lower steps
            Coordinate startPoint = new Coordinate(x, y);

            //direction *= -1;
            //RoadList roadList = getVertical(startPoint, segs, direction);
            RoadList roadList = getDiagonal(startPoint, segs,direction);
            this.add(roadList);

            //direction *= -1;
            //this.add(getHorizontal(roadList.getLast().getLast(), segs, direction) );
           
            // move down the diag
            x = roadList.getLast().getLast().x ;
            y = roadList.getLast().getLast().y ;
        }
        
        // upper diagonal
        
        x = (double) 50.0 + (double) (3.0 * segmentLength);
        y = (double) 150.0 ;
        direction = 1;
        for (int idx = 0; idx < 1; idx++) {
            // lower steps
            Coordinate startPoint = new Coordinate(x, y);

            //direction *= -1;
            //RoadList roadList = getVertical(startPoint, segs, direction);
            RoadList roadList = getDiagonal(startPoint, segs,direction);
            this.add(roadList);

            //direction *= -1;
            //this.add(getHorizontal(roadList.getLast().getLast(), segs, direction) );
           
            // move down the diag
            x = roadList.getLast().getLast().x ;
            y = roadList.getLast().getLast().y ;
        }
        

    }
     
    public RoadList getVertical(Coordinate startPoint, int segments, int direction) {
        RoadList roadList = new RoadList();
        roadList.add((Segment) new Segment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x,
                startPoint.y + (double) segmentLength * direction
        ).setColour(Color.BLACK).setPostedSpeed((double) 10)
        );
        for (int i = 1; i < segments; i++) {

            roadList.add((Segment) new Segment(
                    i,
                    startPoint.x,
                    startPoint.y + ((double) i * segmentLength * direction),
                    startPoint.x,
                    startPoint.y + (double) (((i + 1)) * segmentLength * direction)
            ).setColour(Color.LIGHT_GRAY).setPostedSpeed((double) 10)
            );
        }

        return roadList;
    }

    public RoadList getHorizontal(Coordinate startPoint, int segments, int direction) {
        RoadList roadList = new RoadList();
        roadList.add((Segment) new Segment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x + (segmentLength * direction),
                startPoint.y
        ).setColour(Color.BLACK).setPostedSpeed((double) 10)
        );
        for (int i = 1; i < segments; i++) {

            roadList.add((Segment) new Segment(
                    i,
                    startPoint.x + ((double) i * segmentLength * direction),
                    startPoint.y,
                    startPoint.x + (double) (((i + 1)) * segmentLength * direction),
                    startPoint.y
            ).setColour(Color.LIGHT_GRAY).setPostedSpeed((double) 10)
            );
        }
        return roadList;
    }

    public RoadList getDiagonal(Coordinate startPoint, int segments, int direction) {
        RoadList roadList = new RoadList();
        roadList.add((Segment) new Segment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x + (segmentLength * direction),
                startPoint.y + (segmentLength * direction)
        ).setColour(Color.BLACK).setPostedSpeed((double) 15)
        );
        for (int i = 1; i < segments; i++) {

            roadList.add((Segment) new Segment(
                    i,
                    startPoint.x + ((double) i * segmentLength * direction),
                    startPoint.y + ((double) i * segmentLength * direction),
                    startPoint.x + (double) (((i + 1)) * segmentLength * direction),
                    startPoint.y + (double) (((i + 1)) * segmentLength * direction)
            ).setColour(Color.LIGHT_GRAY).setPostedSpeed((double) 10)
            );
        }
        return roadList;
    }
}
