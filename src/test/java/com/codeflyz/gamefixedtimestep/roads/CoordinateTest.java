/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeflyz.gamefixedtimestep.roads;

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
public class CoordinateTest {
    
    public CoordinateTest() {
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
     * Test of equals method, of class Coordinate.
     */
    
        @Test
    public void testNotEquals() {
        System.out.println("======== not equals");
        Coordinate coord =     new Coordinate((float)140.0,(float)330.10);
        Coordinate instance =  new Coordinate((float)140.0,(float)330.0);
        boolean expResult = false;
        boolean result = instance.equals(coord);
        //System.out.println("A " + (coord.x + Constants.tolerance));
        //System.out.println("B " + (coord.x - Constants.tolerance));
        System.out.println("expected: " + coord);
        System.out.println("  result: " + instance);
        
        assertEquals(expResult, result);
        

    }
  
    @Test
    public void testEquals() {
        System.out.println("======== equals");
        Coordinate coord =     new Coordinate((float)140.0000001,(float)330.0);
        Coordinate instance =  new Coordinate((float)140.0,(float)330.0);
        boolean expResult = true;
        boolean result = instance.equals(coord);
        System.out.println("expected: " + coord);
        System.out.println("  result: " + instance);
        
        assertEquals(expResult, result);
        

    }

    /**
     * Test of toString method, of class Coordinate.
     */
    /*
    @Test
    public void testToString() {
        System.out.println("toString");
        Coordinate instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
