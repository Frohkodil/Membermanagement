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

@Service
public class MembershipService {
    private MembershipDAO membershipDAO;

    public List<Membership> listMemberships() {
        return membershipDAO.getAll();
    }

    public void createMembership(Membership membership) throws IllegalDateException {
        if(membership.getStartDate().getDayOfYear() != 1 || (membership.getStartDate().getDayOfYear() != 357 && membership.getEndDate().getDayOfYear() != 356)) {
            throw new IllegalDateException("Mitgliedschaft kann nur zum Ende des Jahres gewechselt werden und zum Anfang beginnen.");
        }
        membershipDAO.save(membership);
    }

    public Membership loadMembership(Long id) {
        return membershipDAO.get(id).orElseThrow(EntityNotFoundException::new);
    }

    public void updateMembership(Long id, LocalDate startDate, LocalDate endDate, LocalDate cancellationDate, MembershipType membershipType) throws IllegalDateException {
        Membership membership = loadMembership(id);
        if ((cancellationDate == null && endDate != null) || (cancellationDate != null && endDate == null) ) {
            throw new IllegalDateException("Kündigungsdatum und Enddatum müssen gleichzeitig gesetzt sein.");
        }
        if(endDate != null) {
            if (startDate.getDayOfYear() != 1 || (endDate.getDayOfYear() != 357 && endDate.getDayOfYear() != 356) ) {
                throw new IllegalDateException("Mitgliedschaft kann nur zum Ende des Jahres gewechselt werden und zum Anfang beginnen.");
            }
        }
        membership.setMembershipType(membershipType);
        membership.setCancellationDate(cancellationDate);
        membership.setEndDate(endDate);
        membership.setStartDate(startDate);
    }

    @Inject
    public void setMembershipDAO(MembershipDAO membershipDAO) {
        this.membershipDAO = membershipDAO;
    }
}
