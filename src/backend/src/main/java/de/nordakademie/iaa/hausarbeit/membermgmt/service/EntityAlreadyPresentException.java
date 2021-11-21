package de.nordakademie.iaa.hausarbeit.membermgmt.service;
/**
 * Exception indication an already present entity.
 *
 * @author Siebo Vogel
 */
public class EntityAlreadyPresentException extends Throwable {
    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = 1L;


    /**
     * Constructor with message.
     *
     * @param errorMessage The message.
     */
    public EntityAlreadyPresentException(String errorMessage) {
        super(errorMessage);
    }

}
