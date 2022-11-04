package myproject.project.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseModelListener.class)
public class Vehicle extends BaseModel {

    @Column(name = "license_plate", length = 250)
    private String licensePlate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id" )
    private Company company;

    @OneToOne
    @JoinColumn(name = "company_employee_id")
    private CompanyEmployee companyEmployee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicleType_id" , referencedColumnName = "id")
    private VehicleType vehicleType;
}
