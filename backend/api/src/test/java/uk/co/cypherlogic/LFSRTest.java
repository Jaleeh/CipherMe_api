package uk.co.cypherlogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit Test Suite for LFSR Class
 *
 * @author ESRS Group 2
 * @version 2022-02-13
 */
public class LFSRTest {

    LFSR instance;
    Bit zero;
    Bit one;

    public LFSRTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            instance = new LFSR("101", "101");
            zero = new Bit(0);
            one = new Bit(1);
        } catch (Exception ex) {
            fail("The test case is a prototype.");
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getLfsr method, of class LFSR.
     */
    @Test
    public void testGetLfsr() {
        System.out.println("getLfsr");
        BitArray expResult = new BitArray("101");
        BitArray result = instance.getLfsr();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTaps method, of class LFSR.
     */
    @Test
    public void testGetTaps() {
        System.out.println("getTaps");
        ArrayList<Integer> expResult = new ArrayList<>(Arrays.asList(0, 2));
        ArrayList<Integer> result = instance.getTaps();
        assertEquals(expResult, result);
    }

    /**
     * Test of step method, of class LFSR.
     */
    @Test
    public void testStep() {
        System.out.println("step");
        Bit expResult = one;
        Bit result = instance.step();
        assertEquals(expResult, result);
    }

    /**
     * Test of doSteps method, of class LFSR.
     */
    @Test
    public void testDoSteps() {
        System.out.println("doSteps");
        int num_steps = 15;
        BitArray expResult = new BitArray("101001110100111");
        BitArray result = instance.doSteps(num_steps);
        assertEquals(expResult, result);
    }

}
