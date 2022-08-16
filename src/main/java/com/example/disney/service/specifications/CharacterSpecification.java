package com.example.disney.service.specifications;

import com.example.disney.dto.CharacterFiltersDto;
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
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification {
    public Specification<Character> getByFilters(CharacterFiltersDto characterFiltersDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(characterFiltersDto.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + characterFiltersDto.getName().toLowerCase() + "%"
                        )
                );
            }
            if (!ObjectUtils.isEmpty(characterFiltersDto.getAge())) {
                Integer age = characterFiltersDto.getAge();
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), age)
                );
            }
            if (!ObjectUtils.isEmpty(characterFiltersDto.getIdMovie())) {
                Join<Movie, Character> join = root.join("movies", JoinType.INNER);
                Expression<String> idMovie = join.get("id");
                predicates.add(idMovie.in(characterFiltersDto.getIdMovie()));
            }
            //Remove duplicates
            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
