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
