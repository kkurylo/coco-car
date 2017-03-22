package pl.kokokoko.persistance;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class OwnerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public OwnerEntity findOwnerById(Long id) {
        return entityManager.find(OwnerEntity.class, id);
    }

    public List<OwnerEntity> findOwner(Long id, String firstName, String lastName, String phoneNumber) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OwnerEntity> query = cb.createQuery(OwnerEntity.class);

        Root<OwnerEntity> root = query.from(OwnerEntity.class);

        List<Predicate> restrictions = new ArrayList<>();

        if (id != null) {
            Predicate p1 = cb.equal(root.get("id"), id);
            restrictions.add(p1);
        }

        if (firstName != null) {
            Predicate p2 = cb.equal(root.get("firstName"), firstName);
            restrictions.add(p2);
        }

        if (lastName != null) {
            Predicate p3 = cb.equal(root.get("lastName"), lastName);
            restrictions.add(p3);
        }

        if (phoneNumber != null) {
            Predicate p4 = cb.equal(root.get("phoneNumber"), phoneNumber);
            restrictions.add(p4);
        }

        Predicate[] restrictionsAsArray = restrictions.toArray(new Predicate[0]);

        query.where(cb.and(restrictionsAsArray));
        query.select(root);

        List<OwnerEntity> result = entityManager.createQuery(query).getResultList();
        return result;
    }

    public OwnerEntity addOwner(OwnerEntity owner) {
        entityManager.persist(owner);
        return owner;
    }

    public OwnerEntity editOwner(OwnerEntity owner) {
        entityManager.merge(owner);
        return owner;
    }

    public void deleteOwner(Long id) {
        OwnerEntity owner = entityManager.find(OwnerEntity.class, id);
        entityManager.remove(owner);
    }


}
