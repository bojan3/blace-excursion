package com.blace.excursion.util;

import com.blace.excursion.dto.excursion.ExcursionFilter;
import com.blace.excursion.model.Excursion;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AvailableExcursionSpecification implements Specification<Excursion>{

    private final ExcursionFilter excursionFilter;

    @Override
    public Predicate toPredicate(Root<Excursion> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>(5);

        predicates.add(criteriaBuilder.equal(root.get("approved"), true));
        predicates.add(criteriaBuilder.equal(root.get("cancelled"), false));

        if (excursionFilter.getMinimumTicketPrice() != null)
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), excursionFilter.getMinimumTicketPrice()));

        if (excursionFilter.getMaximumTicketPrice() != null)
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), excursionFilter.getMaximumTicketPrice()));

        if (excursionFilter.getMinimumTickets() != null)
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.diff(root.get("reservedTicketsNum"), root.get("maxNumberOfPersons")),
                    excursionFilter.getMinimumTickets()
            ));

        if (excursionFilter.getFromDate() != null)
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("date"), excursionFilter.getFromDate()));

        if (excursionFilter.getToDate() != null)
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("date"), excursionFilter.getToDate()));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
