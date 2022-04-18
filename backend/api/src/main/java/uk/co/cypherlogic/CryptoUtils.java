package uk.co.cypherlogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import static java.util.Map.entry;

/**
 * CryptoUtils Static Class of helper utilities
 *
 * @author ESRS Group 2
 * @version 2022-02-14
 */
public class CryptoUtils {

    private static final Map<Character, Integer> charToIdx = Map.ofEntries(
            entry('A', 0),
            entry('B', 1),
            entry('C', 2),
            entry('D', 3),
            entry('E', 4),
            entry('F', 5),
            entry('G', 6),
            entry('H', 7),
            entry('I', 8),
            entry('J', 9),
            entry('K', 10),
            entry('L', 11),
            entry('M', 12),
            entry('N', 13),
            entry('O', 14),
            entry('P', 15),
            entry('Q', 16),
            entry('R', 17),
            entry('S', 18),
            entry('T', 19),
            entry('U', 20),
            entry('V', 21),
            entry('W', 22),
            entry('X', 23),
            entry('Y', 24),
            entry('Z', 25)
    );

    private static final ArrayList<Character> idxToChar = new ArrayList<>(
            Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));

    private static final Map<String, Character> binToHex = Map.ofEntries(
            entry("0000", '0'),
            entry("0001", '1'),
            entry("0010", '2'),
            entry("0011", '3'),
            entry("0100", '4'),
            entry("0101", '5'),
            entry("0110", '6'),
            entry("0111", '7'),
            entry("1000", '8'),
            entry("1001", '9'),
            entry("1010", 'A'),
            entry("1011", 'B'),
            entry("1100", 'C'),
            entry("1101", 'D'),
            entry("1110", 'E'),
            entry("1111", 'F')
    );

    private static final Map<Character, String> hexToBin = Map.ofEntries(
            entry('0', "0000"),
            entry('1', "0001"),
            entry('2', "0010"),
            entry('3', "0011"),
            entry('4', "0100"),
            entry('5', "0101"),
            entry('6', "0110"),
            entry('7', "0111"),
            entry('8', "1000"),
            entry('9', "1001"),
            entry('A', "1010"),
            entry('B', "1011"),
            entry('C', "1100"),
            entry('D', "1101"),
            entry('E', "1110"),
            entry('F', "1111")
    );

    /**
     * Convert a char to an index value from 0 to 25
     */
    public static int charToIdx(char c) {
        c = Character.toUpperCase(c);
        int idx = 0;
        if (charToIdx.containsKey(c)) {
            return charToIdx.get(c);
        }
        return idx;
    }

    /**
     * Convert a binary string to a hexadecimal string
     *
     * @param bin A String of binary characters, e.g. "01011111"
     * @return A String of hexadecimal characters, e.g. "5F"
     */
    public static String binaryStringToHex(String bin) {
        String hex = "";
        for (int i = 0; i < bin.length() - 3; i += 4) {
            if (binToHex.containsKey(bin.substring(i, i + 4))) {
                hex += binToHex.get(bin.substring(i, i + 4));
            }
        }
        return hex;
    }

    /**
     * Convert a byte value into a binary String
     *
     * @param b A byte to convert to binary String, e.g 68
     * @return A String in binary, e.g. "01000100"
     */
    public static String byteToBinaryString(byte b) {
        String bin = "";
        for (int i = 7; i >= 0; i--) {
            bin += (((b >> i) & 1) == 1) ? '1' : '0';
        }
        return bin;
    }

    /**
     * Convert a binary string value into a byte
     *
     * @param bin A String of binary digits, e.g. "10101010"
     * @return A byte value
     */
    public static byte binaryStringToByte(String bin) {
        // Cannot parse as a Byte as 11111111 is treated as unsigned and therefore is out of range
        return (byte) Integer.parseInt(bin, 2);
    }

    /**
     * Convert hex string to a binary string
     *
     * @param hex A String consisting of hex characters, e.g. "5F5F"
     * @return A String of binary characters, e.g. "0101111101011111"
     */
    public static String hexStringToBinary(String hex) {
        hex = hex.toUpperCase();
        String bin = "";
        for (int i = 0; i < hex.length(); i++) {
            if (hexToBin.containsKey(hex.charAt(i))) {
                bin += hexToBin.get(hex.charAt(i));
            }
        }
        return bin;
    }

    /**
     * Transpose a character by a given offset
     *
     * @param c A char to substitute
     * @param offset An int offset value
     * @return A char offset from c
     */
    public static char substituteChar(char c, int offset) {
        c = Character.toUpperCase(c);
        if (charToIdx.containsKey(c)) {
            int new_index = (charToIdx.get(c) + offset) % charToIdx.size();
            new_index = (new_index + charToIdx.size()) % charToIdx.size();
            return idxToChar.get(new_index);
        }
        return '*';
    }

    /**
     * Is the String compromised of the characters 'A' to 'Z' only?
     *
     * @param txt A String of text
     * @return true is only 'A' to 'Z' characters in String, false otherwise
     */
    public static boolean isTextString(String str) {
        if (str.length() < 1) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!charToIdx.containsKey(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Is the String in hex format?
     *
     * @param hex A String to test if in hex format
     * @return True if a hex string, false otherwise
     */
    public static boolean isHexString(String hex) {
        return isDigits(hex, 16);
    }

    /**
     * Is the String in binary format?
     *
     * @param bin A String to test if in binary format, i.e. consisting of only
     * '0' or '1' characters
     * @return True if a binary string, false otherwise
     */
    public static boolean isBinaryString(String bin) {
        return isDigits(bin, 2);
    }

    /*
    * Utility function to check if a character is a digit in given base.
    * @param str A String of digits
    * @param rad An int of the number base to confirm
    * @return true if character is a digit in the given base
     */
    private static boolean isDigits(String str, int rad) {
        if (str.length() < 1) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (Character.digit(str.charAt(i), rad) == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Format a String to upper case and trim whitespace
     *
     * @param str The String to be formatted
     * @return An uppercase, trimmed String
     */
    public static String formatInput(String str) {
        return str.toUpperCase().trim();
    }

    /**
     * Generate padding of given char repeated n times
     *
     * @param n An int of how many padding chars to generate
     * @param c A char to use as the padding character
     * @return A String of char c, repeated n times
     */
    public static String padding(int n, char c) {
        String padding = "";
        for (int i = 0; i < n; i++) {
            padding += c;
        }
        return padding;
    }

    /**
     * Add padding to a String. Padding takes the form of "100..."
     *
     * @param source A String which requires padding
     * @param mod An int modulus value to compute the number of characters of
     * padding required
     * @return A padded String
     */
    public static String addPadding(String source, int mod) {
        int num_zeroes = mod - (source.length() % mod) - 1;
        return source + "1" + padding(num_zeroes, '0');
    }

    /**
     * Chunk a string into given block sizes. Padding to added to the end. Only
     * hexadecimal characters are processed.
     *
     * @param source A string to be chunked
     * @param block_size An int representing the length of each chunked block
     * @return An ArrayList of type String containing chunked blocks of source
     */
    public static ArrayList<String> chuckString(String source, int block_size) {
        ArrayList<String> blocks = new ArrayList<>();
        String chunk = "";
        for (int i = 0; i < source.length(); i++) {
            char c = Character.toUpperCase(source.charAt(i));

            if (hexToBin.containsKey(c)) {
                chunk += c;
            }
            if (chunk.length() == block_size) {
                blocks.add(chunk);
                chunk = "";
            }
        }

        blocks.add(addPadding(chunk, block_size));
        return blocks;
    }
}
