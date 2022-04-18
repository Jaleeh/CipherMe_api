package uk.co.cypherlogic;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

/**
 * Class to configure and perform stream cipher using LFSR
 *
 * @author ESRS Group 2
 * @version 2022-03-16
 */
public class StreamCipher {

    String plaintext;
    String ciphertext;
    String lfsrstream;
    String iv;
    String taps;
    LFSR lfsr;

    public StreamCipher(String iv, String taps) {
        this.plaintext = "";
        this.ciphertext = "";
        this.lfsrstream = "";
        this.iv = iv;
        this.taps = taps;
    }

    public String encrypt(String plaintext) {
        Jsonb jsonb = JsonbBuilder.create();
        this.plaintext = plaintext;
        this.ciphertext = "";
        this.lfsrstream = "";
        String response = "";

        // Check plaintext is in binary format, e.g. "010111000110";
        if (!CryptoUtils.isBinaryString(this.plaintext)) {
            CryptoResponseError error = new CryptoResponseError("Plaintext is not a binary string");
            return jsonb.toJson(error);
        }

        try {
            this.lfsr = new LFSR(this.iv, this.taps);
            for (int i = 0; i < this.plaintext.length(); i++) {
                Bit b = new Bit(this.plaintext.charAt(i));

                // Get Bit from LFSR
                Bit x = lfsr.step();
                this.lfsrstream += x;
                // XOR LFSR Bit with current Bit
                b.xor(x);
                this.ciphertext += b;
            }
            System.out.println("CIPHERTEXT: " + this.ciphertext);
            CryptoResponseEncryptStream ok = new CryptoResponseEncryptStream(this.plaintext, this.iv, this.taps, this.lfsrstream, this.ciphertext);
            response = jsonb.toJson(ok, CryptoResponseEncryptStream.class);
        } catch (Exception ex) {
            CryptoResponseError error = new CryptoResponseError("Invalid IV and taps");
            response = jsonb.toJson(error);
        }
        return response;
    }

    public String decrypt(String ciphertext) {
        Jsonb jsonb = JsonbBuilder.create();
        this.plaintext = "";
        this.ciphertext = ciphertext;
        this.lfsrstream = "";
        String response = "";

        // Check ciphertext is in binary format, e.g. "010111000110";
        if (!CryptoUtils.isBinaryString(this.ciphertext)) {
            CryptoResponseError error = new CryptoResponseError("Ciphertext is not a binary string");
            return jsonb.toJson(error);
        }

        try {
            this.lfsr = new LFSR(this.iv, this.taps);
            for (int i = 0; i < this.ciphertext.length(); i++) {
                Bit b = new Bit(this.ciphertext.charAt(i));

                // Get Bit from LFSR
                Bit x = lfsr.step();
                this.lfsrstream += x;

                // XOR LFSR Bit with current Bit
                b.xor(x);
                this.plaintext += b;
            }
            System.out.println("PLAINTEXT: " + this.plaintext);
            CryptoResponseDecryptStream ok = new CryptoResponseDecryptStream(this.plaintext, this.iv, this.taps, this.lfsrstream, this.ciphertext);
            response = jsonb.toJson(ok, CryptoResponseEncryptStream.class);
        } catch (Exception ex) {
            CryptoResponseError error = new CryptoResponseError("Invalid IV and taps");
            response = jsonb.toJson(error);
        }
        return response;
    }
}
