package de.nordakademie.iaa.hausarbeit.membermgmt.service;

import de.nordakademie.iaa.hausarbeit.membermgmt.dao.MembershipTypeDAO;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.MembershipType;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class MembershipTypeService {
    private MembershipTypeDAO membershipTypeDAO;

    public List<MembershipType> listMembershipTypes() {
        return membershipTypeDAO.listMembershipTypes();
    }

    public MembershipType loadMembershipType(Long id) {
        MembershipType membershipType = membershipTypeDAO.loadMembership(id);
        return membershipType;
    }

    public void updateMembershipType(Long id, String name, BigDecimal annualFee) {
        MembershipType membershipType = loadMembershipType(id);
        if(membershipType == null) {
            throw new EntityNotFoundException();
        }
        membershipType.setAnnualFee(annualFee);
        membershipType.setName(name);
    }

    public void createMembershipType(MembershipType membershipType) throws EntityAlreadyPresentException {
        try {
            membershipTypeDAO.persistMembershipType(membershipType);
        } catch (ConstraintViolationException e) {
            throw new EntityAlreadyPresentException();
        }
    }
    
    @Inject
    public void setMembershipTypeDAO(MembershipTypeDAO membershipTypeDAO) {
        this.membershipTypeDAO = membershipTypeDAO;
    }

    public void loadMembership(Long id) {
    }
}
