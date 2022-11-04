package myproject.project.service.implement;

import myproject.project.model.request.MonthRequest;
import myproject.project.entity.BuildingEmployee;
import myproject.project.mapper.BuildingEmployeeMapper;
import myproject.project.mapper.MonthSalaryMapper;
import myproject.project.model.response.BuildingEmployeeResponse;
import myproject.project.repository.BuildingEmployeeRepository;
import myproject.project.repository.MonthSalaryRepository;
import myproject.project.service.MonthSalaryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonthSalaryServiceImpl implements MonthSalaryService {

    private final MonthSalaryRepository monthSalaryRepository;
    private final MonthSalaryMapper monthSalaryMappermapper;

    private final BuildingEmployeeRepository repository;
    private final BuildingEmployeeMapper mapper;

    public MonthSalaryServiceImpl(MonthSalaryRepository monthSalaryRepository, MonthSalaryMapper mapper, BuildingEmployeeRepository repository, BuildingEmployeeMapper mapper1) {
        this.monthSalaryRepository = monthSalaryRepository;
        this.monthSalaryMappermapper = mapper;
        this.repository = repository;
        this.mapper = mapper1;
    }

    @Override
    public List<BuildingEmployeeResponse> getAllMonthSalaryPrecent(boolean isDeleted) {
        List<BuildingEmployee> list = repository.findAllByIsDeleted(false);
        return list.stream().map(mapper::to).collect(Collectors.toList());
    }

    @Override
    public List<BuildingEmployeeResponse> getMonthSalaryByMonth(MonthRequest request) {
        List<BuildingEmployee> list = repository.findAllByIsDeleted(false);
        List<BuildingEmployeeResponse> listResponse = new ArrayList<>();
        for ( BuildingEmployee b : list ) {
            listResponse.add(mapper.toByMonth(b,request));
        }
        List<BuildingEmployeeResponse> ans = new ArrayList<>();
        for ( BuildingEmployeeResponse b : listResponse ) {
            if ( b != null )
                ans.add(b);
        }
        return ans;
    }
}
