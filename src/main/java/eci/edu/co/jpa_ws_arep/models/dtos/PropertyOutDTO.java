package eci.edu.co.jpa_ws_arep.models.dtos;

import eci.edu.co.jpa_ws_arep.models.entities.PropertyEntity;
import eci.edu.co.jpa_ws_arep.models.types.PropertyType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyOutDTO {
    private String id;
    private String type;
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
    private PropertyType propertyEntity;
    private String owner;

    public PropertyOutDTO(PropertyEntity propertyEntity) {
        this.id = propertyEntity.getId();
        this.type = propertyEntity.getType().toString();
        this.address = propertyEntity.getAddress();
        this.city = propertyEntity.getCity();
        this.active = propertyEntity.getActive();
        this.price = propertyEntity.getPrice();
        this.description = propertyEntity.getDescription();
        this.name = propertyEntity.getName();
        this.rooms = propertyEntity.getRooms();
        this.bathrooms = propertyEntity.getBathrooms();
        this.parkingLots = propertyEntity.getParkingLots();
        this.areaSquareMeters = propertyEntity.getAreaSquareMeters();
        this.propertyEntity = propertyEntity.getType();
        this.owner = propertyEntity.getOwner();
    }

}
