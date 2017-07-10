package com.codeflyz.gamefixedtimestep.roads;

/**
 *
 * @author jameswilfong
 */
    /**
     * Driver is a set of doubleing point numbers representing the psychological
     * state of the driver: focus
     */
    public class Driver {

        public double focus = 1;  // 0.1 to 1.0 in .1 steps. 

        public Driver() {
            
            focus = (double) Math.random();
            if (focus < 0.4) {
                focus = (double)0.4 + focus;
            }
        }
        public Double getAdjustedSpeed(Double speed){
           return speed * focus;
        }
    }
