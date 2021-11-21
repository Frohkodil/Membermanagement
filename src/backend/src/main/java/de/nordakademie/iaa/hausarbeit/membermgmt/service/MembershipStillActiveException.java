package de.nordakademie.iaa.hausarbeit.membermgmt.service;
/**
 * Exception indicating that a membership is still active
 *
 * @author Siebo Vogel
 */
public class MembershipStillActiveException extends Exception {
    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor with message.
     *
     * @param errorMessage The message.
     */
    public MembershipStillActiveException(String errorMessage) {
        super(errorMessage);
    }
}
