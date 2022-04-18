package uk.co.cypherlogic;

/**
 * XML/JSON CryptoResponse for Stream cipher decryption
 *
 * @author ESRS Group 2
 * @version 2022-03-17
 * @see CryptoResponseEncrypt
 */
public class CryptoResponseDecryptStream extends CryptoResponseDecrypt {

    private String taps;
    private String lfsrstream;

    public CryptoResponseDecryptStream(String plaintext, String iv, String taps, String lfsrstream, String ciphertext) {
        super(plaintext, iv, ciphertext);
        this.taps = taps;
        this.lfsrstream = lfsrstream;
    }

    public String getTaps() {
        return taps;
    }

    public String getLfsrstream() {
        return lfsrstream;
    }
}
