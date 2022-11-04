package myproject.project.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class UserResponse {
    private Long id;

    @JsonProperty("full_name")
    private String fullName;

    private String email;

    @JsonProperty("phone")
    private String phoneNumber;

    private String address;

    @Column(name = "birthday")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date birthday;

    private String status;
}
