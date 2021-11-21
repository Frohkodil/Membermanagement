package de.nordakademie.iaa.hausarbeit.membermgmt.service;

import de.nordakademie.iaa.hausarbeit.membermgmt.dao.MembershipTypeDAO;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.MembershipType;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Siebo Vogel
 */
@Service
public class MembershipTypeService {

    private MembershipTypeDAO membershipTypeDAO;

    /**
     * List all MembershipTypes currently stored in the database.
     *
     * @return a list of MembershipType entities. If no membershiptype was found an empty list is
     * returned.
     */
    public List<MembershipType> listMembershipTypes() {
        return membershipTypeDAO.getAll();
    }

    /**
     * Loads a membershiptype identified by an id.
     *
     * @param id The identifier.
     * @return the found entity.
     * @throws EntityNotFoundException if the entity can't be found.
     */
    public MembershipType loadMembershipType(Long id) {
       return membershipTypeDAO.get(id).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Updates a membershiptype.
     *
     * @param id The identifier.
     * @param name The name.
     * @param annualFee The annual fee of the membershiptype.
     */
    public void updateMembershipType(Long id, String name, BigDecimal annualFee) {
        MembershipType membershipType = loadMembershipType(id);
        membershipType.setAnnualFee(annualFee);
        membershipType.setName(name);
    }

    /**
     * Takes the new membershiptype and stores it in the database.
     *
     * @param membershipType The membershiptype to be created.
     */
    public void createMembershipType(MembershipType membershipType){
        membershipTypeDAO.save(membershipType);
    }

    @Inject
    public void setMembershipTypeDAO(MembershipTypeDAO membershipTypeDAO) {
        this.membershipTypeDAO = membershipTypeDAO;
    }
}
