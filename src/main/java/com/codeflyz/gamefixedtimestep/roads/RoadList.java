package com.codeflyz.gamefixedtimestep.roads;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author jameswilfong
 */
/*
    RoadList is a list of road-segments.

    a road start is indicated when the previous link is -1
    a road end is indicated when the next link is -1
 */
public class RoadList extends ArrayList<Segment> {

    public int idx = -1;
    public Color color = Color.LIGHT_GRAY;
    private MBR mbr = new MBR();
    private ArrayList<Integer> linkIndices = new ArrayList<Integer>();

    public RoadList() {
    }

    public RoadList(int idx) {
        this.idx = idx;
    }
    public Segment getFirst(){
      return get(0);
    }
    public Feature getLast() {
        return get(size() - 1);
    }
    /**
     * retrieve a segment based on a coordinate
     * @param coord
     * @return 
     */
    public Segment getSegment(Coordinate coord){
    
        Segment rc = null;
        if (mbr.contains(coord)) { // in mbr so may actually match up
            for (Feature f : this) {
                if (f.getMBR().contains(coord)) {
                    rc = (Segment) f;
                    break;
                }
            }
        }
        
        return rc;
    }
    /**
     * 
     * test if coordinate is at begining or end of road segment
     * 
     * @param coord
     * @return boolean
     */
    public boolean connectsTo(Coordinate coord) {
        boolean rc = false;
        // is in mbr
        // does it match one of the end points
        if (mbr.contains(coord)) { // in mbr so may actually match up
            // does it have matching point
            Coordinate s = this.get(0).get(0); // start
            Coordinate e = this.getLast().getLast(); // end

            if (s.equals(coord)) { // coord matches start of road segement
                rc = true;
                //System.out.println("  s: "+s+"connectsTo " + coord);
            } else if (e.equals(coord)) { // coord matches end of road segment
                //System.out.println("end match");
                rc = true;
                //System.out.println("  e: "+e+"connectsTo " + coord);
            }
            rc = true;
        }
        return rc; // is coord in the MBR
    }
    
    public boolean connectsEtoS(Segment segment){// this.end to segment.start
       boolean rc = false;
       // does this end connect to segment start
       if(mbr.contains(segment.get(0))){ // is in mbr of roadLine
          Coordinate e = this.getLast().getLast();
          if(e.equals(segment.get(0))){ 
              rc = true;
          }
       }
       return rc;
    }
    
    public boolean connectsStoE(Segment segment){ // this.start to segment.end
       boolean rc = false;
       // does this end connect to segment start
       if(mbr.contains(segment.getLast())){ // is in mbr of roadLine
          Coordinate s = this.getLast().getLast();
          if(s.equals(segment.getLast())){
              rc = true;
          }
       }
       return rc;
    }

    public int getEndToStart(RoadList roadList) {
        int rc = -1;

        Coordinate s = this.get(0).get(0);
        Coordinate e = this.getLast().getLast();
        Coordinate s1 = roadList.get(0).get(0);
        Coordinate e1 = roadList.getLast().getLast();

        if(e.equals(s1)){
          rc = 1; //"ES";
        }
        if (s.equals(s1)) { // not start to start SS
            rc = 2; //"SS";
            //System.out.println("SS");
            return rc;
        }
        if (s.equals(e1)) { // SE
            //System.out.println("SE");
            rc = 3; //"SE";
            return rc;
        }
        if (e.equals(e1)) { // not begin to begin EE
            //System.out.println("EE");
            rc = 4; //"EE";
            return rc;
        }
        if(rc==-1){
          rc = 0;
        }

        return rc;
    }

    /**
     * is first or last coord of roadList in the MBR
     *
     * @param roadList
     * @return
     */
  
    
    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public MBR getMBR() {
        return mbr;
    }

    public ArrayList<Integer> getLinkIndices() {
        return linkIndices;
    }

    public boolean add(Segment feature) {
       
        getMBR().union(feature.getMBR());// make new mbr from two
 
        return super.add(feature);
    }

    public void update(GamePanel gp) {
        
        
    }

    public void draw(Graphics g) {
        //System.out.println("draw roadlist");
        // draw MBR
        //g.setColor(color);
        //getMBR().draw(g); // draw roadlist mbr

        // draw road segment
        //g.setColor(color);
        Coordinate p1 = this.get(0).getCentroid();
           
        for (Feature f : this) {// draw segments

            f.draw(g);
            // g.setColor(Color.red);
        }
        // draw sign
        Segment seg = (Segment) this.getLast();
        Coordinate c1 = seg.get(0);
        Coordinate c2 = seg.get(1);

        int width = 10;
        int height = 10;

        g.setColor(Color.RED);
        //g.fillRect(lastDrawX - 1, lastDrawY - 1, width + 2, height + 2);
        //g.fillRect(5, 0, 75, 30);

        /* Coordinate pp = MathMethods.calculate_perp_point(c1, c2, 15);
        g.drawOval((int)pp.x - (width/2), (int)pp.y- (height/2), width, height);
         */
        g.drawString("" + getIdx(), (int) p1.x, (int) p1.y);
    }

    public String toString() {
        // {"road":[seg]}
        String rc = "{\"idx\":[idx],[mbr],\"feature\":[\n[seg]\n],\"linkIndices\":[linkindices]}\n";
        String l = "";
        for (Feature f : this) {
            if (l.length() > 0) {
                l += "," + "\n";
            }
            l += f.toString();
        }
        rc = rc.replace("[idx]", "" + idx)
                .replace("[mbr]", getMBR().toString())
                .replace("[seg]", l)
                .replace("[linkindices]", getLinkIndices().toString());
        return rc;
    }
    
}
