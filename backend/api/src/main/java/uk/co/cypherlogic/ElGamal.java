/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.co.cypherlogic;

/**
 *
 * @author Amine
 */
import java.math.*;
import java.util.*;
import java.security.*;
import java.io.*;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;


public class ElGamal {

   
        BigInteger p, g, c, secretKey, PlaintextInt,brmodp,EC;
        Random sc = new SecureRandom();
        byte []  PlaintextBytes; 
        String result;

        //secretKey = new BigInteger("12345678901234567890");
        //
        // public key calculation
        //
     //   System.out.println("secretKey = " + secretKey);
       // p = BigInteger.probablePrime(64, sc);
        
//        c = g.modPow(secretKey, p);
//        System.out.println("p = " + p);
//        System.out.println("b = " + g);
//        System.out.println("c = " + c);
//        //
//        // Encryption 
//        //
//        System.out.print("Enter your Big Number message -->");
//        Scanner sc1 = new Scanner(System.in); //System.in is a standard input stream.
//        BigInteger s = sc1.nextBigInteger(); //reads B!ig  integer number
//
//        BigInteger r = new BigInteger(64, sc);
//        BigInteger EC = s.multiply(c.modPow(r, p)).mod(p);
//        BigInteger brmodp = g.modPow(r, p);
//        System.out.println("Plaintext = " + s);
//        System.out.println("r = " + r);
//        System.out.println("EC = " + EC);
//        System.out.println("b^r mod p = " + brmodp);
        //
        // Decryption
        //
//        BigInteger crmodp = brmodp.modPow(secretKey, p);
//        BigInteger d = crmodp.modInverse(p);
//        BigInteger ad = d.multiply(EC).mod(p);
//        System.out.println("\n\nc^r mod p = " + crmodp);
//        System.out.println("d = " + d);
//        System.out.println("Alice decodes: " + ad);

    
public String Encrypt(String Plaintext, BigInteger P , BigInteger secretKey){

    if (P.isProbablePrime(1)){
        g = new BigInteger("3");
        PlaintextBytes = Plaintext.getBytes();
        PlaintextInt = new BigInteger(PlaintextBytes);
        BigInteger r = new BigInteger(64, sc);
        this.EC = PlaintextInt.multiply(c.modPow(r, p)).mod(p);
        this.brmodp = g.modPow(r, p);
this.result = "ENCRYPT";
return "{\"result\":\"the encrypted message is\"}"+brmodp;
}

else { this.result = "ERROR"; 
return "{\"result\":\"Please enter prime numbers\"}";
}
}

public String Decrypt(String Plaintext, BigInteger P , BigInteger secretKey){
    if (P.isProbablePrime(1)){
        g = new BigInteger("3");
        PlaintextBytes = Plaintext.getBytes();
        BigInteger crmodp = brmodp.modPow(secretKey, p);
        BigInteger d = crmodp.modInverse(p);
        BigInteger ad = d.multiply(EC).mod(p);

this.result = "DECRYPT";
return "{\"result\":\"the decrypted message is \"}"+brmodp;
}
else { this.result = "ERROR"; 
return "{\"result\":\"Please enter prime numbers\"}";
}

}





}
