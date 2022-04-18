package uk.co.cypherlogic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * JUnit Test Suite for Triple-DES Cipher. Results are confirmed with
 * cross-referencing output from the OpenSSL implementation. OpenSSL usage:
 * openssl enc -des-ede-ecb -K AABB09182736CCDDAABB09182736CCFF -nopad -in
 * <IN FILE> -out <OUT FILE> -p -nosalt
 *
 * @author ESRS Group 2
 * @version 2022-03-25
 *
 */
public class TripleDESTest {

    public TripleDESTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void TripleDESencryptDecryptTest() {
        String password = "AABB09182736CCDDAABB09182736CCFF";
        String plaintext = "123456ABCD132536";
        String enc;
        String dec;
        TripleDESCipher des3;
        System.out.println("===============\nTRIPLE DES TEST\n===============");

        des3 = new TripleDESCipher(password);
        enc = des3.encrypt(plaintext);
        assertEquals("6862962F82DB1A87", enc);
        dec = des3.decrypt(enc);
        assertEquals(plaintext, dec);
        System.out.println("TRIPLE DES OUTPUT: " + dec);
    }
}
