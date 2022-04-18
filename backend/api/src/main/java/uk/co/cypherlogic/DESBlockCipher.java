package uk.co.cypherlogic;

import java.util.ArrayList;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

/**
 * Data_Encryption Standard (DES) Cipher Class
 *
 * @author ESRS Group 2
 * @version 2022-03-16
 */
public class DESBlockCipher {

    private String plaintextHex;
    private String ciphertextHex;
    private Jsonb jsonb;
    private String hexKey;
    private String binKey;
    private String compressedKey;
    private BitArray leftKey;
    private BitArray rightKey;
    //private BitArray[] roundKeys = new BitArray[16];
    private ArrayList<DESRound> rounds;
    // KEY PERMUTATIONS
    // Key permutation 1 - Initial key compression from 64 bits to 56 bits
    private static final int[] KEY_PERM1 = {56, 48, 40, 32, 24, 16, 8, 0, 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 60, 52, 44, 36, 28, 20, 12, 4, 27, 19, 11, 3};
    // Key permutation 2 - Key compression to 48 bits for each round
    private static final int[] KEY_PERM2 = {13, 16, 10, 23, 0, 4, 2, 27, 14, 5, 20, 9, 22, 18, 11, 3, 25, 7, 15, 6, 26, 19, 12, 1, 40, 51, 30, 36, 46, 54, 29, 39, 50, 44, 32, 47, 43, 48, 38, 55, 33, 52, 45, 41, 49, 35, 28, 31};
    // How many bits to shift key in each round
    private static final int[] KEYSHIFT = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    // DATA PERMUTATIONS
    // Initial permutation of plain text
    private static final int[] INIT_PERM = {57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7, 56, 48, 40, 32, 24, 16, 8, 0, 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6};
    // Inverse of the initial permutation for decryption of ciphertext
    private static final int[] INIT_PERM_INV = {39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25, 32, 0, 40, 8, 48, 16, 56, 24};

    /**
     * DES Constructor
     *
     * @param key A String representing a 64 bit key in hex format, e.g.
     * "AABB09182736CCDD"
     * @throws Exception On key error
     */
    public DESBlockCipher(String key) throws Exception {
        if (!CryptoUtils.isHexString(key) || key.length() != 16) {
            throw new Exception("Invalid key. A key must be a 64 bit hex string!");
        }
        this.jsonb = JsonbBuilder.create();
        this.hexKey = key;
        this.binKey = CryptoUtils.hexStringToBinary(key);
        this.compressedKey = "";
        this.rounds = new ArrayList<>();
        this.generateRoundKeys();
    }

    /**
     * Generate the 16 x 48 bit round keys when class in constructed: 1. Apply
     * an initial permutation to compress the 64 bit key to 56 bits using the
     * KEY_PERM1 array. 2. Split 56 bit key into a left and right block of 28
     * bits each. 3. Generate 16 round keys. 3a. Left rotate bits in left and
     * right blocks, number of bits defined by KEYSHIFT array. 3b. Join left and
     * right blocks and compress to 48 bits using KEY_PERM2 array.
     */
    private void generateRoundKeys() {
        // Initial permutation to reduce 64 bit key to 56 bits
        for (int i = 0; i < 56; i++) {
            this.compressedKey += this.binKey.charAt(KEY_PERM1[i]);
        }

        // Split key into left and right blocks of 28 bits each
        this.leftKey = new BitArray(this.compressedKey.substring(0, 28));
        this.rightKey = new BitArray(this.compressedKey.substring(28));

        System.out.println("Hex Key: " + this.hexKey);
        System.out.println("Bin Key: " + this.binKey);
        System.out.println("56b Key: " + this.compressedKey);
        System.out.println("56b Key: " + CryptoUtils.binaryStringToHex(this.compressedKey));
        // Generate round keys
        for (int i = 0; i < 16; i++) {
            // Left Shift left and right key blocks
            this.leftKey.rotl(KEYSHIFT[i]);
            this.rightKey.rotl(KEYSHIFT[i]);
            String shiftedKey = this.leftKey.toString() + this.rightKey.toString();
            // Perform round key permutation
            String roundKey = "";
            for (int y = 0; y < 48; y++) {
                roundKey += shiftedKey.charAt(KEY_PERM2[y]);
            }
            rounds.add(new DESRound(new BitArray(roundKey)));
        }

    }

    public String encrypt(String plaintextBlock) {
        this.plaintextHex = plaintextBlock;
        String response = "";
        System.out.println("ENCRYPT PLAINTEXT: " + plaintextBlock);
        // Check plaintextBlock is in hex format and is 64 bits
        if (plaintextBlock.length() == 16 && CryptoUtils.isHexString(plaintextBlock)) {
            String binPlaintextBlock = CryptoUtils.hexStringToBinary(plaintextBlock);
            String initPerm = this.initialPermutation(binPlaintextBlock);

            // Split IP into left and right, each 32 bits
            BitArray left = new BitArray(initPerm.substring(0, 32));
            BitArray right = new BitArray(initPerm.substring(32, 64));

            // Provide a round counter purely as feedback for enduser.
            int currentRound = 1;
            // Compute 16 rounds
            for (DESRound round : rounds) {
                round.doRound(left, right, currentRound);
                currentRound++;
                //Swap left and right
                BitArray temp = right;
                right = left;
                left = temp;
            }
            // Combine left and right
            String ciphertext = permutation(right.toString() + left.toString(), INIT_PERM_INV);
            this.ciphertextHex = CryptoUtils.binaryStringToHex(ciphertext);
            System.out.println("CIPHERTEXT: " + this.ciphertextHex);
            CryptoResponseEncryptDES ok = new CryptoResponseEncryptDES(plaintextBlock, this.hexKey, this.ciphertextHex, CryptoUtils.binaryStringToHex(this.compressedKey), this.rounds);
            response = this.jsonb.toJson(ok);
        } else {
            CryptoResponseError error = new CryptoResponseError("Invalid key format");
            response = this.jsonb.toJson(error);
        }
        return response;
    }

    public String decrypt(String ciphertextBlock) {
        this.ciphertextHex = ciphertextBlock;
        String response = "";
        // Check ciphertextBlock is in hex format and is 64 bits
        System.out.println("DECRYPT CIPHERTEXT: " + ciphertextBlock);
        if (ciphertextBlock.length() == 16 && CryptoUtils.isHexString(ciphertextBlock)) {
            String binCiphertextBlock = CryptoUtils.hexStringToBinary(ciphertextBlock);
            String initPerm = this.initialPermutation(binCiphertextBlock);

            // Split IP into left and right, each 32 bits
            BitArray left = new BitArray(initPerm.substring(0, 32));
            BitArray right = new BitArray(initPerm.substring(32, 64));

            // Compute 16 rounds in reverse order for decryption
            for (int i = rounds.size() - 1; i >= 0; i--) {
                rounds.get(i).doRound(left, right, i + 1);

                //Swap left and right
                BitArray temp = right;
                right = left;
                left = temp;
            }
            // Combine left and right
            String plaintext = permutation(right.toString() + left.toString(), INIT_PERM_INV);
            this.plaintextHex = CryptoUtils.binaryStringToHex(plaintext);
            System.out.println("PLAINTEXT: " + this.plaintextHex);
            CryptoResponseDecryptDES ok = new CryptoResponseDecryptDES(this.plaintextHex, this.hexKey, ciphertextBlock, CryptoUtils.binaryStringToHex(this.compressedKey), this.rounds);
            response = this.jsonb.toJson(ok);
        } else {
            CryptoResponseError error = new CryptoResponseError("Invalid key format");
            response = this.jsonb.toJson(error);
        }
        return response;
    }

    private String permutation(String data, int[] permArr) {
        String perm = "";
        for (int i = 0; i < 64; i++) {
            perm += data.charAt(permArr[i]);
        }
        return perm;
    }

    private String initialPermutation(String data) {
        String initPerm = "";
        for (int i = 0; i < 64; i++) {
            initPerm += data.charAt(INIT_PERM[i]);
        }
        return initPerm;
    }

    public String getPlaintextHex() {
        return plaintextHex;
    }

    public String getCiphertextHex() {
        return ciphertextHex;
    }

}
