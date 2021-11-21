package de.nordakademie.iaa.hausarbeit.membermgmt.service;

public class EntityAlreadyPresentException extends Throwable {
    public EntityAlreadyPresentException(String errorMessage) {
        super(errorMessage);
    }
    
}
