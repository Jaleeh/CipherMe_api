package uk.co.cypherlogic;

/**
 * XML/JSON CryptoResponse for Stream cipher encryption
 *
 * @author ESRS Group 2
 * @version 2022-03-17
 * @see CryptoResponseEncrypt
 */
public class CryptoResponseEncryptStream extends CryptoResponseEncrypt {

    private String taps;
    private String lfsrstream;

    public CryptoResponseEncryptStream(String plaintext, String iv, String taps, String lfsrstream, String ciphertext) {
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
