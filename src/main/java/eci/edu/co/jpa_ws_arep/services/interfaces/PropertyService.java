package eci.edu.co.jpa_ws_arep.services.interfaces;

import eci.edu.co.jpa_ws_arep.models.dtos.PropertyInDTO;
import eci.edu.co.jpa_ws_arep.models.dtos.PropertyOutDTO;
import eci.edu.co.jpa_ws_arep.models.entities.PropertyEntity;
import eci.edu.co.jpa_ws_arep.models.types.PropertyFilters;
import org.springframework.data.domain.Page;

public interface PropertyService {
    PropertyOutDTO getPropertyOutDTO(String id);
    Page<PropertyOutDTO> getPropertyOutDTOs(Integer page, Integer size, PropertyFilters propertyFilter);
    PropertyOutDTO save(PropertyInDTO propertyInDTO);
    void delete(String id);
    PropertyEntity update(String id, PropertyInDTO propertyInDTO);
}
