package uk.co.cypherlogic;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

/**
 * Vigenère Cipher Class
 *
 * @author ESRS Group 2
 * @version 2022-02-16
 */
public class VigenereCipher implements Cipher {

    /**
     * Vigenere cipher encryption method
     *
     * @param plaintext A String of letters A-Z only being the plain text to
     * encrypt
     * @param key A String of letters A-Z only being the encryption key/password
     * @return A JSON String response message containing the cipher text
     */
    public static String encrypt(String plaintext, String key) {
        CryptoResponseError badParams = new CryptoResponseError("Invalid parameters passed");
        plaintext = CryptoUtils.formatInput(plaintext);
        key = CryptoUtils.formatInput(key);
        if (CryptoUtils.isTextString(plaintext) && CryptoUtils.isTextString(key)) {
            CryptoResponse res = new CryptoResponseEncrypt(plaintext, key, doEncrypt(plaintext, key));
            Jsonb jsonb = JsonbBuilder.create();
            return jsonb.toJson(res);
        }
        return badParams.toString();
    }

    private static String doEncrypt(String plaintext, String key) {
        String ciphertext = "";
        int k = 0;
        for (int i = 0; i < plaintext.length(); i++) {
            ciphertext += CryptoUtils.substituteChar(plaintext.charAt(i), CryptoUtils.charToIdx(key.charAt(k)));
            k = (k + 1) % key.length();
        }
        return ciphertext;
    }

    /**
     * Vigenere cipher decryption method
     *
     * @param ciphertext A String of letters A-Z only being the cipher text to
     * decrypt
     * @param key A String of letters A-Z only being the decryption key/password
     * @return A JSON String response message containing the deciphered plain
     * text.
     */
    public static String decrypt(String ciphertext, String key) {
        CryptoResponseError badParams = new CryptoResponseError("Invalid parameters passed");
        ciphertext = CryptoUtils.formatInput(ciphertext);
        key = CryptoUtils.formatInput(key);
        if (CryptoUtils.isTextString(ciphertext) && CryptoUtils.isTextString(key)) {
            CryptoResponse res = new CryptoResponseDecrypt(doDecrypt(ciphertext, key), key, ciphertext);
            Jsonb jsonb = JsonbBuilder.create();
            return jsonb.toJson(res);
        }
        return badParams.toString();
    }

    private static String doDecrypt(String ciphertext, String key) {
        String plaintext = "";
        int k = 0;
        for (int i = 0; i < ciphertext.length(); i++) {
            plaintext += CryptoUtils.substituteChar(ciphertext.charAt(i), -(CryptoUtils.charToIdx(key.charAt(k))));
            k = (k + 1) % key.length();
        }
        return plaintext;
    }
}
