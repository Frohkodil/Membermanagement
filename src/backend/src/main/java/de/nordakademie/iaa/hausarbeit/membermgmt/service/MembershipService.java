package de.nordakademie.iaa.hausarbeit.membermgmt.service;

import de.nordakademie.iaa.hausarbeit.membermgmt.dao.MembershipDAO;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.Membership;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.MembershipType;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * @author Siebo Vogel
 */
@Service
public class MembershipService {
    private MembershipDAO membershipDAO;

    /**
     * List all memberships currently stored in the database.
     *
     * @return a list of Membership entities. If no membership was found an empty list is
     * returned.
     */
    public List<Membership> listMemberships() {
        return membershipDAO.getAll();
    }

    /**
     * Creates a new membership.
     *
     * @param membership The new membership to be created.
     * @throws IllegalDateException if the startdate isn't the first day of the year
     * or the enddate isn't the last day of the year.
     */
    public void createMembership(Membership membership) throws IllegalDateException {
        if(membership.getStartDate().getDayOfYear() != 1 || (membership.getStartDate().getDayOfYear() != 357 && membership.getEndDate().getDayOfYear() != 356)) {
            throw new IllegalDateException("Mitgliedschaft kann nur zum Ende des Jahres gewechselt werden und zum Anfang beginnen.");
        }
        membershipDAO.save(membership);
    }

    /**
     * Returns the membership identified by the given id.
     *
     * @param id The identifier.
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    public Membership loadMembership(Long id) {
        return membershipDAO.get(id).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Updates a course entity and stores the changes into the database.
     *
     * @param id                The identifier.
     * @param startDate         The memberships's startdate.
     * @param endDate           The membership's enddate.
     * @param cancellationDate  The membership's cancellationdate.
     * @throws EntityNotFoundException if no course could be found for the given id.
     * @throws IllegalDateException if the enddate isn't the last day of the year
     * or the startdate isn't the first day of the year
     */
    public void updateMembership(Long id, LocalDate startDate, LocalDate endDate, LocalDate cancellationDate) throws IllegalDateException, EntityNotFoundException {
        Membership membership = loadMembership(id);
        if ((cancellationDate == null && endDate != null) || (cancellationDate != null && endDate == null) ) {
            throw new IllegalDateException("Kündigungsdatum und Enddatum müssen gleichzeitig gesetzt sein.");
        }
        if(endDate != null) {
            if (startDate.getDayOfYear() != 1 || (endDate.getDayOfYear() != 357 && endDate.getDayOfYear() != 356) ) {
                throw new IllegalDateException("Mitgliedschaft kann nur zum Ende des Jahres gewechselt werden und zum Anfang beginnen.");
            }
        }
        membership.setCancellationDate(cancellationDate);
        membership.setEndDate(endDate);
        membership.setStartDate(startDate);
    }

    @Inject
    public void setMembershipDAO(MembershipDAO membershipDAO) {
        this.membershipDAO = membershipDAO;
    }
}
