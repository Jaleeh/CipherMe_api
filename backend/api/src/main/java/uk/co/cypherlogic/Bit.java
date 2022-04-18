package uk.co.cypherlogic;

/**
 * A Class to represent a bit of data
 *
 * @author ESRS Group 2
 * @version 2022-02-03
 */
public class Bit {

    boolean bit;

    /**
     * Default Bit Constructor, sets Bit to 0
     */
    public Bit() {
        this.bit = false;
    }

    /**
     * Bit Constructor using a boolean to initialise Bit value
     *
     * @param bit A boolean to represent a binary digit, true == '1', false ==
     * '0'
     */
    public Bit(boolean bit) {
        this.bit = bit;
    }

    /**
     * Bit Constructor using an int to initialise Bit value. Any non-zero
     * integer is 1, zero is 0
     *
     * @param bit A int representation of a binary digit, 0 == '0' sets Bit to
     * '0', otherwise '1'
     */
    public Bit(int bit) {
        this.bit = (bit != 0);
    }

    /**
     * Bit Constructor using a character to initialise Bit value.
     *
     * @param bit A char representing a binary digit, '0' sets Bit to '0',
     * otherwise '1'
     */
    public Bit(char bit) {
        this.bit = (bit != '0');
    }

    /**
     * Is this Bit == 0?
     *
     * @return A boolean true if binary digit is '0'
     */
    public boolean isZero() {
        return this.bit == false;
    }

    /**
     * Is this Bit == 1?
     *
     * @return A boolean true if binary digit is '1'
     */
    public boolean isOne() {
        return this.bit;
    }

    /**
     * Invert the Bit
     */
    public void flip() {
        this.bit = !this.bit;
    }

    /**
     * XOR this bit with supplied bit
     *
     * @param bit A Bit to XOR
     */
    public void xor(Bit bit) {
        if (this.bit && bit.isOne()) {
            this.flip();
        } else if (bit.isOne() || this.bit) {
            this.bit = true;
        }
    }

    /**
     * Copy the Bit
     *
     * @return A Bit copy
     */
    public Bit copy() {
        return new Bit(this.bit);
    }

    /**
     * Override toString method to output the Bit as either "0" or "1"
     */
    @Override
    public String toString() {
        return (this.bit ? "1" : "0");
    }

    /**
     * Override the object's hashCode method
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.bit ? 1 : 0);
        return hash;
    }

    /**
     * Override the object's equals method
     *
     * @param obj the Object to compare for equality
     * @return A boolean of the equality comparison
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bit other = (Bit) obj;
        return this.bit == other.bit;
    }

}
