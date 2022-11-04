package myproject.project.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicle_type")
@Getter
@Setter
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseModelListener.class)
public class VehicleType extends BaseModel {
    @Column(name = "description", length = 250)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicleType")
    private List<Vehicle> vehicleList;
}
