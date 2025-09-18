package eci.edu.co.jpa_ws_arep.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class RealState {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String phone;
    // @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "realState")
    // private Set<PropertyEntity> properties;
}
