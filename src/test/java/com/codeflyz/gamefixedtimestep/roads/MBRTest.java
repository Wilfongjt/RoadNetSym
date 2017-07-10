/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeflyz.gamefixedtimestep.roads;

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
public class MBRTest {

    public MBRTest() {
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
     * Test of getMIN method, of class MBR.
     */
    /*
    @Test
    public void testGetMIN() {
        System.out.println("getMIN");
        MBR instance = new MBR();
        Coordinate expResult = null;
        Coordinate result = instance.getMIN();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of getMAX method, of class MBR.
     */
    /*
    @Test
    public void testGetMAX() {
        System.out.println("getMAX");
        MBR instance = new MBR();
        Coordinate expResult = null;
        Coordinate result = instance.getMAX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of contains method, of class MBR.
     */
    @Test
    public void testContains_CoordinateXLower() {
        System.out.println("contains");

        //Coordinate coord = new Coordinate((double)453.06027,(double)403.06027);
        Coordinate coord = new Coordinate((double) 450.00, (double) 403.06027);
        Coordinate min = new Coordinate((double) 450.0, (double) 400.0);
        Coordinate max = new Coordinate((double) 500.0, (double) 450.0);

        MBR instance = new MBR();

        instance.add(max);
        instance.add(min);

        boolean expResult = true;
        boolean result = instance.contains(coord);

        assertEquals(expResult, result);

    }

    @Test
    public void testContains_CoordinateXUpper() {
        System.out.println("contains");

        //Coordinate coord = new Coordinate((double)453.06027,(double)403.06027);
        Coordinate coord = new Coordinate((double) 500.00, (double) 403.06027);
        Coordinate min = new Coordinate((double) 450.0, (double) 400.0);
        Coordinate max = new Coordinate((double) 500.0, (double) 450.0);

        MBR instance = new MBR();

        instance.add(max);
        instance.add(min);

        boolean expResult = true;
        boolean result = instance.contains(coord);

        assertEquals(expResult, result);

    }
@Test
    public void testContains_CoordinateYUpper() {
        System.out.println("contains");

        //Coordinate coord = new Coordinate((double)453.06027,(double)403.06027);
        Coordinate coord = new Coordinate((double) 500.00, (double) 450.0);
        Coordinate min = new Coordinate((double) 450.0, (double) 400.0);
        Coordinate max = new Coordinate((double) 500.0, (double) 450.0);

        MBR instance = new MBR();

        instance.add(max);
        instance.add(min);

        boolean expResult = true;
        boolean result = instance.contains(coord);

        assertEquals(expResult, result);

    }
    @Test
    public void testNOTContains_CoordinateYUpper() {
        System.out.println("contains");

        //Coordinate coord = new Coordinate((double)453.06027,(double)403.06027);
        Coordinate coord = new Coordinate((double) 500.00, (double) 450.0 + (double)0.1);
        Coordinate min = new Coordinate((double) 450.0, (double) 400.0);
        Coordinate max = new Coordinate((double) 500.0, (double) 450.0);

        MBR instance = new MBR();

        instance.add(max);
        instance.add(min);
        System.out.println("coord: " + coord);
        System.out.println("mbr: " + instance);

        boolean expResult = false;
        boolean result = instance.contains(coord);

        assertEquals(expResult, result);

    }

    @Test
    public void testContains_CoordinateYLower() {
        System.out.println("contains");

        //Coordinate coord = new Coordinate((double)453.06027,(double)403.06027);
        Coordinate coord = new Coordinate((double) 500.00, (double) 400.0);
        Coordinate min = new Coordinate((double) 450.0, (double) 400.0);
        Coordinate max = new Coordinate((double) 500.0, (double) 450.0);

        MBR instance = new MBR();

        instance.add(max);
        instance.add(min);

        boolean expResult = true;
        boolean result = instance.contains(coord);

        assertEquals(expResult, result);

    }
    @Test
    public void testNOTContains_CoordinateYLower() {
        System.out.println("contains");

        //Coordinate coord = new Coordinate((double)453.06027,(double)403.06027);
        Coordinate coord = new Coordinate((double) 500.00, (double) 400.0 - (double)0.0001);
        Coordinate min = new Coordinate((double) 450.0, (double) 400.0);
        Coordinate max = new Coordinate((double) 500.0, (double) 450.0);

        MBR instance = new MBR();

        instance.add(max);
        instance.add(min);

        boolean expResult = false;
        boolean result = instance.contains(coord);

        assertEquals(expResult, result);

    }

    /**
     * Test of contains method, of class MBR.
     */
    /*
    @Test
    public void testContains_Feature() {
        System.out.println("contains");
        Feature feature = null;
        MBR instance = new MBR();
        boolean expResult = false;
        boolean result = instance.contains(feature);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of getWidth method, of class MBR.
     */
    /*
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        MBR instance = new MBR();
        double expResult = 0.0F;
        double result = instance.getWidth();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of getHeight method, of class MBR.
     */
    /*
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        MBR instance = new MBR();
        double expResult = 0.0F;
        double result = instance.getHeight();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of union method, of class MBR.
     */
    /*
    @Test
    public void testUnion() {
        System.out.println("union");
        MBR mbr = null;
        MBR instance = new MBR();
        boolean expResult = false;
        boolean result = instance.union(mbr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of add method, of class MBR.
     */
    /*
    @Test
    public void testAdd() {
        System.out.println("add");
        Coordinate c = null;
        MBR instance = new MBR();
        boolean expResult = false;
        boolean result = instance.add(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of update method, of class MBR.
     */
    /*
    @Test
    public void testUpdate() {
        System.out.println("update");
        GamePanel gp = null;
        MBR instance = new MBR();
        instance.update(gp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of draw method, of class MBR.
     */
    /*
    @Test
    public void testDraw() {
        System.out.println("draw");
        Graphics g = null;
        MBR instance = new MBR();
        instance.draw(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of toString method, of class MBR.
     */
    /*
    @Test
    public void testToString() {
        System.out.println("toString");
        MBR instance = new MBR();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */

}
