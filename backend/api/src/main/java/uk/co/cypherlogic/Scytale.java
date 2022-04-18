/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.co.cypherlogic;

/**
 *
 * @author Amine
 */
/**
 * The Scytale class implements the scytale transposition cipher.
 *
 * The scytale cipher is named after a tool used by ancient greeks. It was a
 * cylinder of a particular diameter wrapped in leather. The message was written
 * across the cylinder, and then the leather was unwrapped. For example the
 * message "examplescytale" on a would look like this: |e|x|a|m|p| |l|e|s|c|y|
 * |t|a|l|e|~| Resulting in a cipher text of eltxeaaslmcpy.
 */

class Scytale {
String result;
    char[][] rod;
    int width;
    int height;
    public static final char NULLCHAR = '\u0000';
    public static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public static final char[] UPPER_ALPHABET = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();

    // Creates a scytale object with the rod initalized from either the cipher or plain text
    // depending on encode flag.
    public Scytale(String message, int width, boolean persistCaps, boolean keepCharacters, boolean encode) {
        if (!persistCaps) {
            message = message.toLowerCase();
        }
        if (!keepCharacters) {
            message = removeNonalphabeticChars(message);
        }
        this.width = width;
        this.height = message.length() % width == 0 ? message.length() / width : message.length() / width + 1;
        if (encode) {
            buildRodEncode(message);
        } else {
            buildRodDecode(message);
        }
    }

    Scytale() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // initializes the rod from the plaintext
    public String buildRodEncode(String message) {
        rod = new char[height][width];
        int index = 0;
        char[] text = message.toCharArray();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rod[i][j] = index < text.length ? text[index] : NULLCHAR;
                index++;
            }
        }
this.result = "ENCRYPTED";
return "{\"result\":\"the decrypted message is\"}"+ rod.toString();
    }

    // initializes the rod from the ciphertext
    public String buildRodDecode(String message) {
        rod = new char[height][width];
        int index = 0;
        char[] text = message.toCharArray();

        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                rod[i][j] = index < message.length() ? text[index] : NULLCHAR;
                index++;
            }
        }
this.result = "DECRYPTED";
return "{\"result\":\"the decrypted message is\"}"+ rod.toString();
    }

    // gets the plaintext from the scytale
    public String getPlaintext() {
        String out = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                out += rod[i][j];
            }
        }
        return out;
    }

    // gets the ciphertext from the scytale
    public String getCiphertext() {
        String out = "";
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                out += rod[i][j];
            }
        }
        return out;
    }

    // returns a diagram of the scytale
    public String rodDiagram() {
        String out = "";
        for (int i = 0; i < height; i++) {
            out += "|";
            for (int j = 0; j < width; j++) {
                out += rod[i][j] != NULLCHAR ? rod[i][j] : '~';
                out += "|";
            }
            out += "\n";
        }
        return out;
    }

    public static String removeNonalphabeticChars(String in) {
        // TODO: maybe nonalphanumeric? maybe ignore spaces?
        return in.replaceAll("[^A-Za-z]", "");
    }

    public static char lowerChar(char in) {
        // returns the lowercase equivalent of the character, if any; otherwise, the character itself.
        return Character.toLowerCase(in);
    }

    // lazy wrapper method
    public static boolean isCapitalized(char in) {
        return Character.isUpperCase(in);
    }

    // lazy wrapper method
    public static String charToString(char in) {
        return String.valueOf(in);
    }

}
