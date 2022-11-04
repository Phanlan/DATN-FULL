package myproject.project.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "infrastructure")
@Getter
@Setter
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseModelListener.class)
public class Infrastructure extends BaseModel {
    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "type", length = 250)
    private String type;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_amount")
    private Float unitAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "infrastructureType_id" , referencedColumnName = "id")
    private InfrastructureType infrastructureType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "infrastructure")
    private List<UsedInfrastructure> usedInfrastructureList;

}
