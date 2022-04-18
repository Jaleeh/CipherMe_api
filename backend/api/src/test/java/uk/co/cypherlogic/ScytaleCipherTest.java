package uk.co.cypherlogic;

import java.util.Map;
import static java.util.Map.entry;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit Test Suite for Scytale Cipher
 *
 * @author ESRS Group 2
 * @version 2022-03-24
 */
public class ScytaleCipherTest {

    Jsonb json = JsonbBuilder.create();

    public ScytaleCipherTest() {
    }

    /**
     * Test of encrypt method, of class ScytaleCipher.
     */
    @Test
    public void testEncrypt() {
        TestResponse response = json.fromJson(ScytaleCipher.encrypt("ABCDEFGHIJ", 5), TestResponse.class);
        assertEquals("AFBGCHDIEJ", response.ciphertext);
    }

    /**
     * Test of decrypt method, of class ScytaleCipher.
     */
    @Test
    public void testDecrypt() {
        TestResponse response = json.fromJson(ScytaleCipher.decrypt("AFBGCHDIEJ", 5), TestResponse.class);
        assertEquals("ABCDEFGHIJ", response.plaintext);
    }

    /**
     * Test of encrypt and then decrypt methods, of class ScytaleCipher.
     */
    @Test
    public void testEncryptDecrypt() {
        Map<Integer, String> testsuite = Map.ofEntries(
                entry(1, "ABCDEFGHIJKLMNOPQRTSTUVWXYZ"),
                entry(2, "ABCDEFGHIJKLMNOPQRTSTUVWXYZ"),
                entry(3, "ABCDEFGHIJKLMNOPQRTSTUVWXYZ"),
                entry(4, "SDAHGAEWRHEHTRERJTSSFGJNNMSFGSFGNMRAHAERHERAN"),
                entry(5, "DFGFDAHERTAHTAERHGHSFNSFGNMFSGHMNSFGHMFGHYJKYTKJTYDKTDYJKTYWEKSDTYKSTYDKSKFGHJKSJKYJKSTYJKYT"),
                entry(6, "SHJFTSJYYSTKTYKSNMFMSFGHMXFGHSMDHMDHMSDHGMHSDDGHMTRYHAIKWQIMOLMODMSMKNMOEOPPMVNNCMWMFPMFWEPMWEOPGMPOMEKLIOKYUTDSYKTSDKTYKKNMNDSRTYUJTYSRHASHKJDTK"),
                entry(7, "ANANANNNNANANANANNNNNAAAAANANANNANAN"),
                entry(8, "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ"),
                entry(9, "DFGFDAHERTAHTAERHGHSFNSFGNMFSGHMNSFGHMFGHYJKYTKJTYDKTDYJKTYWEKSDTYKSTYDKSKFGHJKSJKYJKSTYJKYT"),
                entry(10, "SHJFTSJYYSTKTYKSNMFMSFGHMXFGHSMDHMDHMSDHGMHSDDGHMTRYHAIKWQIMOLMODMSMKNMOEOPPMVNNCMWMFPMFWEPMWEOPGMPOMEKLIOKYUTDSYKTSDKTYKKNMNDSRTYUJTYSRHASHKJDTK")
        );
        String plaintext;
        int key;
        TestResponse encResponse;
        TestResponse decResponse;
        for (Map.Entry<Integer, String> entry : testsuite.entrySet()) {
            plaintext = entry.getValue();
            key = entry.getKey();
            encResponse = json.fromJson(ScytaleCipher.encrypt(plaintext, key), TestResponse.class);
            decResponse = json.fromJson(ScytaleCipher.decrypt(encResponse.ciphertext, key), TestResponse.class);
            assertEquals(encResponse.plaintext, decResponse.plaintext);
        }

    }
}
