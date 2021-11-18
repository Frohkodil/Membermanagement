package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.Member;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.MemberSearchParameters;
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

@Repository
public class MemberDAO implements DAO<Member> {
    private EntityManager entityManager;


    @Override
    public Optional<Member> get(Long id) {
        return Optional.ofNullable(entityManager.find(Member.class, id));
    }

    @Override
    public List<Member> getAll() {
        return entityManager.createQuery("from Member", Member.class).getResultList();
    }

    @Override
    public void save(Member member) throws ConstraintViolationException {
        entityManager.persist(member);
    }

    @Override
    public void update(Member member) {

    }

    @Override
    public void delete(Member member) {
        entityManager.remove(member);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Member> search(MemberSearchParameters memberSearchParameters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Member> query = criteriaBuilder.createQuery(Member.class);
        Root<Member> member = query.from(Member.class);

        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotEmpty(memberSearchParameters.getFirstName()))
            predicates.add(criteriaBuilder.equal(member.get("firstName"), memberSearchParameters.getFirstName()));
        if (StringUtils.isNotEmpty(memberSearchParameters.getLastName()))
            predicates.add(criteriaBuilder.equal(member.get("lastName"), memberSearchParameters.getLastName()));
        if (StringUtils.isNotEmpty(memberSearchParameters.getPostalCode()))
            predicates.add(criteriaBuilder.equal(member.get("postalCode"), memberSearchParameters.getPostalCode()));
        if (StringUtils.isNotEmpty(memberSearchParameters.getCity()))
            predicates.add(criteriaBuilder.equal(member.get("city"), memberSearchParameters.getCity()));
       // if (StringUtils.isNotEmpty(memberSearchParameters.getDateOfBirth()))
        //    predicates.add(criteriaBuilder.equal(member.get("firstName"), memberSearchParameters.getFirstName()));


        query.select(member).where(predicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(query).getResultList();
    }
}
