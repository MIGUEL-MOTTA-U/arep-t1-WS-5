package eci.edu.co.jpa_ws_arep.controllers;

import eci.edu.co.jpa_ws_arep.models.ExampleModel;
import eci.edu.co.jpa_ws_arep.services.interfaces.ExampleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ExampleController {

    private final ExampleService exampleService;


    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/examples")
    public ResponseEntity<ExampleModel> createExample() {
        ExampleModel exampleModel;
        exampleModel = new ExampleModel("Example description", 42);
        return ResponseEntity.ok(exampleService.createExample(exampleModel));
    }

    @GetMapping("/examples")
    public ResponseEntity<Page<ExampleModel>> getExamples(@Param("page") Integer page, @Param("size") Integer size) {
        Page<ExampleModel> result = exampleService.getExamples(page, size);
        return ResponseEntity.ok(result);
    }
}
