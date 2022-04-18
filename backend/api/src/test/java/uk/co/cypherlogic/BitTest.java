package uk.co.cypherlogic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit Test for Bit class
 *
 * @author ESRS Group 2
 * @version 2022-02-10
 */
public class BitTest {

    Bit zero;
    Bit one;

    public BitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        zero = new Bit();
        one = new Bit('1');
    }

    @After
    public void tearDown() {
    }

    // Test Constructing a Bit
    @Test
    public void TestInitBit() {
        assertEquals(true, zero.isZero());
        assertEquals(false, zero.isOne());
        assertEquals(false, one.isZero());
        assertEquals(true, one.isOne());

        // Test Constructor using an int
        Bit intConOne = new Bit(1);
        Bit intConZero = new Bit(0);
        assertEquals(true, intConOne.isOne());
        assertEquals(true, intConZero.isZero());
        intConOne = new Bit(100);
        assertEquals(true, intConOne.isOne());
        intConOne = new Bit(-100);
        assertEquals(true, intConOne.isOne());

        // Test Constructor using a boolean
        Bit boolConOne = new Bit(true);
        Bit boolConZero = new Bit(false);
        assertEquals(true, boolConOne.isOne());
        assertEquals(true, boolConZero.isZero());

        // Test Constructor using a char
        Bit charConOne = new Bit('1');
        Bit charConZero = new Bit('0');
        assertEquals(true, charConOne.isOne());
        assertEquals(true, charConZero.isZero());
        charConOne = new Bit('T');
        assertEquals(true, charConOne.isOne());
        charConOne = new Bit('\n');
        assertEquals(true, charConOne.isOne());
        charConOne = new Bit('Γ');
        assertEquals(true, charConOne.isOne());
    }

    // Test flipping a Bit
    @Test
    public void testFlipBit() {
        zero.flip();
        assertEquals(false, zero.isZero());
        zero.flip();
        assertEquals(true, zero.isZero());
    }

    // Test XORing a Bit
    @Test
    public void testXorBit() {
        Bit bit = new Bit();

        // 0 XOR 0 == 0
        bit.xor(zero);
        assertEquals(true, bit.isZero());

        // 0 XOR 1 == 1
        bit.xor(one);
        assertEquals(true, bit.isOne());

        // 1 XOR 0 == 1
        bit.xor(zero);
        assertEquals(true, bit.isOne());

        // 1 XOR 1 == 0
        bit.xor(one);
        assertEquals(true, bit.isZero());
    }

    // Test overrided toString Method
    @Test
    public void testBitToString() {
        assertEquals(true, "0".equals(zero.toString()));
        assertEquals(true, "1".equals(one.toString()));
        assertEquals(false, "ONE".equals(one.toString()));
        assertEquals(false, "\n".equals(one.toString()));
        assertEquals(false, "".equals(one.toString()));
    }

    // Test override equals method
    @Test
    public void testBitEquals() {
        Bit new_one = new Bit(1);
        Bit new_zero = new Bit(0);
        assertEquals(true, new_one.equals(one));
        assertEquals(false, new_one.equals(zero));
        assertEquals(false, new_zero.equals(one));
        assertEquals(true, new_zero.equals(zero));
        assertEquals(false, new_zero.equals(new_one));
    }

    // Test copying a Bit
    @Test
    public void testBitCopy() {
        Bit new_bit = one.copy();
        one.flip();  // one == 0
        assertEquals(new_bit.isOne(), one.isZero());
        one.flip(); // one == 1
        new_bit.flip(); // new_bit == 0
        assertEquals(new_bit.isOne(), one.isZero());
        assertEquals(false, new_bit.equals(one));
        assertEquals(true, new_bit.equals(zero));
    }
}
