package eci.edu.co.jpa_ws_arep.models.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyFilters {
    private Double minPrice;
    private Double maxPrice;
    private String city;
    private String name;
    private String address;
    private Double minAreaSquareMeters;
    private Double maxAreaSquareMeters;
    private PropertyType type;
}
