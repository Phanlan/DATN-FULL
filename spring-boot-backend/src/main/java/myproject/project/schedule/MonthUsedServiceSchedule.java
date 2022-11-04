package myproject.project.schedule;

import lombok.AllArgsConstructor;
import myproject.project.service.MonthUsedServiceBusinessService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MonthUsedServiceSchedule {
    private final MonthUsedServiceBusinessService service;

    @Async
    @Scheduled(cron = "0 0 0 1 * ? *")
    public void updateMonthUsedService() {
        service.updateServicePerMonth();
    }
}
