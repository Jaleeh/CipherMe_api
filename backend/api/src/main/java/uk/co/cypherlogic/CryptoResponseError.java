package uk.co.cypherlogic;

/**
 * XML/JSON CryptoResponseError to return an error message
 *
 * @author ESRS Group 2
 * @version 2022-03-16
 * @see CryptoResponse
 */
public class CryptoResponseError {

    private String message;
    private String result;

    public CryptoResponseError() {
    }

    public CryptoResponseError(String message) {
        this.result = "ERROR";
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "{\"result\":\"" + result + "\", \"message\":\"" + this.message + "\"}";
    }
}
