package myproject.project.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProtectedServiceRequest extends ServiceRequest{
    @NotNull
    private String any;
}
