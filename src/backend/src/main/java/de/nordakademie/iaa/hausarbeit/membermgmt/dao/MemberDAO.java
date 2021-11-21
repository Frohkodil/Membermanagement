package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.Member;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.MemberSearchParameter;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.PaymentHistory;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The member DAO that manages all persistence functionality.
 *
 * @author Siebo Vogel
 */

@Repository
public class MemberDAO {
    /**
     * The current entity manager.
     */
    private EntityManager entityManager;


    /**
     * Returns the member identified by the given id.
     *
     * @param id The identifier.
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    public Optional<Member> get(Long id) {
        return Optional.ofNullable(entityManager.find(Member.class, id));
    }

    /**
     * List all members currently stored in the database.
     *
     * @return a list of member entities. If no member was found an empty list is
     * returned.
     */
    public List<Member> getAll() {
        return entityManager.createQuery("from Member", Member.class).getResultList();
    }

    /**
     * Takes the member and stores it in the database.
     *
     * @param member The member to be persisted.
     * @throws ConstraintViolationException if a member with the same building/member number
     *                                      combination is already present in the database.
     */
    public void save(Member member) throws ConstraintViolationException {
        entityManager.persist(member);
    }

    /**
     * Deletes the member.
     *
     * @param member The member to be deleted.
     */
    public void delete(Member member) {
        entityManager.remove(member);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Returns a list of members identified by the given searchparameters.
     *
     * @param memberSearchParameter The search parameters.
     * @return the found entities in a list or an empty list if no entity was found.
     */
    public List<Member> search(MemberSearchParameter memberSearchParameter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Member> query = criteriaBuilder.createQuery(Member.class);
        Root<Member> member = query.from(Member.class);
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotEmpty(memberSearchParameter.getFirstName()))
            predicates.add(criteriaBuilder.equal(member.get("firstName"), memberSearchParameter.getFirstName()));
        if (StringUtils.isNotEmpty(memberSearchParameter.getLastName()))
            predicates.add(criteriaBuilder.equal(member.get("lastName"), memberSearchParameter.getLastName()));
        if (StringUtils.isNotEmpty(memberSearchParameter.getPostalCode()))
            predicates.add(criteriaBuilder.equal(member.get("postalCode"), memberSearchParameter.getPostalCode()));
        if (StringUtils.isNotEmpty(memberSearchParameter.getCity()))
            predicates.add(criteriaBuilder.equal(member.get("city"), memberSearchParameter.getCity()));
        if (memberSearchParameter.getDateOfBirth() != null)
            predicates.add(criteriaBuilder.equal(member.get("dateOfBirth"), memberSearchParameter.getDateOfBirth()));
        query.select(member).where(predicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(query).getResultList();
    }

    /**
     * Returns a list of members with active memberships.
     *
     * @return the found entities in a list or an empty list if no entity was found.
     */
    public List<Member> getActiveMembers() {
        return entityManager.createQuery("from Member where exists (from Membership where endDate = null)", Member.class).getResultList();
    }
}
