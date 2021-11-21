package de.nordakademie.iaa.hausarbeit.membermgmt.service;

public class IllegalDateException extends Exception{
    public IllegalDateException(String errorMessage) {
        super(errorMessage);
    }
}
