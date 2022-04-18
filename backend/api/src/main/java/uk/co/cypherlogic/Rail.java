/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.co.cypherlogic;

/**
 *
 * @author Amine
 */
import java.util.*;

public class Rail {

    int depth;
    String result;
    String Encryption(String plainText, int depth) throws Exception {
        int r = depth, len = plainText.length();
        int c = len / depth;
        char mat[][] = new char[r][c];
        int k = 0;

        String cipherText = "";

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (k != len) {
                    mat[j][i] = plainText.charAt(k++);
                } else {
                    mat[j][i] = 'X';
                }
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                cipherText += mat[i][j];
            }
        }
        result = "ENCRYPTED";
         return "{\"result\":\"the encrypted message is\"}"+ cipherText;
    }

    String Decryption(String cipherText, int depth) throws Exception {
        int r = depth, len = cipherText.length();
        int c = len / depth;
        char mat[][] = new char[r][c];
        int k = 0;

        String plainText = "";

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                mat[i][j] = cipherText.charAt(k++);
            }
        }
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                plainText += mat[j][i];
            }
        }
        result = "DECRYPTED";
        return "{\"result\":\"the decrypted message is \"}"+ plainText;
    }

  /*  public static void main(String args[]) throws Exception {
        Rail rf = new Rail();
        Scanner scn = new Scanner(System.in);
        int depth;

        String plainText, cipherText, decryptedText;

        System.out.println("Enter plain text:");
        plainText = scn.nextLine();

        System.out.println("Enter depth for Encryption:");
        depth = scn.nextInt();

        cipherText = rf.Encryption(plainText, depth);
        System.out.println("Encrypted text is:\n" + cipherText);

        decryptedText = rf.Decryption(cipherText, depth);

        System.out.println("Decrypted text is:\n" + decryptedText);

    }*/
}
