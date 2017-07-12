package com.codeflyz.gamefixedtimestep.networks;

import com.codeflyz.gamefixedtimestep.roads.JRoadList;
import com.codeflyz.gamefixedtimestep.roads.JSegment;
import com.codeflyz.roadnet.Coordinate;

import com.codeflyz.roadnet.RoadNetwork;
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
                JRoadList roadList = this.getHorizontal(startPoint, segs, direction);
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
                JRoadList roadList = this.getVertical(startPoint, segs, direction);
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
            JRoadList roadList = this.getDiJagagonal(startPoint, segs, direction);
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
            JRoadList roadList = this.getDiJagagonal(startPoint, segs, direction);
            this.add(roadList);

            
            
        
    }

    public JRoadList getVertical(Coordinate startPoint, int segments, Direction direction) {
        JRoadList roadList = new JRoadList();
        //System.out.println("vertical: " + segmentLength);
        roadList.add( new JSegment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x,
                startPoint.y + (double) (segmentLength * direction.y)
        ).setColour(Color.BLACK).setPostedSpeed((double) 5)
        );

        for (int i = 1; i < segments; i++) {

            roadList.add( new JSegment(
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

    public JRoadList getHorizontal(Coordinate startPoint, int segments, Direction direction) {
        JRoadList roadList = new JRoadList();
        //System.out.println("horizontal: " + segmentLength);
        roadList.add(  new JSegment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x + (double) (segmentLength * direction.x),
                startPoint.y
        ).setColour(Color.BLACK).setPostedSpeed((double) 5)
        );
        for (int i = 1; i < segments; i++) {

            roadList.add(  new JSegment(
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

    public JRoadList getDiagonal(Coordinate startPoint, int segments, Direction direction) {
        JRoadList roadList = new JRoadList();
        //System.out.println("diagonal: " + segmentLength);
        roadList.add(  new JSegment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x + (double) (segmentLength * direction.x),
                startPoint.y + (double) (segmentLength * direction.y)
        ).setColour(Color.BLACK).setPostedSpeed((double) 30)
        );
        for (int i = 1; i < segments; i++) {

            roadList.add(  new JSegment(
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
    
        public JRoadList getDiJagagonal(Coordinate startPoint, int segments, Direction direction) {
        JRoadList roadList = new JRoadList();
        //System.out.println("diagonal: " + segmentLength);
        
        // create a diagonal
        // nudge the internal segement coordinates
        
        roadList.add(  new JSegment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x + (double) (segmentLength * direction.x),
                startPoint.y + (double) (segmentLength * direction.y)
        ).setColour(Color.BLACK).setPostedSpeed((double) 30)
        );
        for (int i = 1; i < segments; i++) {

            roadList.add(  new JSegment(
                    i,
                    startPoint.x + (double) (i * segmentLength * direction.x),
                    startPoint.y + (double) (i * segmentLength * direction.y),
                    startPoint.x + (double) (((i + 1)) * segmentLength * direction.x),
                    startPoint.y + (double) (((i + 1)) * segmentLength * direction.y)
            ).setColour(Color.LIGHT_GRAY).setPostedSpeed((double) 30)
            );
        }
        
        roadList.getFirst().getLast().y += 25.0;
        roadList.get(1).getFirst().y += 25.0;
        roadList.get(1).getLast().y -= 25.0;
        roadList.get(2).getFirst().y -= 25.0;
        
        return roadList;
    }
    
    
}
