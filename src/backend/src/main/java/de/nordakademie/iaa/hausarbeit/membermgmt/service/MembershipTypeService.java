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
        return membershipTypeDAO.getAll();
    }

    public MembershipType loadMembershipType(Long id) {
       return membershipTypeDAO.get(id).orElseThrow(EntityNotFoundException::new);
    }

    public void updateMembershipType(Long id, String name, BigDecimal annualFee) {
        MembershipType membershipType = loadMembershipType(id);
        membershipType.setAnnualFee(annualFee);
        membershipType.setName(name);
    }

    public void createMembershipType(MembershipType membershipType) throws EntityAlreadyPresentException {
        try {
            membershipTypeDAO.save(membershipType);
        } catch (ConstraintViolationException e) {
            throw new EntityAlreadyPresentException();
        }
    }
    
    @Inject
    public void setMembershipTypeDAO(MembershipTypeDAO membershipTypeDAO) {
        this.membershipTypeDAO = membershipTypeDAO;
    }
}
