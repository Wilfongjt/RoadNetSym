package com.codeflyz.gamefixedtimestep.roads;

/**
 *
 * @author jameswilfong
 */
public class MathMethods {

    /**
     * calculates point a given distance perpendicular to a line
     * 
     * @param A
     * @param B
     * @param distance
     * @return 
     */
    public static Coordinate calculate_perp_point(Coordinate A, Coordinate B, double distance) {
        //System.out.println("A: " + A);
        //System.out.println("B: " + B);
        // vec2d M = (A + B) / 2;
        double mx = (A.x + B.x) / 2.0;
        double my = (A.y + B.y) / 2.0;
        //System.out.println("M(" + mx + ", " + my + ")");

        double px = B.x + (-1.0 * A.x);
        double py = B.y + (-1.0 * A.y);

        //System.out.println("p(" + px + ", " + py + ")");
        double nx = -1 * py;
        double ny = px;
        //System.out.println("n(" + nx + ", " + ny + ")");
        double norm_length = Math.sqrt(Math.pow(nx, 2) + Math.pow(ny, 2));
        //System.out.println("N: " + norm_length);

        double rx = nx / norm_length;
        double ry = ny / norm_length;
        //System.out.println("r(" + rx + ", " + ry + ")");

        double rcx = (mx + (distance * rx));
        double rcy = (my + (distance * ry));
        //System.out.println("rc(" + rcx + ", " + rcy + ")");

        return new Coordinate((double) rcx, (double) rcy);
    }
}
