package eci.edu.co.jpa_ws_arep.models.dtos;

import eci.edu.co.jpa_ws_arep.models.types.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PropertyInDTO {
    private String address;
    private String city;
    private Boolean active;
    private Double price;
    private String description;
    private String name;
    private Integer rooms;
    private Integer bathrooms;
    private Integer parkingLots;
    private Double areaSquareMeters;
    private PropertyType type;
    private String owner;
}
