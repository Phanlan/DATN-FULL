package myproject.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "infrastructure_type")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseModelListener.class)

public class InfrastructureType extends BaseModel{
    @Column(name = "description", length = 250)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "infrastructureType")
    private List<Infrastructure> infrastructure;
}
