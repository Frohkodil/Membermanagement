package model;

import java.time.LocalDate;

public class Membership {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate cancellationDate;
    private MembershipType membershipType;
}
