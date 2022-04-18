package uk.co.cypherlogic;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit Test Suite for DESBlockCipher Cipher. Results are cross referenced with
 * output of the OpenSSL implementation. OpenSSL usage: openssl enc -des-ecb -K
 * AABB09182736CCDD -nopad -in <IN FILE> -out <OUT FILE>
 *
 * @author ESRS Group 2
 * @version 2022-03-18
 */
public class DESCipherTest {

    Jsonb json = JsonbBuilder.create();

    public DESCipherTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void DesKeyInitTest() {
        String response;
        String key1 = "AABB09182736CCDD";
        String plaintext = "123456ABCD132536";
        String key2 = "0F1571C947D9E859";
        String plaintext2 = "02468ACEECA86420";
        try {
            DESBlockCipher des = new DESBlockCipher(key1);

            response = des.encrypt(plaintext);
            TestResponse responseObj = json.fromJson(response, TestResponse.class);
            assertEquals("C0B7A8D05F3A829C", responseObj.ciphertext);

            System.out.println("DES ENCRYPT RESPONSE: " + response);

            des = new DESBlockCipher(key2);
            response = des.encrypt(plaintext2);
            responseObj = json.fromJson(response, TestResponse.class);
            assertEquals("DA02CE3A89ECAC3B", responseObj.ciphertext);

            response = des.decrypt("DA02CE3A89ECAC3B");
            responseObj = json.fromJson(response, TestResponse.class);
            assertEquals("02468ACEECA86420", responseObj.plaintext);
        } catch (Exception ex) {
            //fail();
        }

        //fail();
    }
}
