package Factorization;
import java.io.IOException;

/**
 * Exception for errors involving indices passed by user.
 * For example, inputting a number less than two to the
 * solver throws an InvalidIndexException.
 */
public class InvalidIndexException extends IOException {
    public InvalidIndexException(String s) {
        super(s);
    }
}
