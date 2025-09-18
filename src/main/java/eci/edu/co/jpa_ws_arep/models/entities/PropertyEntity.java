package eci.edu.co.jpa_ws_arep.models.entities;

import eci.edu.co.jpa_ws_arep.models.dtos.PropertyInDTO;
import eci.edu.co.jpa_ws_arep.models.types.PropertyType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NonNull
    private String name;
    @NonNull
    private PropertyType type;
    @NonNull
    private String address;
    @NonNull
    private String city;
    @NonNull
    private Boolean active;
    @NonNull
    private Double price;
    @NonNull
    private String description;
    @NonNull
    private Integer rooms;
    @NonNull
    private Integer bathrooms;
    @NonNull
    private Integer parkingLots;
    @NonNull
    private Double areaSquareMeters;
    @NonNull
    private String owner;
    // @ManyToOne
    // @JoinColumn(name = "realStateId", nullable = false)
    // @NonNull
    // private RealState realState;
    @NonNull
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public PropertyEntity(PropertyInDTO propertyInDTO) {
        this.name = propertyInDTO.getName();
        this.type = propertyInDTO.getType();
        this.address = propertyInDTO.getAddress();
        this.city = propertyInDTO.getCity();
        this.active = propertyInDTO.getActive();
        this.price = propertyInDTO.getPrice();
        this.description = propertyInDTO.getDescription();
        this.rooms = propertyInDTO.getRooms();
        this.bathrooms = propertyInDTO.getBathrooms();
        this.parkingLots = propertyInDTO.getParkingLots();
        this.areaSquareMeters = propertyInDTO.getAreaSquareMeters();
        this.owner = propertyInDTO.getOwner();
    }

    public void updateFromDTO(PropertyInDTO propertyInDTO) {
        this.name = propertyInDTO.getName();
        this.type = propertyInDTO.getType();
        this.address = propertyInDTO.getAddress();
        this.city = propertyInDTO.getCity();
        this.active = propertyInDTO.getActive();
        this.price = propertyInDTO.getPrice();
        this.description = propertyInDTO.getDescription();
        this.rooms = propertyInDTO.getRooms();
        this.bathrooms = propertyInDTO.getBathrooms();
        this.parkingLots = propertyInDTO.getParkingLots();
        this.areaSquareMeters = propertyInDTO.getAreaSquareMeters();
        this.owner = propertyInDTO.getOwner();
    }
}
