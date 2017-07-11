package com.codeflyz.gamefixedtimestep.roads;

import com.codeflyz.roadnet.MBR;
import java.awt.Color;
import java.awt.Graphics;
//import static javax.swing.UIManager.get;

/**
 *
 * @author jameswilfong
 */
public class JMBR extends MBR implements Drawable {

    private Color color = Color.LIGHT_GRAY;

    public JMBR() {
        super();
    }

    public void update() {

    }

    public void draw(Graphics g) {

        g.setColor(Color.LIGHT_GRAY);
        g.drawLine((int) get(0).x, (int) get(0).y, (int) get(0).x, (int) get(1).y);// top down
        g.drawLine((int) get(0).x, (int) get(1).y, (int) get(1).x, (int) get(1).y);// left to right
        g.drawLine((int) get(1).x, (int) get(1).y, (int) get(1).x, (int) get(0).y);// bottom up
        g.drawLine((int) get(1).x, (int) get(0).y, (int) get(0).x, (int) get(0).y);// right to left
    }

    public Color getColour() {
        return this.color;
    }

    public void setColour(Color color) {
        this.color = color;
    }

}
