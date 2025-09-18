package eci.edu.co.jpa_ws_arep.services.implementations;

import eci.edu.co.jpa_ws_arep.models.dtos.PropertyInDTO;
import eci.edu.co.jpa_ws_arep.models.dtos.PropertyOutDTO;
import eci.edu.co.jpa_ws_arep.models.entities.PropertyEntity;
import eci.edu.co.jpa_ws_arep.models.types.PropertyFilters;
import eci.edu.co.jpa_ws_arep.repositories.PropertyRepository;
import eci.edu.co.jpa_ws_arep.services.interfaces.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    @Override
    public PropertyOutDTO getPropertyOutDTO(String id) {
        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(id);
        if (propertyEntity.isPresent()) {
            PropertyEntity property = propertyEntity.get();
            return new PropertyOutDTO(property);
        }
        return null;
    }

    @Override
    public Page<PropertyOutDTO> getPropertyOutDTOs(Integer page, Integer size, PropertyFilters propertyFilter) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").descending());
        Page<PropertyEntity> propertyEntities = propertyRepository.findByFilters(
                propertyFilter,
                pageable
        );
        return propertyEntities.map(PropertyOutDTO::new);
    }

    @Override
    public PropertyOutDTO save(PropertyInDTO propertyInDTO) {
        PropertyEntity propertyEntity = new PropertyEntity(propertyInDTO);
        PropertyEntity result = propertyRepository.save(propertyEntity);
        return new PropertyOutDTO(result);
    }

    @Override
    public void delete(String id) {
        this.propertyRepository.deleteById(id);
    }

    @Override
    public PropertyEntity update(String id, PropertyInDTO propertyInDTO) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(id);
        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity propertyEntity = optionalPropertyEntity.get();
            propertyEntity.updateFromDTO(propertyInDTO);
            return propertyRepository.save(propertyEntity);
        }
        return null;
    }
}
