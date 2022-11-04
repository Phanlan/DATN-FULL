package myproject.project.mapper;

import myproject.project.model.request.SalaryRequest;
import myproject.project.entity.Salary;
import myproject.project.model.response.SalaryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SalaryMapper implements Mapper<Salary>{

    public Salary to(SalaryRequest request) {
        Salary salary = new Salary();
        BeanUtils.copyProperties(request, salary);
        return salary;
    }

    public SalaryResponse to(Salary salary) {
        SalaryResponse response = new SalaryResponse();
        BeanUtils.copyProperties(salary, response);
        return response;
    }
}
