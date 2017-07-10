/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeflyz.gamefixedtimestep.roads;


import java.util.ArrayList;

/**
 *
 * @author jameswilfong
 */
  public class LinkList extends ArrayList<Link> {

        @Override
        public boolean add(Link link) {
            link.idx = size();
            return super.add(link);
        }
        public Link getLast(){
           return get(size()-1);
        }

        public String toString() {
            String links = "";
            for (Link link : this) {
                if (links.length() > 0) {
                    links += ",";
                }
                links += link.toString();
            }
            return links;

        }
    }
