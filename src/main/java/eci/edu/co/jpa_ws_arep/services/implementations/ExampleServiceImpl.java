package eci.edu.co.jpa_ws_arep.services.implementations;

import eci.edu.co.jpa_ws_arep.models.ExampleModel;
import eci.edu.co.jpa_ws_arep.repositories.ExampleRepository;
import eci.edu.co.jpa_ws_arep.services.interfaces.ExampleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExampleServiceImpl implements ExampleService {

    private final ExampleRepository exampleRepository;

    @Override
    public Page<ExampleModel> getExamples(Integer page, Integer size) {
        return exampleRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
    }

    @Override
    public ExampleModel createExample(ExampleModel exampleModel) {
        return exampleRepository.save(exampleModel);
    }
}
