import java.io.IOException;

public class InvalidIndexException extends IOException {
    public InvalidIndexException(String s) {
        super(s);
    }
}
