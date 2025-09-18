package eci.edu.co.jpa_ws_arep.controllers;

import eci.edu.co.jpa_ws_arep.models.ExampleModel;
import eci.edu.co.jpa_ws_arep.services.interfaces.ExampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(ExampleController.class)
class ExampleControllerTest {

    @MockitoBean
    private ExampleService exampleService;
    private ExampleController exampleController;


    @BeforeEach
    void setUp() {
        exampleController = new ExampleController(exampleService);
    }

    @Test
    void hello() {
        String response = exampleController.hello().getBody();
        assert response != null;
        assertEquals("Hello World", response);
    }

    @Test
    void createExample() {
        ExampleModel exampleModel = new ExampleModel("Example description", 42);
        when(exampleService.createExample(exampleModel)).thenReturn(exampleModel);
        ExampleModel response = exampleController.createExample().getBody();
        assertNotNull(response);
        assertEquals("Example description", response.getDescription());
        assertEquals(42, response.getValue());
    }

    @Test
    void getExamples() {
        Page<ExampleModel> page = new PageImpl<>(List.of(new ExampleModel("Example description", 42)));
        when(exampleService.getExamples(0, 10)).thenReturn(page);
        Page<ExampleModel> response = exampleController.getExamples(0, 10).getBody();
        assertNotNull(response);
        assertEquals(response.getSize(), page.getSize());
        assertEquals("Example description",response.getContent().get(0).getDescription());
    }
}