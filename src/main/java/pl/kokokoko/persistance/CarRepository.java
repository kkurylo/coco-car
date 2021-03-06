package pl.kokokoko.persistance;

import io.spring.guides.gs_producing_web_service.Type;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public CarEntity findById(Long id) {
        return entityManager.find(CarEntity.class, id);
    }

    public CarEntity referenceById(Long id) {
        return entityManager.getReference(CarEntity.class, id);
    }

    public List<CarEntity> find(Long id, Type type, String make, Integer yearFrom, Integer yearTo, BigDecimal priceFrom,
                                BigDecimal priceTo, String color) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CarEntity> query = cb.createQuery(CarEntity.class);

        Root<CarEntity> root = query.from(CarEntity.class);

        List<Predicate> restrictions = new ArrayList<>();

        if (id != null) {
            Predicate p1 = cb.equal(root.get("id"), id);
            restrictions.add(p1);
        }
        if (type != null) {
            Predicate p2 = cb.equal(root.get("type"), id);
            restrictions.add(p2);
        }

        if (make != null) {
            Predicate p3 = cb.equal(root.get("make"), make);
            restrictions.add(p3);
        }

        if (yearFrom != null) {
            Predicate p4 = cb.ge(root.get("year"), yearFrom);
            restrictions.add(p4);
        }

        if (yearTo != null) {
            Predicate p5 = cb.le(root.get("year"), yearTo);
            restrictions.add(p5);
        }

        if (priceFrom != null) {
            Predicate p6 = cb.ge(root.get("price"), priceFrom);
            restrictions.add(p6);
        }

        if (priceTo != null) {
            Predicate p7 = cb.le(root.get("price"), priceTo);
            restrictions.add(p7);
        }

        if (color != null) {
            Predicate p8 = cb.equal(root.get("color"), color);
            restrictions.add(p8);
        }

        Predicate[] restrictionsAsArray = restrictions.toArray(new Predicate[0]);

        query.where(cb.and(restrictionsAsArray));
        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }

    public CarEntity add(CarEntity car) {
        entityManager.persist(car);
        return car;
    }

    public CarEntity update(CarEntity car) {
        entityManager.merge(car);
        return car;
    }

    public void delete(Long id) {
        CarEntity car = referenceById(id);
        entityManager.remove(car);
    }

}
