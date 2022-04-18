/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.co.cypherlogic;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Triple Data Encryption Standard (DES) Cipher Class. Can use either two or
 * three keys. Performs DES encryption > decryption > encryption.
 *
 * @author ESRS Group 2
 * @version 2022-03-24
 */
public class TripleDESCipher extends BlockCipher {

    private String key;
    private String plaintext;
    private String ciphertext;
    private String round1, round2, round3;
    private ArrayList<String> keys;
    private DESBlockCipher stage1, stage2, stage3;

    public TripleDESCipher(String key) {
        // Take inputted key and chunk into two or three DES keys
        this.key = key;
        this.keys = CryptoUtils.chuckString(key, 16);
        try {
            // Init three stages of DES
            this.stage1 = new DESBlockCipher(this.keys.get(0));
            this.stage2 = new DESBlockCipher(this.keys.get(1));
            this.stage3 = new DESBlockCipher(this.keys.get(0));
        } catch (Exception ex) {
            Logger.getLogger(TripleDESCipher.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String encrypt(String plaintext) {
        stage1.encrypt(plaintext);
        stage2.decrypt(stage1.getCiphertextHex());
        stage3.encrypt(stage2.getPlaintextHex());
        return stage3.getCiphertextHex();

    }

    @Override
    public String decrypt(String ciphertext) {
        stage3.decrypt(ciphertext);
        stage2.encrypt(stage3.getPlaintextHex());
        stage1.decrypt(stage2.getCiphertextHex());
        return stage1.getPlaintextHex();

    }

}
