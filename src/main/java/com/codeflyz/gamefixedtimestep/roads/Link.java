/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeflyz.gamefixedtimestep.roads;

/**
 *
 * @author jameswilfong
 */

    /**
     * Link is a pair of indices. pointing to next and previous objects
     */
    public class Link {

        public int idx = -1;  // index in the link-list
        public int next = -1; // index representing the next thing 
        public int prev = -1; // index representing the previous thing

        public Link() {

        }

        public Link set(int featureFrom, int featureTo) {

            this.prev = featureFrom;
            this.next = featureTo;
            return this;
        }

        public int getIdx() {
            return idx;
        }

        public Link setIdx(int idx) {
            this.idx = idx;
            return this;
        }

        public int getNext() {
            return next;
        }

        public Link setNext(int next) {
            this.next = next;
            return this;
        }

        public int getPrev() {
            return prev;
        }

        public Link setPrev(int prev) {
            this.prev = prev;
            return this;
        }

        public String toString() {
            // {"idx":[idx],"feature":[feature],"next":[next],"prev":[prev]}
            String rc = "{\"idx\":[idx],\"prev\":[prev],\"next\":[next]}";
            rc = rc.replace("[idx]", "" + idx)
                    .replace("[prev]", "" + prev)
                    .replace("[next]", "" + next);
            return rc;
        }
    }
