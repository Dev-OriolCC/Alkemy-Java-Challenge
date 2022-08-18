package com.example.disney.repository.specifications;

import com.example.disney.dto.CharacterFiltersDto;
import com.example.disney.dto.MovieFiltersDto;
import com.example.disney.entity.Character;
import com.example.disney.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {
    public Specification<Movie> getByFilters(MovieFiltersDto movieFiltersDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(movieFiltersDto.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + movieFiltersDto.getName().toLowerCase() + "%"
                        )
                );
            }

            if (!ObjectUtils.isEmpty(movieFiltersDto.getIdGenre())) {
                Integer idGenre = movieFiltersDto.getIdGenre();
                predicates.add(
                        criteriaBuilder.equal(root.get("idGenre"), idGenre)
                );
            }

            //Remove duplicates
            query.distinct(true);
            //
            String orderByField = "date_created";
            query.orderBy(
                    movieFiltersDto.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
