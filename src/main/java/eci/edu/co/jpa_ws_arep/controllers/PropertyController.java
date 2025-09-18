package eci.edu.co.jpa_ws_arep.controllers;

import eci.edu.co.jpa_ws_arep.models.dtos.PropertyInDTO;
import eci.edu.co.jpa_ws_arep.models.dtos.PropertyOutDTO;
import eci.edu.co.jpa_ws_arep.models.types.PropertyFilters;
import eci.edu.co.jpa_ws_arep.models.types.PropertyType;
import eci.edu.co.jpa_ws_arep.services.interfaces.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/properties")
    public ResponseEntity<Page<PropertyOutDTO>> getAllProperties(
           @Param("minPrice") Double minPrice,
           @Param("maxPrice") Double maxPrice,
           @Param("city") String city,
           @Param("name") String name,
           @Param("address") String address,
           @Param("minAreaSquareMeters") Double minAreaSquareMeters,
           @Param("maxAreaSquareMeters") Double maxAreaSquareMeters,
           @Param("type") PropertyType type,
           @Param("page") Integer page,
           @Param("size") Integer size) {
        Page<PropertyOutDTO> result = propertyService.getPropertyOutDTOs(page, size, new PropertyFilters(minPrice, maxPrice, city, name, address, minAreaSquareMeters, maxAreaSquareMeters, type));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/properties/{id}")
    public ResponseEntity<PropertyOutDTO> getPropertyById(@PathVariable String id) {
        PropertyOutDTO propertyOutDTO = propertyService.getPropertyOutDTO(id);
        if (propertyOutDTO != null) {
            return ResponseEntity.ok(propertyOutDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/properties")
    public ResponseEntity<PropertyOutDTO> saveProperty(@RequestBody PropertyInDTO propertyInDTO) {
        return ResponseEntity.ok(this.propertyService.save(propertyInDTO));
    }

    @PutMapping("/properties/{id}")
    public ResponseEntity<PropertyOutDTO> updateProperty(@PathVariable String id, @RequestBody
    PropertyInDTO propertyInDTO) {
        PropertyOutDTO updatedProperty = propertyService.save(propertyInDTO);
        if (updatedProperty != null) {
            return ResponseEntity.ok(updatedProperty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/properties/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable String id) {
        propertyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
