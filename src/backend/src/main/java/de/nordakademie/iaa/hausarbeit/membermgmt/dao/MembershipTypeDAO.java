package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.MembershipType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MembershipTypeDAO {
    private EntityManager entityManager;


    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void persistMembershipType(MembershipType membershipType) {
        entityManager.persist(membershipType);
    }

    public MembershipType loadMembership(Long id) {
        return entityManager.find(MembershipType.class, id);
    }

    public List<MembershipType> listMembershipTypes() {
        return entityManager.createQuery("select membershiptype from MembershipType").getResultList();
    }
}
