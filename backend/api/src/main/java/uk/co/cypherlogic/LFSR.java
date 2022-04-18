/*
 * TO DO
 * Create KeyLength Exception
 */
package uk.co.cypherlogic;

import java.util.ArrayList;

/**
 * Linear-Feedback Shift Register Class
 *
 * @author ESRS Group 2
 * @version 2022-02-04
 */
public class LFSR {

    BitArray lfsr;
    ArrayList<Integer> taps;

    /**
     * LFSR Constructor
     *
     * @param iv A String of binary representing the initialisation vector
     * @param taps A String of binary representing the tap positions. 1 is an
     * active tap, 0 otherwise
     * @throws KeyLength Exception when iv is a different length to the taps
     * string
     */
    public LFSR(String iv, String taps) throws Exception {
        if (iv.length() != taps.length()) {
            throw new Exception("Initilisation vector length mismatch with taps");
        } else {
            lfsr = new BitArray(iv);
            this.taps = new ArrayList<>();
            for (int i = 0; i < taps.length(); i++) {
                if (taps.charAt(i) == '1') {
                    this.taps.add(i);
                }
            }
        }
    }

    /**
     * Get the current state of the LFSR
     *
     * @return A BitArray representing the LFSR
     */
    public BitArray getLfsr() {
        return lfsr;
    }

    /**
     * Get the LFSR tap positions
     *
     * @return The ArrayList<Integer> of tap positions
     */
    public ArrayList<Integer> getTaps() {
        return taps;
    }

    /**
     * Perform one step of the LFSR. Return output Bit
     *
     * @return A Bit outputted by a step of the lFSR
     */
    public Bit step() {
        Bit lsb = lfsr.getLsb();
        // Create new msb Bit
        Bit msb = new Bit();
        // XOR msb with taps
        for (int tap : taps) {
            msb.xor(lfsr.getBitAt(tap));
        }
        // Rotate right one step so LSB is moved to MSB
        lfsr.rotr(1);
        // Set MSB to new msb
        lfsr.putBitAt(msb, 0);
        return lsb;
    }

    /**
     * Perform a series of steps. Return output as BitArray
     *
     * @param num_steps An int number of steps to perform
     * @return A BitArray of Bits containing sequencial output of the LFSR
     */
    public BitArray doSteps(int num_steps) {
        BitArray word = new BitArray(num_steps);
        for (int i = 0; i < num_steps; i++) {
            word.putBitAt(this.step(), i);
        }
        return word;
    }
}
