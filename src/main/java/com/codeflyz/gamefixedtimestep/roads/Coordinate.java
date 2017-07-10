package com.codeflyz.gamefixedtimestep.roads;

/**
 *
 * @author jameswilfong
 */
public class Coordinate {

    public double x, y;

    public Coordinate(){}
    
    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Coordinate coord) {

        double dx = coord.x - this.x;
        double dy = coord.y - this.y;
        
        if( Math.abs(dx) >  Constants.tolerance ){ return false; }
        if( Math.abs(dy) >  Constants.tolerance ){ return false; }
        

        return true;
    }
    public Coordinate assign(Coordinate coord){
       this.x = coord.x;
       this.y = coord.y;
       return this;
    }

    public String toString() {
        // {"x":[x],"y":[y]}
        String rc = "{\"x\":[x],\"y\":[y]}";
        rc = rc.replace("[x]", "" + x)
                .replace("[y]", "" + y);

        return rc;
    }
}
