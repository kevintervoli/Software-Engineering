package com.patagonia.web.search;

import com.patagonia.web.enums.SearchOperation;
import com.patagonia.web.filter.search.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GeneralSpecification<T> implements Specification<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3810596400215022908L;
	private List<SearchCriteria> list;

	public GeneralSpecification(List<SearchCriteria> list) {
		this.list = list;
	}

	public void setList(List<SearchCriteria> list) {
		this.list = list;
	}

	public void add(SearchCriteria criteria) {
		list.add(criteria);
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		List<Predicate> predicates = new ArrayList<>();

		for (SearchCriteria criteria : list) {

			if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
				predicates.add(builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
			} else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
				predicates.add(builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
			} else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
				predicates
						.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
			} else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
				predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
			} else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
				predicates.add(builder.notEqual(root.get(criteria.getKey()), criteria.getValue()));
			} else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
				predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
			} else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
				predicates.add(builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
			} else if (criteria.getOperation().equals(SearchOperation.INNER_OBJECT_CODE)) {
				Path<T> app = root.get(criteria.getInnerObject());
				predicates.add(builder.equal(app.get(criteria.getKey()), criteria.getValue().toString()));
			} else if (criteria.getOperation().equals(SearchOperation.INNER_OBJECT_CODE_NOT_EQUAL)) {
				Path<T> app = root.get(criteria.getInnerObject());
				predicates.add(builder.notEqual(app.get(criteria.getKey()), criteria.getValue().toString()));
			} else if (criteria.getOperation().equals(SearchOperation.INNER_OBJECT_CONTAINS)) {
				Path<T> app = root.get(criteria.getInnerObject());
				predicates.add(builder.like(builder.upper(app.get(criteria.getKey())),
						"%" + criteria.getValue().toString().toUpperCase() + "%"));
			} else if (criteria.getOperation().equals(SearchOperation.EQUAL_DATE)) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String date = criteria.getValue().toString();
				LocalDate localDate = LocalDate.parse(date, formatter);
				predicates.add(builder.equal(root.get(criteria.getKey()), localDate));
			} else if (criteria.getOperation().equals(SearchOperation.MATCH_START_DATE)) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String date = criteria.getValue().toString();
				LocalDate localDate = LocalDate.parse(date, formatter);

				predicates.add(builder.greaterThanOrEqualTo(
						builder.function("DAY", Integer.class, root.get(criteria.getKey())),
						localDate.getDayOfMonth()));

				predicates.add(builder.greaterThanOrEqualTo(
						builder.function("MONTH", Integer.class, root.get(criteria.getKey())),
						localDate.getMonthValue()));

			} else if (criteria.getOperation().equals(SearchOperation.MATCH_END_DATE)) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String date = criteria.getValue().toString();
				LocalDate localDate = LocalDate.parse(date, formatter);

				predicates.add(
						builder.lessThanOrEqualTo(builder.function("DAY", Integer.class, root.get(criteria.getKey())),
								localDate.getDayOfMonth()));

				predicates.add(
						builder.lessThanOrEqualTo(builder.function("MONTH", Integer.class, root.get(criteria.getKey())),
								localDate.getMonthValue()));

			} else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
				predicates.add(builder.like(builder.lower(root.get(criteria.getKey())),
						"%" + criteria.getValue().toString().toLowerCase() + "%"));
			} else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
				predicates.add(builder.like(builder.lower(root.get(criteria.getKey()).as(String.class)),
						"%" + criteria.getValue().toString().toLowerCase()));
			} else if (criteria.getOperation().equals(SearchOperation.MATCH_START)) {
				predicates.add(builder.like(builder.lower(root.get(criteria.getKey()).as(String.class)),
						criteria.getValue().toString().toLowerCase() + "%"));
			} else if (criteria.getOperation().equals(SearchOperation.IN)) {
				predicates.add(builder.in(root.get(criteria.getKey())).value(criteria.getValue()));
			} else if (criteria.getOperation().equals(SearchOperation.NOT_IN)) {
				predicates.add(builder.not(root.get(criteria.getKey())).in(criteria.getValue()));
			}
		}

		return builder.and(predicates.toArray(new Predicate[0]));
	}
}
