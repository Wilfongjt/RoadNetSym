/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeflyz.gamefixedtimestep.roads;

import java.awt.Color;

/**
 *
 * @author jameswilfong
 */
public class GameState {
    private int frameCount = 0;
    private Color backround = Color.WHITE;

    public int getFrameCount() {
        return frameCount;
    }

    public GameState setFrameCount(int frameCount) {
        this.frameCount = frameCount;
        return this;
    }

    public Color getBackround() {
        return backround;
    }

    public GameState setBackround(Color backround) {
        this.backround = backround;
        return this;
    }
    
}
