package com.codeflyz.gamefixedtimestep.roads;

import com.codeflyz.roadnet.Coordinate;
import com.codeflyz.roadnet.Segment;
import java.awt.Color;
import java.awt.Graphics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jameswilfong
 */
public class SegmentTest {

    public SegmentTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getSpeed method, of class Segment.
     */
    /*
    @Test
    public void testGetSpeed() {
        System.out.println("getSpeed");
        Segment instance = null;
        Double expResult = null;
        Double result = instance.getSpeed();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of isOccupied method, of class Segment.
     */
    /*
    @Test
    public void testIsOccupied() {
        System.out.println("isOccupied");
        Segment instance = null;
        boolean expResult = false;
        boolean result = instance.isOccupied();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of setOccupied method, of class Segment.
     */
    /*
    @Test
    public void testSetOccupied() {
        System.out.println("setOccupied");
        double carSpeed = 0.0;
        Segment instance = null;
        instance.setOccupied(carSpeed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of contains method, of class Segment.
     */
    @Test
    public void testContains_Coordinate() {
        System.out.println("===== contains Coordinate");
        Coordinate coord = new Coordinate(950.0, 249.22455522745622);
        Segment instance = new Segment(0, 950.0, 250.0,
                 950.0, 200.0);
        boolean expResult = true;

        System.out.println("seg: " + instance);
        System.out.println("mbr: " + instance.getMBR());

        boolean result = instance.contains(coord);
        assertEquals(expResult, result);

    }

    @Test
    public void testEquals() {
        System.out.println("===== equals ");

        Segment segment = new Segment(0, 950.0, 250.0, 950.0, 200.0);
        Segment instance = new Segment(0, 950.0, 250.0, 950.0, 200.0);
        
        boolean expResult = true;

        System.out.println("segment: " + segment);
        System.out.println("instance: " + instance);

        boolean result = instance.contains(segment);
        
        assertEquals(expResult, result);

    }
    /**
     * Test of contains method, of class Segment.
     */
    /*
    @Test
    public void testContains_Feature() {
        System.out.println("contains");
        Feature feature = null;
        Segment instance = null;
        boolean expResult = false;
        boolean result = instance.contains(feature);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of update method, of class Segment.
     */
    /*
    @Test
    public void testUpdate() {
        System.out.println("update");
        GamePanel gp = null;
        Segment instance = null;
        instance.update(gp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of draw method, of class Segment.
     */
    /*
    @Test
    public void testDraw() {
        System.out.println("draw");
        Graphics g = null;
        Segment instance = null;
        instance.draw(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */

    /**
     * Test of setColor method, of class Segment.
     */
    /*
    @Test
    public void testSetColor() {
        System.out.println("setColor");
        Color color = null;
        Segment instance = null;
        Segment expResult = null;
        Segment result = instance.setColor(color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of setSpeedMax method, of class Segment.
     */
    /*
    @Test
    public void testSetSpeedMax() {
        System.out.println("setSpeedMax");
        double speedMax = 0.0;
        Segment instance = null;
        Segment expResult = null;
        Segment result = instance.setSpeedMax(speedMax);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of toString method, of class Segment.
     */
    /*
    @Test
    public void testToString() {
        System.out.println("toString");
        Segment instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
}
