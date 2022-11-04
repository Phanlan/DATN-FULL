package myproject.project.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "invoice")
@Getter
@Setter
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseModelListener.class)
public class Invoice extends BaseModel {
    @Column(name = "total")
    private Float total;

    @Column(name = "month")
    private Timestamp month;

    @Column(name = "invoice_date")
    private Timestamp invoiceDate;

    @Column(name = "service_price")
    private Float servicePrice;

    @Column(name = "water_price")
    private Float waterPrice;

    @Column(name = "electric_price")
    private Float electricPrice;

    @Column(name = "rental_price")
    private Float rentalPrice;



    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;
}
