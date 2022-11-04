package myproject.project.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CleanedRequest extends ServiceRequest{

    @NotNull
    private Integer timesPerWeek;

}
