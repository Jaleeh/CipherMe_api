package uk.co.cypherlogic;

import java.util.Map;
import static java.util.Map.entry;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Jersey Test Framework - Vigenere Cipher Integration Test
 *
 * @author ESRS Group 2
 * @version 2022-03-11
 */
public class VigenereCipherIntegrationTest extends JerseyTest {

    private Jsonb jsonb = JsonbBuilder.create();
    private String encryptPath = "vigenere/encrypt";
    private String decryptPath = "vigenere/decrypt";
    private CryptoResponseError badParams = new CryptoResponseError("Invalid parameters passed");

    @Override
    protected Application configure() {
        return new ResourceConfig(EndpointVigenere.class);
    }

    @Test
    public void vigenereResponseTest() {
        Response response = target(encryptPath).path("/ABCDEFGHIJKLMNOPQRSTUVWXYZ/PASS").request().get();
        assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        response = target(encryptPath).request().get();
        assertEquals("Http Response should be 404: ", Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        response = target(encryptPath).path("//").request().get();
        assertEquals("Http Response should be 404: ", Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void vigenereEncryptTest() {
        // Vigenere Cipher with period of 4
        String json = target(encryptPath).path("/ABCDEFGHIJKLMNOPQRSTUVWXYZ/PASS").request().get(String.class);
        assertEquals(json, "{\"ciphertext\":\"PBUVTFYZXJCDBNGHFRKLJVOPNZ\",\"key\":\"PASS\",\"plaintext\":\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"result\":\"ENCRYPTED\"}");

        // Caesar Cipher or Vigenere Cipher of period 1
        json = target(encryptPath).path("/ABCDEFGHIJKLMNOPQRSTUVWXYZ/P").request().get(String.class);
        assertEquals(json, "{\"ciphertext\":\"PQRSTUVWXYZABCDEFGHIJKLMNO\",\"key\":\"P\",\"plaintext\":\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"result\":\"ENCRYPTED\"}");

        // One Time Pad or Vigenere Cipher of period length of plaintext
        json = target(encryptPath).path("/ABCDEFGHIJKLMNOPQRSTUVWXYZ/ZYXWVUTSRQPONMLKJIHGFEDCBA").request().get(String.class);
        assertEquals(json, "{\"ciphertext\":\"ZZZZZZZZZZZZZZZZZZZZZZZZZZ\",\"key\":\"ZYXWVUTSRQPONMLKJIHGFEDCBA\",\"plaintext\":\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"result\":\"ENCRYPTED\"}");

        // Invalid Parameters provided
        json = target(encryptPath).path("/ABC19/ZYX").request().get(String.class);

        assertEquals(json, badParams.toString());
    }

    @Test
    public void vigenereDecryptTest() {
        // Vigenere Cipher with period of 4
        String json = target(decryptPath).path("/PBUVTFYZXJCDBNGHFRKLJVOPNZ/PASS").request().get(String.class);
        assertEquals(json, "{\"ciphertext\":\"PBUVTFYZXJCDBNGHFRKLJVOPNZ\",\"key\":\"PASS\",\"plaintext\":\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"result\":\"DECRYPTED\"}");

        // Caesar Cipher or Vigenere Cipher of period 1
        json = target(decryptPath).path("/PQRSTUVWXYZABCDEFGHIJKLMNO/P").request().get(String.class);
        assertEquals(json, "{\"ciphertext\":\"PQRSTUVWXYZABCDEFGHIJKLMNO\",\"key\":\"P\",\"plaintext\":\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"result\":\"DECRYPTED\"}");

        // One Time Pad or Vigenere Cipher of period length of plaintext
        json = target(decryptPath).path("/ZZZZZZZZZZZZZZZZZZZZZZZZZZ/ZYXWVUTSRQPONMLKJIHGFEDCBA").request().get(String.class);
        assertEquals(json, "{\"ciphertext\":\"ZZZZZZZZZZZZZZZZZZZZZZZZZZ\",\"key\":\"ZYXWVUTSRQPONMLKJIHGFEDCBA\",\"plaintext\":\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\",\"result\":\"DECRYPTED\"}");

        // Invalid Parameters provided
        json = target(decryptPath).path("/ABCDEFG/11ZYX").request().get(String.class);
        assertEquals(json, badParams.toString());

    }

    @Test
    public void vigenereEncryptDecryptTest() {
        Map<String, String> testsuite = Map.ofEntries(
                entry("SDHJKG", "ABCDEFGHIJKLMNOPQRTSTUVWXYZ"),
                entry("S", "ABCDEFGHIJKLMNOPQRTSTUVWXYZ"),
                entry("FJGOOVDLSKGLFGSDJ", "ABCDEFGHIJKLMNOPQRTSTUVWXYZ"),
                entry("SDHDFGHJKG", "SDAHGAEWRHERAHAERHERAN"),
                entry("FHG", "DFGFDAHERTAHTAERHYJKYTKJTYDKTDYJKTYWEKSDTYKSTYDKSKFGHJKSJKYJKSTYJKYT"),
                entry("TYRJSSFTGJFGJ", "SHJFTSJYYSTKTYKDSYKTSDKTYKKNMNDSRTYUJTYSRHASHKJDTK"),
                entry("AAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
                entry("HHHH", "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ"),
                entry("F", "G"),
                entry("JKJKJKJ", "ABABABABABABABABABABABABABABABBABABBABABBA")
        );
        String plaintext;
        String key;
        String json;
        TestResponse encResponse;
        TestResponse decResponse;

        for (Map.Entry<String, String> entry : testsuite.entrySet()) {
            plaintext = entry.getValue();
            key = entry.getKey();
            json = target(encryptPath).path("/" + plaintext + "/" + key).request().get(String.class);
            encResponse = jsonb.fromJson(json, TestResponse.class);
            System.out.println(encResponse.result + ": " + encResponse.plaintext + " > " + encResponse.ciphertext);
            json = target(decryptPath).path("/" + encResponse.ciphertext + "/" + key).request().get(String.class);
            decResponse = jsonb.fromJson(json, TestResponse.class);
            System.out.println(decResponse.result + ": " + decResponse.ciphertext + " > " + decResponse.plaintext);
            assertEquals(plaintext, decResponse.plaintext);
        }
    }
}
