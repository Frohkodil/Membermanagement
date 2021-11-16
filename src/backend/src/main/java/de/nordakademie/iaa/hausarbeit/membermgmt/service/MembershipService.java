package de.nordakademie.iaa.hausarbeit.membermgmt.service;

import de.nordakademie.iaa.hausarbeit.membermgmt.dao.MembershipDAO;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.Membership;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.MembershipType;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public class MembershipService {
    private MembershipDAO membershipDAO;

    public List<Membership> listMemberships() {
        return membershipDAO.listMemberships();
    }

    public void createMembership(Membership membership) throws EntityAlreadyPresentException {
        try {
            membershipDAO.persistMembership(membership);
        } catch (ConstraintViolationException e) {
            throw new EntityAlreadyPresentException();
        }
    }

    public Membership loadMembership(Long id) {
        return membershipDAO.loadMembership(id);
    }

    public void updateMembership(Long id, Date startDate, Date endDate, Date cancellationDate, MembershipType membershipType) {
        Membership membership = membershipDAO.loadMembership(id);
        if(membership == null) {
            throw new EntityNotFoundException();
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
