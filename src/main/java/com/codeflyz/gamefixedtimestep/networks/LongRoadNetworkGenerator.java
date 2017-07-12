package com.codeflyz.gamefixedtimestep.networks;

import com.codeflyz.gamefixedtimestep.roads.JRoadList;
import com.codeflyz.gamefixedtimestep.roads.JRoadNetwork;
import com.codeflyz.gamefixedtimestep.roads.JSegment;
import com.codeflyz.roadnet.Coordinate;



import java.awt.Color;

/**
 *
 * @author jameswilfong
 *
 *
 */
public class LongRoadNetworkGenerator extends JRoadNetwork implements NetworkGenerator {

    double segmentLength = 15;

    public LongRoadNetworkGenerator(double segmentLength) {
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

        double x = (double) 50.0 + (6 * segmentLength);
        double y = (double) 150.0 + (3 * segmentLength);
        int segs = 3;
        ////////////////
        // diagonal
        //////
        
        for (int idx = 0; idx < 2; idx++) {

            Coordinate startPoint = new Coordinate(x, y);

            //JRoadList roadList = getHorizontal(startPoint, segs, 1);
            JRoadList roadList = getDiagonal(startPoint, segs, 1);
            this.add(roadList);

            x = this.getLast().getLast().getLast().x;
            y = this.getLast().getLast().getLast().y;

        } 
        ////////////////////////
        // alternating verticals
        //////////

        x = (double) 50.0;//+ (double) (3.0 * segmentLength);
        y = (double) 150.0;//+ (double) (3.0 * segmentLength);
        int direction = 1;
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
        direction = 1;
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
        direction = -1;
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
        direction = -1;
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

    public JRoadList getVertical(Coordinate startPoint, int segments, int direction) {
        JRoadList roadList = new JRoadList();
        //System.out.println("vertical: " + segmentLength);
        roadList.add(  new  JSegment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x,
                startPoint.y + (double) segmentLength * direction
        ).setColour(Color.BLACK).setPostedSpeed((double) 10)
        );
        
        for (int i = 1; i < segments; i++) {

            roadList.add(  new  JSegment(
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

    public JRoadList getHorizontal(Coordinate startPoint, int segments, int direction) {
        JRoadList roadList = new JRoadList();
        //System.out.println("horizontal: " + segmentLength);
        roadList.add(  new  JSegment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x + (segmentLength * direction),
                startPoint.y
        ).setColour(Color.BLACK).setPostedSpeed((double) 15)
        );
        for (int i = 1; i < segments+3; i++) {

            roadList.add(  new  JSegment(
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

    public JRoadList getDiagonal(Coordinate startPoint, int segments, int direction) {
        JRoadList roadList = new JRoadList();
        //System.out.println("diagonal: " + segmentLength);
        roadList.add(  new  JSegment(
                0,
                startPoint.x,
                startPoint.y,
                startPoint.x + (segmentLength * direction),
                startPoint.y + (segmentLength * direction)
        ).setColour(Color.BLACK).setPostedSpeed((double) 15)
        );
        for (int i = 1; i < segments; i++) {

            roadList.add(  new  JSegment(
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
