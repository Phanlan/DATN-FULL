package myproject.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "user")
public class User extends BaseModel {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

//    @ManyToOne
//    @JoinColumn(name = "role_id", nullable = false)
//    private Role role;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "birthday", columnDefinition = "date")
    private Date birthday;

    @Column(name = "status")
    private String status;

}
