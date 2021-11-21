package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.MembershipType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * The membershiptype DAO that manages all persistence functionality.
 *
 * @author Siebo Vogel
 */

@Repository
public class MembershipTypeDAO {
    /**
     * The current entity manager.
     */
    private EntityManager entityManager;


    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Returns the membershiptype identified by the given id.
     *
     * @param id The identifier.
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    public Optional<MembershipType> get(Long id) {
        return Optional.ofNullable(entityManager.find(MembershipType.class, id));
    }


    /**
     * List all membershiptypes currently stored in the database.
     *
     * @return a list of membershiptype entities. If no membershiptype was found an empty list is
     * returned.
     */
    public List<MembershipType> getAll() {
        return entityManager.createQuery("from MembershipType").getResultList();
    }

    /**
     * Takes the membershiptype and stores it in the database.
     *
     * @param membershipType The membershiptype to be persisted.
     */
    public void save(MembershipType membershipType) {
        entityManager.persist(membershipType);
    }

    /**
     * Deletes the membershiptype.
     *
     * @param membershipType The membershiptype to be deleted.
     */
    public void delete(MembershipType membershipType) {

    }
}
