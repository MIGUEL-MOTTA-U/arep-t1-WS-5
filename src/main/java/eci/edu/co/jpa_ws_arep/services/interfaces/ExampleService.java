package eci.edu.co.jpa_ws_arep.services.interfaces;

import eci.edu.co.jpa_ws_arep.models.ExampleModel;
import org.springframework.data.domain.Page;

public interface ExampleService {
    Page<ExampleModel> getExamples(Integer page, Integer size);
    ExampleModel createExample(ExampleModel exampleModel);
}
