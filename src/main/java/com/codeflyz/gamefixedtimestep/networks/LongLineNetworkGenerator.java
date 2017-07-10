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
public class LongLineNetworkGenerator extends RoadNetwork implements NetworkGenerator {

    double segmentLength = 15;

    public LongLineNetworkGenerator(double segmentLength) {
        this.segmentLength = segmentLength;
        run();
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
        ////////////////
        // diagonal
        //////
        
        for (int idx = 0; idx < 5; idx++) {

            Coordinate startPoint = new Coordinate(x, y);

            RoadList roadList = getDiagonal(startPoint, segs, 1);
            this.add(roadList);

            x = this.getLast().getLast().getLast().x;
            y = this.getLast().getLast().getLast().y;

        } 


       
    }

    public RoadList getVertical(Coordinate startPoint, int segments, int direction) {
        RoadList roadList = new RoadList();
        //System.out.println("vertical: " + segmentLength);
        roadList.add((Segment) new Segment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x,
                startPoint.y + (double) segmentLength * direction
        ).setColour(Color.BLACK).setPostedSpeed((double) 15)
        );
        
        for (int i = 1; i < segments; i++) {

            roadList.add((Segment) new Segment(
                    i,
                    startPoint.x,
                    startPoint.y + ((double) i * segmentLength * direction),
                    startPoint.x,
                    startPoint.y + (double) (((i + 1)) * segmentLength * direction)
            ).setColour(Color.LIGHT_GRAY).setPostedSpeed((double) 15)
            );
        }

        return roadList;
    }

    public RoadList getHorizontal(Coordinate startPoint, int segments, int direction) {
        RoadList roadList = new RoadList();
        //System.out.println("horizontal: " + segmentLength);
        roadList.add((Segment) new Segment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x + (segmentLength * direction),
                startPoint.y
        ).setColour(Color.BLACK).setPostedSpeed((double) 15)
        );
        for (int i = 1; i < segments; i++) {

            roadList.add((Segment) new Segment(
                    i,
                    startPoint.x + ((double) i * segmentLength * direction),
                    startPoint.y,
                    startPoint.x + (double) (((i + 1)) * segmentLength * direction),
                    startPoint.y
            ).setColour(Color.LIGHT_GRAY).setPostedSpeed((double) 15)
            );
        }
        return roadList;
    }

    public RoadList getDiagonal(Coordinate startPoint, int segments, int direction) {
        RoadList roadList = new RoadList();
        //System.out.println("diagonal: " + segmentLength);
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
            ).setColour(Color.LIGHT_GRAY).setPostedSpeed((double) 15)
            );
        }
        return roadList;
    }

}
