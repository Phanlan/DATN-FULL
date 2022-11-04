package myproject.project.service.implement;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import myproject.project.entity.UsedService;
import myproject.project.model.request.UsedServiceRequest;
import myproject.project.entity.MonthUsedService;
import myproject.project.mapper.UsedServiceMapper;
import myproject.project.model.response.UsedServiceResponse;
import myproject.project.repository.MonthUsedServiceRepository;
import myproject.project.repository.UsedServiceRepository;
import myproject.project.service.UsedServiceBusinessService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class UsedServiceBusinessServiceImpl implements UsedServiceBusinessService {

    private final UsedServiceRepository repository;
    private final MonthUsedServiceRepository monthUsedServiceRepository;
    private final UsedServiceMapper mapper;

    @Override
    public UsedServiceResponse save(UsedServiceRequest request) {
        UsedService usedService = repository.saveAndFlush(mapper.to(request));
        MonthUsedService monthUsedService = new MonthUsedService();
        Date toDate = Date.valueOf(LocalDate.now().withDayOfMonth(1));
        monthUsedService.setFromDate(new Timestamp(System.currentTimeMillis()));
        monthUsedService.setUsedService(usedService);
        monthUsedService.setToDate(new Timestamp(DateUtils.addMonths(toDate,1).getTime()));
        monthUsedServiceRepository.saveAndFlush(monthUsedService);
        return mapper.to(usedService);
    }

    @Override
    @Transactional
    public String deleteByCompanyIdAndServiceId(Long companyId, Long serviceId) {
        UsedService usedService = repository.findByIsDeletedAndCompanyIdAndServiceId(false,companyId, serviceId);
        usedService.setIsDeleted(true);
        repository.saveAndFlush(usedService);
        return "Deleted";
    }

    @Override
    public List<UsedServiceResponse> findUsedServiceMonthByCompany(Long companyId, Long month) {
        List<UsedService> usedServiceList = repository.findUsedServiceMonthByCompanyId(companyId,month);
        return usedServiceList.stream().map(mapper::to).collect(Collectors.toList());
    }
}
