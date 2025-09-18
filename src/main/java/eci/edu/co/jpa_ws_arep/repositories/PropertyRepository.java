package eci.edu.co.jpa_ws_arep.repositories;

import eci.edu.co.jpa_ws_arep.models.entities.PropertyEntity;
import eci.edu.co.jpa_ws_arep.models.types.PropertyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PropertyRepository extends CrudRepository<PropertyEntity,String>, PagingAndSortingRepository<PropertyEntity,String> {

    @Query(
        "SELECT p FROM PropertyEntity p WHERE " +
        "(p.address LIKE %:address% OR :address IS NULL) AND " +
        "(p.city LIKE %:city% OR :city IS NULL) AND " +
        "(p.price BETWEEN :minPrice AND :maxPrice OR :minPrice IS NULL OR :maxPrice IS NULL) AND " +
        "(p.type = :type OR :type IS NULL) AND " +
        "(p.name LIKE %:name% OR :name IS NULL) AND " +
        "(p.areaSquareMeters BETWEEN :minAreaSquareMeters AND :maxAreaSquareMeters OR :minAreaSquareMeters IS NULL OR :maxAreaSquareMeters IS NULL)")
    Page<PropertyEntity> findByFilters(String address, String city,
                                       Double minPrice, Double maxPrice,
                                       PropertyType type, Double minAreaSquareMeters,
                                        Double maxAreaSquareMeters,
                                       String name, Pageable pageable);
}
