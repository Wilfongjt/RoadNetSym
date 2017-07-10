/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeflyz.gamefixedtimestep.roads;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author jameswilfong
 */
/**
 * Road-Network is a list of roads: color, mbr, list of road to road links
 */
public class RoadNetwork extends ArrayList<RoadList> {

    //protected Color color = Color.BLACK;
    private MBR mbr = new MBR();
    private LinkList links = new LinkList();
    private int segmentCount =0;
    //private int capacity=0; // number of roads * number of segments 

    public RoadList getFirst(){return get(0);}
    
    public int getSegmentCount() {
        return segmentCount;
    }

    public void setSegmentCount(int segmentCount) {
        this.segmentCount = segmentCount;
    }

    public int getCapacity(){  
      return  segmentCount;
    }
    
    public boolean withIn(Feature feature) {
        return mbr.contains(feature);
    }

    public LinkList getLinks() {
        return links;
    }

    public RoadList getLast() {
        return get(size() - 1);
    }

    public MBR getMBR() {
        return mbr;
    }

    @Override
    public boolean add(RoadList roadList) {
        setSegmentCount(getSegmentCount() + roadList.size());
        roadList.setIdx(size());
        
        mbr.union(roadList.getMBR());
        
        autoLink(roadList);
        
        //capacity += roadList *; 
        return super.add(roadList);
    }
    
    public void draw(Graphics g) {
        //g.setColor(color);
        //getMBR().draw(g);  // draw roadnetwork mbr
        for (RoadList f : this) {
            f.draw(g);
        }
        g.drawString("capacity: "+getCapacity() + " segments: "+ this.getSegmentCount(), 20, 20);
    }

    public String toString() {
        // {"network":[roads]}
        String rc = "{\"network\":[\n[roads]],\"links\":[[links]]}";
        String l = "";
        for (RoadList rl : this) {
            if (l.length() > 0) {
                l += ",";
            }
            l += rl.toString();
        }
        rc = rc.replace("[roads]", l)
                .replace("[links]", getLinks().toString());
        return rc;
    }

    /**
     * convert road relationship code to human readable form
     * 
     * @param code
     * @return 
     */
    public String getConnectorDescription(int code) {
        String rc = "";
        switch (code) {
            case 0: // ??
                rc = "||"; /// parallel
                break;
            case 1: //ES
                rc = "ES";
                break;
            case 2: // SS
                rc = "SS";
                break;
            case 3: // SE
                rc = "SE";
                break;
            case 4: // EE
                rc = "EE";
        }
        return rc;
    }
   /**
     * create roadlist to roadlist links as added to network
     *
     * @param roadList
     */
    public void autoLink(RoadList roadList) {
        //System.out.println("build");
        if (this.size() < 1) {
            return;
        }

        // back side link
        // rL.end to roadlist.s   ES
        // rL.end to roadlist.e   EE
        // rL.start to roadlist.s SS
        // rL.start to roadlist.e SE
        int i = 0;
        for (RoadList rL : this) {
            //Link link = null;
            //System.out.println("i: test " + rL.getIdx() + " and " + roadList.getIdx() + " == " + getConnectorDescription(rL.getEndToStart(roadList)));
            switch (rL.getEndToStart(roadList)) {
                case 0: // || parallel

                    break;
                case 1: //ES
                    this.getLinks().add(new Link().set(rL.getIdx(), roadList.getIdx()));
                    rL.getLinkIndices().add(this.getLinks().getLast().getIdx());
                    break;
                case 2: // SS

                    break;
                case 3: // SE
                    this.getLinks().add(new Link().set(roadList.getIdx(), rL.getIdx()));
                    roadList.getLinkIndices().add(this.getLinks().getLast().getIdx());
                    break;
                case 4: // EE
                    break;

            }
        }
    }
}
