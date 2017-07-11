/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeflyz.gamefixedtimestep.roads;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jameswilfong
 */
public interface Drawable {

    public void update();

    public void draw(Graphics g);

    public void setColour(Color color);
}
