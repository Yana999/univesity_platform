package ru.abdramanova.university_platform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.abdramanova.university_platform.entity.ControlFormDict;

@Repository
public interface ControlFormDictRepository extends PagingAndSortingRepository<ControlFormDict, Short> {
}