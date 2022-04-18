package uk.co.cypherlogic;

/**
 * XML/JSON CryptoResponse for encryption
 *
 * @author ESRS Group 2
 * @version 2022-03-16
 * @see CryptoResponse
 */
public class CryptoResponseDecrypt extends CryptoResponse {

    public CryptoResponseDecrypt(String plaintext, String key, String ciphertext) {
        super("DECRYPTED", plaintext, key, ciphertext);
    }
}
