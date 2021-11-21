package de.nordakademie.iaa.hausarbeit.membermgmt.service;

/**
 * Exception indicating an illegal date.
 *
 * @author Siebo Vogel
 */
public class IllegalDateException extends Exception{
    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor with message.
     *
     * @param errorMessage The message.
     */
    public IllegalDateException(String errorMessage) {
        super(errorMessage);
    }
}
