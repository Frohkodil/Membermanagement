package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.Membership;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * The membership DAO that manages all persistence functionality.
 *
 * @author Siebo Vogel
 */

@Repository
public class MembershipDAO{
    /**
     * The current entity manager.
     */
    private EntityManager entityManager;

    /**
     * Returns the membership identified by the given id.
     *
     * @param id The identifier.
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    public Optional<Membership> get(Long id) {
        return Optional.ofNullable(entityManager.find(Membership.class, id));
    }

    /**
     * List all memberships currently stored in the database.
     *
     * @return a list of Membership entities. If no Membership was found an empty list is
     * returned.
     */
    public List<Membership> getAll() {
        return entityManager.createQuery("from Membership").getResultList();
    }

    /**
     * Takes the Membership and stores it in the database.
     *
     * @param membership The Membership to be persisted.
     */
    public void save(Membership membership) {
        entityManager.persist(membership);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
