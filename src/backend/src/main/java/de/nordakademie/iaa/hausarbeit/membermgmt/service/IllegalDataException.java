package de.nordakademie.iaa.hausarbeit.membermgmt.service;

/**
 * Exception indicating illegal data.
 *
 * @author Siebo Vogel
 */
public class IllegalDataException extends Exception {
    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor with message.
     *
     * @param message The message.
     */
    public IllegalDataException(String message) {
        super(message);
    }
}
