package uk.co.cypherlogic;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BitArray Class An array of Bit objects which can be rotated, XOR'd, and
 * flipped
 *
 * @author ESRS Group 2
 * @version 2022-02-03
 */
public class BitArray {

    private final Bit[] bitArray;
    private int idx = 0;

    /**
     * BitArray Constructor.
     *
     * @param bits A string of a binary number to init the BitArray
     * @see Bit
     */
    public BitArray(String bits) {
        if (bits.length() < 1) {
            bits = "00000000";
        }
        this.bitArray = new Bit[bits.length()];
        CharacterIterator it = new StringCharacterIterator(bits);

        while (it.current() != CharacterIterator.DONE) {
            this.bitArray[idx] = new Bit(it.current());
            idx = ++idx % this.bitArray.length;
            it.next();
        }
    }

    /**
     * BitArray Constructor
     *
     * @see Bit
     */
    public BitArray() {
        this("");
    }

    /**
     * BitArray Constructor. Creates an empty BitRate of length provided
     *
     * @param len An int to specify length of the BitArray
     */
    public BitArray(int len) {
        this.bitArray = new Bit[len];
    }

    /**
     * Rotate bits right by a number of places
     *
     * @param places An int indicating the number of rotations to make
     */
    public void rotr(int places) {
        idx = this.dec_idx(places);
    }

    /**
     * Rotate bits left by a number of places
     *
     * @param places An int indicating the number of rotations to make
     */
    public void rotl(int places) {
        idx = inc_idx(places);
    }

    /**
     * XOR BitArray with the given BitArray parameter
     *
     * @param word A BitArray to XOR against this BitArray
     */
    public void xor(BitArray word) {
        int xor_span = bitArray.length;
        if (word.size() < xor_span) {
            xor_span = word.size();
        }
        for (int i = 0; i < xor_span; i++) {
            bitArray[inc_idx(i)].xor(word.getBitAt(i));
        }
    }

    /**
     * Invert all Bits in the BitArray
     */
    public void flip() {
        for (int i = 0; i < bitArray.length; i++) {
            bitArray[inc_idx(i)].flip();
        }
    }

    /**
     * Get the least significant Bit of the BitArray
     *
     * @return The least significant Bit
     */
    public Bit getLsb() {
        return bitArray[inc_idx(bitArray.length - 1)];
    }

    /**
     * Get the most significant Bit of the BitArray
     *
     * @return The most significant Bit
     */
    public Bit getMsb() {
        return bitArray[idx];
    }

    /**
     * Get the Bit at the given index
     *
     * @param index An int of the required element in the BitArray
     * @return The Bit at the given index position
     */
    public Bit getBitAt(int index) {
        return bitArray[inc_idx(index)];
    }

    /**
     * Replace Bit in BitArray at given index
     *
     * @param bit The Bit replacement
     * @param index An int indicating the replacement position in the BitArray
     */
    public void putBitAt(Bit bit, int index) {
        bitArray[inc_idx(index)] = bit;
    }

    /**
     * ==== TO DO ==== Set a bit at index position
     *
     * @param bit
     * @param index
     */
    public void setBitAt(String bit, int index) {

    }

    /**
     * Get the length of the BitArray
     *
     * @return An int length of the BitArray
     */
    public int size() {
        return bitArray.length;
    }

    /**
     * ==== TO DO ==== Append a BitArray onto the end of this BitArry
     *
     * @param ba The BitArray to append to this
     */
    public void append(BitArray ba) {
        //BitArray = new BitArray(bitArray.toString() + ba.toString());
    }

    /**
     * Override the toString method
     *
     * @return A String representing the BitArray as a binary number
     */
    @Override
    public String toString() {
        String pnt = "";
        for (int i = 0; i < bitArray.length; i++) {
            pnt += bitArray[inc_idx(i)].toString();
        }
        return pnt;
    }

    /**
     * Override hashCode method
     *
     * @return An int hashCode of BitArray object
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Arrays.deepHashCode(this.bitArray);
        hash = 53 * hash + this.idx;
        return hash;
    }

    /**
     * Override equals method
     *
     * @return A boolean true is object past is equal to this
     */
    @Override
    public boolean equals(Object obj) {
        // Two BitArrays are equal if the current bit pattern is the same
        // We must take into account any bit rotations performed on either of the BitArrays
        // e.g. 0101 is not equal to 1010 but if 1010 is rotated right one place then they are equal

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BitArray other = (BitArray) obj;
        // BitArrays should be of identical length
        if (this.size() != other.size()) {
            return false;
        }

        // Bit pattern must match regardless of previous manipulations
        for (int i = 0; i < this.size(); i++) {
            if (!this.getBitAt(i).equals(other.getBitAt(i))) {
                return false;
            }
        }

        return true;
    }

    /*
     * Private utility method. Offset the position of the current index into the
     * BitArray by plus places, wrap around on overflow @param places An int to
     * offset the current index position @return An int of the new index
     * position
     */
    private int inc_idx(int places) {
        int new_pos = (idx + places) % bitArray.length;
        // Java does not do modulus, % is actually remainder, so requires more work for negative values
        if (new_pos < 0) {
            new_pos += bitArray.length;
        }
        return new_pos;
    }

    /*
     * Private utility method. Offset the position of the current index into the
     * BitArray by negative places, wrap around on overflow @param places An int
     * to decrement the current index position @return An int of the new index
     * positions
     */
    private int dec_idx(int places) {
        int new_pos = (idx - places) % bitArray.length;

        // Java does not do modulus, % is actually remainder, so requires more work for negative values
        if (new_pos < 0) {
            new_pos += bitArray.length;
        }
        return new_pos;
    }

}
