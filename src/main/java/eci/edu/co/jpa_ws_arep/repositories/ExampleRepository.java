package eci.edu.co.jpa_ws_arep.repositories;

import eci.edu.co.jpa_ws_arep.models.ExampleModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ExampleRepository extends CrudRepository<ExampleModel,Integer>, PagingAndSortingRepository<ExampleModel,Integer> {
}
