package myproject.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "used_electric_water")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseModelListener.class)

public class UsedElectricWater extends BaseModel {

    @Column(name = "water_number")
    private Long waterNumber;

    @Column(name = "electric_number")
    private Long electricNumber;

    @Column(name = "month")
    private Timestamp month;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;

}
