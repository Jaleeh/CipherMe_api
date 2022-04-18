package uk.co.cypherlogic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit Test for BitArray class
 *
 * @author ESRS Group 2
 * @version 2022-02-10
 */
public class BitArrayTest {

    BitArray zeroes;
    BitArray ones;
    BitArray ba0001;
    BitArray ba0000;
    BitArray ba1111;
    BitArray ba1010;
    BitArray ba0101;
    BitArray baEmpty;

    public BitArrayTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        zeroes = new BitArray("0000000");
        ones = new BitArray("1111111111111");
        ba0001 = new BitArray("0001");
        ba0000 = new BitArray("0000");
        ba1111 = new BitArray("1111");
        ba1010 = new BitArray("1010");
        ba0101 = new BitArray("0101");
        baEmpty = new BitArray();

    }

    @After
    public void tearDown() {
    }

    // Test Constructor and toString.
    @Test
    public void testBitArrayInit() {
        assertTrue("1111111111111".equals(ones.toString()));
        assertFalse("1111110111111".equals(ones.toString()));
        assertFalse(" 1111111111111 ".equals(ones.toString()));
        assertTrue(baEmpty.toString().equals("00000000"));
    }

    // Test size() method, returns length of BitArray
    @Test
    public void testBitArraySize() {
        assertEquals(4, ba0000.size());
        assertEquals(7, zeroes.size());
        assertEquals(13, ones.size());
    }

    // Test getting and putting a Bit into a BitArray
    @Test
    public void testGetPutBit() {
        BitArray ba = new BitArray("1010");
        Bit zero = new Bit(0);
        Bit one = new Bit(1);

        ba.putBitAt(zero, 0);
        assertEquals(zero, ba.getBitAt(0));

        ba.putBitAt(one, 1);
        assertEquals(one, ba.getBitAt(1));

        ba.putBitAt(zero, 4);
        assertEquals(zero, ba.getBitAt(4));

        ba.putBitAt(one, 5);
        assertEquals(one, ba.getBitAt(5));

        ba.putBitAt(zero, 10);
        assertEquals(zero, ba.getBitAt(10));

        ba.putBitAt(one, 11);
        assertEquals(one, ba.getBitAt(11));

        ba.putBitAt(zero, -2);
        assertEquals(zero, ba.getBitAt(-2));

        ba.putBitAt(one, -1);
        assertEquals(one, ba.getBitAt(-1));

        assertTrue("0101".equals(ba.toString()));
    }

    // Test rotatation left and right, getLSB, getMSB
    @Test
    public void testBitArrayRotation() {
        ba0101.rotl(1);
        assertTrue("1010".equals(ba0101.toString()));
        ba0101.rotl(1);
        assertTrue("0101".equals(ba0101.toString()));
        ba0101.rotr(1);
        assertTrue("1010".equals(ba0101.toString()));
        ba0101.rotr(1);
        assertTrue("0101".equals(ba0101.toString()));

        assertTrue("1".equals(ba0001.getLsb().toString()));
        assertTrue("0".equals(ba0001.getMsb().toString()));
        assertTrue("0001".equals(ba0001.toString()));
        ba0001.rotr(2);
        assertTrue("0100".equals(ba0001.toString()));
        ba0001.rotl(3);
        assertTrue("0010".equals(ba0001.toString()));
        ba0001.rotr(17);
        assertTrue("0001".equals(ba0001.toString()));
        ba0001.rotl(24);
        assertTrue("1".equals(ba0001.getLsb().toString()));
        assertTrue("0".equals(ba0001.getMsb().toString()));
        assertTrue("0001".equals(ba0001.toString()));
    }

    // Test flip method
    @Test
    public void testBitArrayFlip() {
        ba0001.flip();
        assertTrue("1110".equals(ba0001.toString()));
        ba0001.flip();
        assertTrue("0001".equals(ba0001.toString()));
    }

    // Test overrided equals method
    @Test
    public void testBitArrayEquals() {
        assertFalse(zeroes.equals(ones));
        assertFalse(zeroes.equals(ba0000));
        assertTrue(zeroes.equals(zeroes));
        BitArray new_zeroes = new BitArray("0000000");
        assertTrue(zeroes.equals(new_zeroes));
        BitArray a = new BitArray("0000001");
        BitArray b = new BitArray("1000000");
        BitArray c = new BitArray("0000001");
        assertFalse(a.equals(b));
        b.rotl(1); // now b == "0000001"
        assertTrue(a.equals(b));
        assertTrue(a.equals(c));
        assertTrue(b.equals(c));
        a.flip(); // now a == "1111110"
        assertFalse(a.equals(c));
    }

    // Test XOR method
    @Test
    public void testBitArrayXor() {
        // 1111 XOR 0000 = 1111
        ba1111.xor(ba0000);
        assertTrue("1111".equals(ba1111.toString()));

        // 1111 XOR 1010 = 0101
        ba1111.xor(ba1010);
        assertEquals(ba1111, ba0101);

        // 0101 XOR 0101 = 0000
        ba1111.xor(ba0101);
        assertEquals(ba1111, ba0000);

        // ! 0000 = 1111
        ba1111.flip();
        assertTrue("1111".equals(ba1111.toString()));

        BitArray baShort = new BitArray("01");
        BitArray baLong = new BitArray("01001010");
        BitArray baEmptyCon = new BitArray();

        //// 1111 XOR 01 = 1011
        ba1111.xor(baShort);
        assertTrue("1011".equals(ba1111.toString()));

        // 1011 XOR 0100.... = 1111
        ba1111.xor(baLong);
        assertTrue("1111".equals(ba1111.toString()));

        // 1111 XOR 0000  = 1111
        ba1111.xor(baEmptyCon);
        assertTrue("1111".equals(ba1111.toString()));
    }

}
