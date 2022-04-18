# Cryptography Explorer API and Frontend Application
975G5: Engineering Scalable and Reliable Software Group Project [21/22]


## Contributors
Amine-ounn

Jaleeh

jmichaelss

lr337

### Current Endpoints

Vigenere Encrypt: http://cypherlogic.co.uk:8080/crypto-0.1/vigenere/encrypt/PLAINTEXT/KEY

Vigenere Decrypt: http://cypherlogic.co.uk:8080/crypto-0.1/vigenere/decrypt/CIPHERTEXT/KEY


Caesar Encrypt: http://cypherlogic.co.uk:8080/crypto-0.1/caesar/encrypt/PLAINTEXT/K

Caesar Decrypt: http://cypherlogic.co.uk:8080/crypto-0.1/caesar/decrypt/CIPHERTEXT/K


One Time Pad Encrypt: http://cypherlogic.co.uk:8080/crypto-0.1/otp/encrypt/PLAINTEXT/KEYKEYKEY

One Time Pad Decrypt: http://cypherlogic.co.uk:8080/crypto-0.1/otp/decrypt/CIPHERTEXT/KEYKEYKEYK


**Stream Cipher with LFSR.**

Encrypt Path = stream/encrypt/{plaintextInBinary}/{initialisationVector}/{taps}

Decrypt Path = stream/decrypt/{ciphertextInBinary}/{initialisationVector}/{taps}


All input should be binary strings, e.g.

Encrypt: http://cypherlogic.co.uk:8080/crypto-0.1/stream/encrypt/11110010000110111100100001101111001000011011110010/101/101

Decrypt: http://cypherlogic.co.uk:8080/crypto-0.1/stream/decrypt/01010101010101010101010101010101010101010101010101/101/101


**Data Encryption Standard (DES)**

For a single block of 64 bit (16 hex chars) of plaintext with a 64 bit (16 hex chars key)

Encrypt Path = des/encrypt/{plaintextInHex}/{keyInHex}

Decrypt Path = des/decrypt/{ciphertextInHex}/{keyInHex}


Encrypt: http://cypherlogic.co.uk:8080/crypto-0.1/des/encrypt/0123456789ABCDEF/AABB09182736CCDD

Decrypt: http://cypherlogic.co.uk:8080/crypto-0.1/des/decrypt/F07704D0741EB2C2/AABB09182736CCDD

