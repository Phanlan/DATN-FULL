package myproject.project.service;

import myproject.project.model.request.UsedElectricWaterRequest;
import myproject.project.model.response.UsedElectricWaterResponse;

import java.util.List;

public interface UsedEletricWaterService {
    UsedElectricWaterResponse save(UsedElectricWaterRequest request);

    List<UsedElectricWaterResponse> getAllUsedElectricWater();
}
