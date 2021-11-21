package de.nordakademie.iaa.hausarbeit.membermgmt.service;

public class MembershipStillActiveException extends Exception {
    public MembershipStillActiveException(String errorMessage) {
        super(errorMessage);
    }
}
