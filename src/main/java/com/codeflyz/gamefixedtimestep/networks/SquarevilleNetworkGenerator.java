/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeflyz.gamefixedtimestep.networks;

import com.codeflyz.gamefixedtimestep.networks.NetworkGenerator;
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
public class SquarevilleNetworkGenerator extends RoadNetwork implements NetworkGenerator {

    double segmentLength = 30;

    public SquarevilleNetworkGenerator(double segmentLength) {
        this.segmentLength = segmentLength;
        run();
    }

    @Override
    public void run() {

        load();

    }

    private class Direction {

        double x = 1;
        double y = 1;

        public Direction(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    @Override
    public void load() {
        int segmentLength = 50;
        int segs = 3;
        int cols = 4;
        int rows = 3;
        int rowSpacing = segmentLength * segs;
        int colSpacing = segmentLength * segs;
        double x = (double) 50.0;
        double y = (double) 150.0;
        Direction direction = null;
        //////
        // Horizontal
        ////
        x = (double) x;//+ (double) (3.0 * segmentLength);
        y = (double) y;//150.0;//+ (double) (3.0 * segmentLength);
        direction = new Direction(1, 1);
        for (int i = 0; i < rows + 1; i++) {
            for (int idx = 0; idx < cols; idx++) {
                // lower steps
                Coordinate startPoint = new Coordinate(x, y);
                //direction *= -1;
                RoadList roadList = this.getHorizontal(startPoint, segs, direction);
                this.add(roadList);
                x = this.getLast().getLast().getLast().x;
                y = this.getLast().getLast().getLast().y;

            }
            direction = new Direction((direction.x * -1), 1);
            y = this.getLast().getLast().getLast().y + rowSpacing;
        }

        //////
        // Vertical
        ////
        x = (double) 50.0;
        y = (double) 150.0;
        direction = new Direction(1, 1);
        for (int c = 0; c < cols + 1; c++) {
            for (int idx = 0; idx < rows; idx++) {
                // lower steps
                Coordinate startPoint = new Coordinate(x, y);
                //direction *= -1;
                RoadList roadList = this.getVertical(startPoint, segs, direction);
                this.add(roadList);
                x = this.getLast().getLast().getLast().x;
                y = this.getLast().getLast().getLast().y;

            }

            direction = new Direction(1, direction.y * -1);
            x = this.getLast().getLast().getLast().x + rowSpacing;

        }

        //////////
        /// Diagonal
        ///
        x = (double) 50.0;
        y = rowSpacing * (rows + 1);
        for (int i = 0; i < rows; i++) {
            Coordinate startPoint = new Coordinate(x, y);
            direction = new Direction(1, -1);
            RoadList roadList = this.getDiagonal(startPoint, segs, direction);
            this.add(roadList);
            x = this.getLast().getLast().getLast().x;
            y = this.getLast().getLast().getLast().y;

        }
        
        
        
        //////////
        /// Diagonal
        ///
        x = 50.0 +colSpacing;
        y = 2 * rowSpacing;
        
            Coordinate startPoint = new Coordinate(x, y);
            direction = new Direction(-1, -1);
            RoadList roadList = this.getDiagonal(startPoint, segs, direction);
            this.add(roadList);

        
    }

    public RoadList getVertical(Coordinate startPoint, int segments, Direction direction) {
        RoadList roadList = new RoadList();
        //System.out.println("vertical: " + segmentLength);
        roadList.add((Segment) new Segment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x,
                startPoint.y + (double) (segmentLength * direction.y)
        ).setColour(Color.BLACK).setPostedSpeed((double) 5)
        );

        for (int i = 1; i < segments; i++) {

            roadList.add((Segment) new Segment(
                    i,
                    startPoint.x,
                    startPoint.y + (double) (i * segmentLength * direction.y),
                    startPoint.x,
                    startPoint.y + (double) (((i + 1)) * segmentLength * direction.y)
            ).setColour(Color.LIGHT_GRAY).setPostedSpeed((double) 5)
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
                startPoint.x + (double) (segmentLength * direction.x),
                startPoint.y
        ).setColour(Color.BLACK).setPostedSpeed((double) 5)
        );
        for (int i = 1; i < segments; i++) {

            roadList.add((Segment) new Segment(
                    i,
                    startPoint.x + (double) (i * segmentLength * direction.x),
                    startPoint.y,
                    startPoint.x + (double) (((i + 1)) * segmentLength * direction.x),
                    startPoint.y
            ).setColour(Color.LIGHT_GRAY).setPostedSpeed((double) 5)
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
                startPoint.x + (double) (segmentLength * direction.x),
                startPoint.y + (double) (segmentLength * direction.y)
        ).setColour(Color.BLACK).setPostedSpeed((double) 30)
        );
        for (int i = 1; i < segments; i++) {

            roadList.add((Segment) new Segment(
                    i,
                    startPoint.x + (double) (i * segmentLength * direction.x),
                    startPoint.y + (double) (i * segmentLength * direction.y),
                    startPoint.x + (double) (((i + 1)) * segmentLength * direction.x),
                    startPoint.y + (double) (((i + 1)) * segmentLength * direction.y)
            ).setColour(Color.LIGHT_GRAY).setPostedSpeed((double) 30)
            );
        }
        return roadList;
    }
}
