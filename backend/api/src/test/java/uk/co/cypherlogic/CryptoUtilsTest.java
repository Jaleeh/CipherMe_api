package uk.co.cypherlogic;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ESRS Group 2
 * @version 2022-02-16
 */
public class CryptoUtilsTest {

    String bin;
    String bin_not_div_by_four;
    String bin_invalid;
    String hex;
    String hex_lc;
    String hex_invalid;
    byte b68 = 68;
    byte bm1 = -1;
    byte b0 = 0;
    byte bm127 = -127;
    byte bm111 = -111;
    byte b85 = 85;

    public CryptoUtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        bin = "0000000100100011010001010110011110001001101010111100110111101111";
        bin_not_div_by_four = "0000000100100011010001010110011110001001101010111100110111101111000";
        bin_invalid = "000000010252356310100011010001010110011110001001101010111100110111101111";
        hex = "0123456789ABCDEF";
        hex_lc = "0123456789abcdef";
        hex_invalid = "0123456789abcdefg";
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of charToIdx method, of class CryptoUtils.
     */
    @Test
    public void testCharToIdx() {
        System.out.println("charToIdx");
        assertEquals(0, CryptoUtils.charToIdx('A'));
        assertEquals(25, CryptoUtils.charToIdx('Z'));
        assertEquals(5, CryptoUtils.charToIdx('f'));
        assertEquals(0, CryptoUtils.charToIdx('2'));

    }

    /**
     * Test of binaryStringToHex method, of class CryptoUtils.
     */
    @Test
    public void testBinaryStringToHex() {
        System.out.println("binaryStringToHex");
        String result = CryptoUtils.binaryStringToHex(bin);
        assertEquals(hex, result);

        // Handle a binary string which is not divisible by four
        result = CryptoUtils.binaryStringToHex(bin_not_div_by_four);
        assertEquals(hex, result);

        // Handle a binary string containing invalid characters, i.e. not '0' or '1'
        result = CryptoUtils.binaryStringToHex(bin_invalid);
        assertEquals("01A3456789ABCDEF", result);
    }

    /**
     * Test of hexStringToBinary method, of class CryptoUtils.
     */
    @Test
    public void testHexStringToBinary() {
        System.out.println("hexStringToBinary");
        String result = CryptoUtils.hexStringToBinary(hex);
        assertEquals(bin, result);
        result = CryptoUtils.hexStringToBinary(hex_lc);
        assertEquals(bin, result);
        result = CryptoUtils.hexStringToBinary(hex_invalid);
        assertEquals(bin, result);
    }

    /**
     * Test of substituteChar method, of class CryptoUtils.
     */
    @Test
    public void testSubstituteChar() {
        System.out.println("transposeChar");
        char result = CryptoUtils.substituteChar('A', 10);
        assertEquals('K', result);
        result = CryptoUtils.substituteChar('A', 25);
        assertEquals('Z', result);
        result = CryptoUtils.substituteChar('A', 26);
        assertEquals('A', result);
        result = CryptoUtils.substituteChar('A', 53);
        assertEquals('B', result);
        result = CryptoUtils.substituteChar('A', -1);
        assertEquals('Z', result);
        result = CryptoUtils.substituteChar('A', -25);
        assertEquals('B', result);

        result = CryptoUtils.substituteChar('Z', 53);
        assertEquals('A', result);
        result = CryptoUtils.substituteChar('Z', -1);
        assertEquals('Y', result);
        result = CryptoUtils.substituteChar('Z', -25);
        assertEquals('A', result);

        // Lower case valid chars
        result = CryptoUtils.substituteChar('a', 25);
        assertEquals('Z', result);
        result = CryptoUtils.substituteChar('a', 26);
        assertEquals('A', result);

        // Invalid chars
        result = CryptoUtils.substituteChar('ä', 26);
        assertEquals('*', result);
        result = CryptoUtils.substituteChar('6', 26);
        assertEquals('*', result);

    }

    /**
     * Test of isTextString method, of class CryptoUtils.
     */
    public void testIsTextString() {
        System.out.println("isTextString");
        assertTrue(CryptoUtils.isTextString("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        assertTrue(CryptoUtils.isTextString("A"));
        assertFalse(CryptoUtils.isTextString(""));
        assertFalse(CryptoUtils.isTextString("101"));
        assertFalse(CryptoUtils.isTextString("A101FF"));
        assertFalse(CryptoUtils.isTextString("abcdef"));
        assertFalse(CryptoUtils.isTextString("TEXT STRING"));
        assertFalse(CryptoUtils.isTextString("!"));
        assertFalse(CryptoUtils.isTextString("ß"));
    }

    /**
     * Test of isHexString method, of class CryptoUtils.
     */
    @Test
    public void testIsHexString() {
        System.out.println("isHexString");
        assertTrue(CryptoUtils.isHexString("0"));
        assertTrue(CryptoUtils.isHexString("F"));
        assertTrue(CryptoUtils.isHexString("0123456789ABCDEF"));
        assertTrue(CryptoUtils.isHexString("0000101010101010101111"));
        assertFalse(CryptoUtils.isHexString(""));
        assertFalse(CryptoUtils.isHexString("1.2"));
        assertFalse(CryptoUtils.isHexString("0123456789ABCDEFG"));
        assertFalse(CryptoUtils.isHexString("HEX STRING"));
        assertFalse(CryptoUtils.isHexString("0123456789ABCDEF!"));
    }

    /**
     * Test of isHexString method, of class CryptoUtils.
     */
    @Test
    public void testIsBinString() {
        System.out.println("isBinaryString");
        assertTrue(CryptoUtils.isBinaryString("0"));
        assertTrue(CryptoUtils.isBinaryString("1"));
        assertTrue(CryptoUtils.isBinaryString("0000101010101010101111"));
        assertFalse(CryptoUtils.isBinaryString(""));
        assertFalse(CryptoUtils.isBinaryString("0123456789ABCDEFG"));
        assertFalse(CryptoUtils.isBinaryString("BINARY STRING"));
        assertFalse(CryptoUtils.isBinaryString("0101010!"));
    }

    /**
     * Test of formatInput method, of class CryptoUtils.
     */
    @Test
    public void testFormatInput() {
        String test = CryptoUtils.formatInput("abcdef");
        assertTrue("ABCDEF".equals(test));

        test = CryptoUtils.formatInput(" abcdef ");
        assertTrue("ABCDEF".equals(test));

        test = CryptoUtils.formatInput(" abcdef\r\n");
        assertTrue("ABCDEF".equals(test));
    }

    /**
     * Test of byteToBinaryString static method, of class CryptoUtils
     */
    @Test
    public void testByteToBinaryString() {
        assertEquals("01000100", CryptoUtils.byteToBinaryString(b68));
        assertEquals("11111111", CryptoUtils.byteToBinaryString(bm1));
        assertEquals("00000000", CryptoUtils.byteToBinaryString(b0));
        assertEquals("10000001", CryptoUtils.byteToBinaryString(bm127));
        assertEquals("01010101", CryptoUtils.byteToBinaryString(b85));
        assertEquals("10010001", CryptoUtils.byteToBinaryString(bm111));
    }

    /**
     * Test of binaryStringToByte static method, of class CryptoUtils
     */
    @Test
    public void testBinaryStringToByte() {
        assertEquals(b68, CryptoUtils.binaryStringToByte("01000100"));
        assertEquals(bm1, CryptoUtils.binaryStringToByte("11111111"));
        assertEquals(b0, CryptoUtils.binaryStringToByte("00000000"));
        assertEquals(bm127, CryptoUtils.binaryStringToByte("10000001"));
        assertEquals(b85, CryptoUtils.binaryStringToByte("01010101"));
        assertEquals(bm111, CryptoUtils.binaryStringToByte("10010001"));
    }

    /**
     * Test of Padding static method, of class CryptoUtils
     */
    @Test
    public void testPadding() {
        assertEquals("XXXXX", CryptoUtils.padding(5, 'X'));
        assertEquals("       ", CryptoUtils.padding(7, ' '));
        assertEquals("", CryptoUtils.padding(0, 'N'));
        assertEquals("", CryptoUtils.padding(-10, 'N'));
        assertEquals("1234511000", CryptoUtils.addPadding("123451", 5));
        assertEquals("1234512310", CryptoUtils.addPadding("12345123", 5));
        assertEquals("1234512341", CryptoUtils.addPadding("123451234", 5));
        assertEquals("12310", CryptoUtils.addPadding("123", 5));
        assertEquals("10000", CryptoUtils.addPadding("", 5));
        assertEquals("1", CryptoUtils.addPadding("", 1));
        assertEquals("123451231", CryptoUtils.addPadding("12345123", 1));
        assertEquals("1234512341", CryptoUtils.addPadding("123451234", -5));
    }

    /**
     * Test of chunkString static method, of class CryptoUtils
     */
    @Test
    public void testStringChunking() {
        String source = "0123456789abcdef";
        ArrayList<String> result = CryptoUtils.chuckString(source, 5);

        assertEquals("01234", result.get(0));
        assertEquals("56789", result.get(1));
        assertEquals("ABCDE", result.get(2));
        assertEquals("F1000", result.get(3));
        source = "AAAAABBBBBCCCCCDDDDD";
        result = CryptoUtils.chuckString(source, 5);
        assertEquals("10000", result.get(4));
    }
}
