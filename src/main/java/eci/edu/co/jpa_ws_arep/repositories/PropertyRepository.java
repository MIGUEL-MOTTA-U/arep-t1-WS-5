package eci.edu.co.jpa_ws_arep.repositories;

import eci.edu.co.jpa_ws_arep.models.entities.PropertyEntity;
import eci.edu.co.jpa_ws_arep.models.types.PropertyFilters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PropertyRepository extends CrudRepository<PropertyEntity,String>, PagingAndSortingRepository<PropertyEntity,String> {

    @Query("SELECT p FROM PropertyEntity p WHERE " +
            "(:#{#filters.address} IS NULL OR p.address LIKE %:#{#filters.address}%) AND " +
            "(:#{#filters.city} IS NULL OR p.city LIKE %:#{#filters.city}%) AND " +
            "(:#{#filters.minPrice} IS NULL OR p.price >= :#{#filters.minPrice}) AND " +
            "(:#{#filters.maxPrice} IS NULL OR p.price <= :#{#filters.maxPrice}) AND " +
            "(:#{#filters.type} IS NULL OR p.type = :#{#filters.type}) AND " +
            "(:#{#filters.name} IS NULL OR p.name LIKE %:#{#filters.name}%) AND " +
            "(:#{#filters.minAreaSquareMeters} IS NULL OR p.areaSquareMeters >= :#{#filters.minAreaSquareMeters}) AND " +
            "(:#{#filters.maxAreaSquareMeters} IS NULL OR p.areaSquareMeters <= :#{#filters.maxAreaSquareMeters})")
    Page<PropertyEntity> findByFilters(@Param("filters") PropertyFilters filters, Pageable pageable);

}
