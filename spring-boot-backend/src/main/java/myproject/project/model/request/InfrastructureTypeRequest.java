package myproject.project.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class InfrastructureTypeRequest {
    @NotNull
    private String name;
}
