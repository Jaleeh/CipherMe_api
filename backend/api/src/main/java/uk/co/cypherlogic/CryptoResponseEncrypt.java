package uk.co.cypherlogic;

/**
 * XML/JSON CryptoResponse for encryption
 *
 * @author ESRS Group 2
 * @version 2022-03-16
 * @see CryptoResponse
 */
public class CryptoResponseEncrypt extends CryptoResponse {

    public CryptoResponseEncrypt(String plaintext, String key, String ciphertext) {
        super("ENCRYPTED", plaintext, key, ciphertext);
    }

}
