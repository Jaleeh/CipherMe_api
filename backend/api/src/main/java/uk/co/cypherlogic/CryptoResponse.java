package uk.co.cypherlogic;

/**
 * XML/JSON Response
 *
 * @author ESRS Group 2
 * @version 2022-02-16
 */
public class CryptoResponse {

    private String plaintext;
    private String key;
    private String ciphertext;
    private String result;

    public CryptoResponse() {
    }

    public CryptoResponse(String result, String plaintext, String key, String ciphertext) {
        super();
        this.result = result;
        this.plaintext = plaintext;
        this.key = key;
        this.ciphertext = ciphertext;
    }

    @Override
    public String toString() {
        return "{\"result\":\"" + result + "\", \"plaintext\":\"" + plaintext + "\", \"key\":\"" + key + "\", \"ciphertext\":\"" + ciphertext + "\"}";
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

}
