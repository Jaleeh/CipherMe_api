package uk.co.cypherlogic;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
 

public class RSA {
 
    private BigInteger P;
    private BigInteger Q;
    private BigInteger N;
    private BigInteger PHI;
    private BigInteger e;
    private BigInteger d;
    private int maxLength = 1024;
    private Random R;
    private byte[] ciphertext;
    private String ciphertextString;
    private byte[] plaintext;
    private String plaintextString;
    private String result;

    public RSA() {
        R = new Random();

        P = BigInteger.probablePrime(maxLength, R);
P.isProbablePrime(1);
        Q = BigInteger.probablePrime(maxLength, R);
        N = P.multiply(Q);
        PHI = P.subtract(BigInteger.ONE).multiply(Q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(maxLength / 2, R);
        while (PHI.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(PHI) < 0) {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(PHI);
    }

    public RSA(BigInteger e, BigInteger d, BigInteger N) {
        this.e = e;
        this.d = d;
        this.N = N;
    }

    public RSA(String plaintext, BigInteger P, BigInteger Q) {
        this.plaintext = plaintext.getBytes();
        this.P = P;
        this.Q = Q;

    }

     
    private static String bToS(byte[] cipher) {
        String temp = "";
        for (byte b : cipher) {
            temp += Byte.toString(b);
        }
        return temp;
    }

    // Encrypting the message
    public String encryptMessage(String message, BigInteger P, BigInteger Q, BigInteger e) {
        if ( P.isProbablePrime(1)&& Q.isProbablePrime(1) ) {
        N = P.multiply(Q);
        PHI = P.subtract(BigInteger.ONE).multiply(Q.subtract(BigInteger.ONE));
        
        while (PHI.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(PHI) < 0) {
            e.add(BigInteger.ONE);
        
    
        this.plaintext = message.getBytes();
        this.ciphertext = (new BigInteger(message)).modPow(e, N).toByteArray();
        ciphertextString = bToS (ciphertext);
        this.result = "ENCRYPTED";
       
}
 return "{\"result\":\"the encrypted message is\"}"+ ciphertextString;
}
else { this.result = "ERROR"; 
return "{\"result\":\"Please enter prime numbers\"}";
}
    }

    // Decrypting the message
    public String decryptMessage(String message) {
 if ( P.isProbablePrime(1)&& Q.isProbablePrime(1) ) {
        N = P.multiply(Q);
        PHI = P.subtract(BigInteger.ONE).multiply(Q.subtract(BigInteger.ONE));
        
        while (PHI.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(PHI) < 0) {
            e.add(BigInteger.ONE);
        
        d = e.modInverse(PHI);
        this.ciphertext = message.getBytes();
        this.plaintext = (new BigInteger(message)).modPow(d, N).toByteArray();
        plaintextString = bToS (plaintext);
        this.result = "DECRYPTED";
}
   return "{\"result\":\"the decrypted message is\"}"+ plaintextString;
}
else { this.result = "ERROR"; 
return "{\"result\":\"Please enter prime numbers\"}";
}
}

    public String jsonResponse() {

        return "{\"result\":\"working progress\"}";
    }

}
