package uk.co.cypherlogic;

import java.util.List;

/**
 * XML/JSON CryptoResponse for DES encryption
 *
 * @author ESRS Group 2
 * @version 2022-03-24
 * @see CryptoResponse
 */
public class CryptoResponseDecryptDES extends CryptoResponse {

    private List<DESRound> rounds;
    private String compressedKey;

    public CryptoResponseDecryptDES(String plaintext, String key, String ciphertext, String compressedKey, List<DESRound> rounds) {
        super("DECRYPTED", plaintext, key, ciphertext);
        this.compressedKey = compressedKey;
        this.rounds = rounds;
    }

    public List<DESRound> getRounds() {
        return rounds;
    }

    public String getCompressedKey() {
        return compressedKey;
    }

}
