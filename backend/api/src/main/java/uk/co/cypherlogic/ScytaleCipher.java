package uk.co.cypherlogic;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

/**
 * Scytale Cipher Class
 *
 * @author ESRS Group 2
 * @version 2022-03-24
 */
public class ScytaleCipher implements Cipher {

    /**
     * Scytale cipher encryption method
     *
     * @param plaintext A String of letters A-Z only being the plain text to
     * encrypt
     * @param key A String of letters A-Z only being the encryption key/password
     * @return A JSON String response message containing the cipher text
     */
    public static String encrypt(String plaintext, int key) {
        CryptoResponseError badParams = new CryptoResponseError("Invalid parameters passed");
        plaintext = CryptoUtils.formatInput(plaintext);
        int pad = key - (plaintext.length() % key);
        if (pad != key) {
            plaintext = plaintext + CryptoUtils.padding(pad, 'X');
        }

        if (CryptoUtils.isTextString(plaintext)) {
            CryptoResponse res = new CryptoResponseEncrypt(plaintext, Integer.toString(key), doEncrypt(plaintext, key));
            Jsonb jsonb = JsonbBuilder.create();
            return jsonb.toJson(res);
        }
        return badParams.toString();
    }

    private static String doEncrypt(String intext, int key) {
        String outtext = "";
        for (int j = 0; j < key; j++) {
            for (int i = 0; i < intext.length(); i = i + key) {
                outtext += intext.charAt(i + j);
            }
        }
        return outtext;
    }

    private static String doDecrypt(String intext, int key) {
        String outtext = "";
        int offset = intext.length() / key;
        for (int j = 0; j < offset; j++) {
            for (int i = 0; i < intext.length(); i = i + offset) {
                outtext += intext.charAt(i + j);
            }
        }
        return outtext;
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
    public static String decrypt(String ciphertext, int key) {
        CryptoResponseError badParams = new CryptoResponseError("Invalid parameters passed");
        ciphertext = CryptoUtils.formatInput(ciphertext);
        if (CryptoUtils.isTextString(ciphertext) && ciphertext.length() % key == 0) {
            CryptoResponse res = new CryptoResponseDecrypt(doDecrypt(ciphertext, key), Integer.toString(key), ciphertext);
            Jsonb jsonb = JsonbBuilder.create();
            return jsonb.toJson(res);
        }
        return badParams.toString();
    }

}
