package AB8;

import java.io.IOException;

/**
 * Thrown to indicate that a file does not meet the expected format.
 */
public class StateFileFormatException extends IOException {

    // TODO: implement class
    public StateFileFormatException(String message) {
        super(message);
    }
    public StateFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
