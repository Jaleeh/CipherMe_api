package uk.co.cypherlogic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit Test Suite for Vigenère Cipher
 *
 * @author ESRS Group 2
 * @version 2022-02-16
 */
public class VigenereCipherTest {

    public VigenereCipherTest() {
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
     * Test of encrypt method, of class VigenereCipher.
     */
    @Test
    public void testEncrypt() {
        System.out.println("encrypt");
        String result = VigenereCipher.encrypt("AAAAAAAA", "ZAP");
        assertEquals("{\"ciphertext\":\"ZAPZAPZA\",\"key\":\"ZAP\",\"plaintext\":\"AAAAAAAA\",\"result\":\"ENCRYPTED\"}", result);

        // Test Caesar cipher
        result = VigenereCipher.encrypt("CODE", "C");
        assertEquals("{\"ciphertext\":\"EQFG\",\"key\":\"C\",\"plaintext\":\"CODE\",\"result\":\"ENCRYPTED\"}", result);

        // Test One Time Pad
        result = VigenereCipher.encrypt("CODE", "EDOC");
        assertEquals("{\"ciphertext\":\"GRRG\",\"key\":\"EDOC\",\"plaintext\":\"CODE\",\"result\":\"ENCRYPTED\"}", result);

    }

    /**
     * Test of decrypt method, of class VigenereCipher.
     */
    @Test
    public void testDecrypt() {
        System.out.println("decrypt");
        String result = VigenereCipher.decrypt("ZAPZAPZA", "ZAP");
        assertEquals("{\"ciphertext\":\"ZAPZAPZA\",\"key\":\"ZAP\",\"plaintext\":\"AAAAAAAA\",\"result\":\"DECRYPTED\"}", result);

        // Test Caesar cipher decrypt
        result = VigenereCipher.decrypt("EQFG", "C");
        assertEquals("{\"ciphertext\":\"EQFG\",\"key\":\"C\",\"plaintext\":\"CODE\",\"result\":\"DECRYPTED\"}", result);

        // Test One Time Pad decrypt
        result = VigenereCipher.decrypt("GRRG", "EDOC");
        assertEquals("{\"ciphertext\":\"GRRG\",\"key\":\"EDOC\",\"plaintext\":\"CODE\",\"result\":\"DECRYPTED\"}", result);

    }

}
