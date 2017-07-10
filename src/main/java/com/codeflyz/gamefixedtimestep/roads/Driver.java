package com.codeflyz.gamefixedtimestep.roads;

/**
 *
 * @author jameswilfong
 */
    /**
     * Driver is a set of doubleing point numbers representing the psychological
     * state of the driver: speedPsyc
     */
    public class Driver {

        public double speedPsyc = 1;  // 0.1 to 1.0 in .1 steps. 

        public Driver() {
            
            speedPsyc = (double) Math.random();
            if (speedPsyc < 0.4) {
                speedPsyc = (double)0.4 + speedPsyc;
            }
        }
        public Double getAdjustedSpeed(Double speed){
           return speed * speedPsyc;
        }
    }
