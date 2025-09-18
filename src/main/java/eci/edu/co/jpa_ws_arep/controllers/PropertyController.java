package eci.edu.co.jpa_ws_arep.controllers;

import eci.edu.co.jpa_ws_arep.errors.ErrorCustomController;
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
           @Param("size") Integer size) throws ErrorCustomController {
        validateParams(page, size, minPrice, maxPrice, minAreaSquareMeters, maxAreaSquareMeters);
        Page<PropertyOutDTO> result = propertyService.getPropertyOutDTOs(page, size, new PropertyFilters(minPrice, maxPrice, city, name, address, minAreaSquareMeters, maxAreaSquareMeters, type));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/properties/{id}")
    public ResponseEntity<PropertyOutDTO> getPropertyById(@PathVariable String id) throws ErrorCustomController {
        PropertyOutDTO propertyOutDTO = propertyService.getPropertyOutDTO(id);
        if (propertyOutDTO == null) throw new ErrorCustomController("Property: " + id + " not found", ErrorCustomController.NOT_FOUND);
        return ResponseEntity.ok(propertyOutDTO);
    }

    @PostMapping("/properties")
    public ResponseEntity<PropertyOutDTO> saveProperty(@RequestBody PropertyInDTO propertyInDTO) throws ErrorCustomController {
        validatePropertyIn(propertyInDTO);
        return ResponseEntity.ok(this.propertyService.save(propertyInDTO));
    }

    @PutMapping("/properties/{id}")
    public ResponseEntity<PropertyOutDTO> updateProperty(@PathVariable String id, @RequestBody
    PropertyInDTO propertyInDTO) throws ErrorCustomController {
        PropertyOutDTO updatedProperty = propertyService.getPropertyOutDTO(id);
        if (updatedProperty == null) throw new ErrorCustomController("Property:" + id + " not found", ErrorCustomController.NOT_FOUND);
        propertyService.save(propertyInDTO);
        return ResponseEntity.ok(updatedProperty);
    }

    @DeleteMapping("/properties/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable String id) {
        propertyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private void validateParams(Integer page, Integer size, Double minPrice, Double maxPrice, Double minAreaSquareMeters, Double maxAreaSquareMeters) throws ErrorCustomController {
        if (page < 0) {
            throw new ErrorCustomController("Page number must be non-negative.", ErrorCustomController.BAD_REQUEST);
        }
        if (size <= 0) {
            throw new ErrorCustomController("Size must be positive.", ErrorCustomController.BAD_REQUEST);
        }
        if (minPrice != null && maxPrice != null && minPrice > maxPrice) {
            throw new ErrorCustomController("Minimum price cannot be greater than maximum price.", ErrorCustomController.BAD_REQUEST);
        }
        if (minAreaSquareMeters != null && maxAreaSquareMeters != null && minAreaSquareMeters > maxAreaSquareMeters) {
            throw new ErrorCustomController("Minimum area cannot be greater than maximum area.", ErrorCustomController.BAD_REQUEST);
        }
    }

    private void validatePropertyIn(PropertyInDTO propertyInDTO) throws ErrorCustomController {
        String message = "";
        message = invalidField(propertyInDTO) ? "A given field is not defined. The fields are: name, type, address, city, active, description, owner." : message;
        message = invalidNumber(propertyInDTO) ? message + "A given numeric field cannot be negative. The fields are: price, rooms, bathrooms, parkingLots, areaSquareMeters." : message;
        if (!message.isEmpty()) throw new ErrorCustomController(message, ErrorCustomController.BAD_REQUEST);
    }

    private Boolean invalidField(PropertyInDTO propertyInDTO) {
        return
            propertyInDTO.getName() == null || propertyInDTO.getName().isEmpty()
            || propertyInDTO.getType() == null || propertyInDTO.getAddress() == null
            || propertyInDTO.getAddress().isEmpty() || propertyInDTO.getCity() == null
            || propertyInDTO.getCity().isEmpty() || propertyInDTO.getActive() == null
            || propertyInDTO.getDescription() == null || propertyInDTO.getDescription().isEmpty()
            || propertyInDTO.getOwner() == null || propertyInDTO.getOwner().isEmpty();
    }

    private Boolean invalidNumber(PropertyInDTO propertyInDTO) {
        return
            propertyInDTO.getPrice() == null || propertyInDTO.getPrice() < 0
            || propertyInDTO.getRooms() == null || propertyInDTO.getRooms() < 0
            || propertyInDTO.getBathrooms() == null || propertyInDTO.getBathrooms() < 0
            || propertyInDTO.getParkingLots() == null || propertyInDTO.getParkingLots() < 0
            || propertyInDTO.getAreaSquareMeters() == null || propertyInDTO.getAreaSquareMeters() < 0;
    }
}
