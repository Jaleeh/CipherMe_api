package uk.co.cypherlogic;

/**
 * BlockCipher abstract class.
 *
 * @author ESRS Group 2
 * @version 2022-03-24
 */
public abstract class BlockCipher implements Cipher {

    public abstract String encrypt(String plaintext);

    public abstract String decrypt(String ciphertext);
}
