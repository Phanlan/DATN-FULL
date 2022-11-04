package myproject.project.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseModelListener.class)
public class Company extends BaseModel {

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "tax_code", length = 250)
    private String taxCode;

    @Column(name = "authorized_capital")
    private Float authorizedCapital;

    @Column(name = "field_of_activity", length = 250)
    private String fieldOfActivity;

    @Column(name = "floor", length = 250)
    private String floor;

    @Column(name = "hotline", length = 250)
    private String hotline;

    @Column(name = "area")
    private Float area;

    @Column(name = "number_of_employee")
    private Integer numberOfEmployee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<CompanyEmployee> companyEmployeeList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "company")
    private List<UsedService> usedServiceList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<UsedElectricWater> usedElectricWaterList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "company")
    private List<UsedInfrastructure> usedInfrastructureList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Invoice> invoiceList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "company")
    private List<Vehicle> vehicleList;

}
