package myproject.project.controller;

import lombok.AllArgsConstructor;
import myproject.project.model.request.SalaryRequest;
import myproject.project.service.implement.SalaryServiceImpl;
import myproject.project.model.response.BaseResponse;
import myproject.project.model.response.SalaryResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("public-api/v1.0.0/salary")
@AllArgsConstructor
public class SalaryController {
    private final SalaryServiceImpl salaryService;

    @GetMapping("{id}")
    BaseResponse<SalaryResponse> getSalaryById(@PathVariable Long id){
        return BaseResponse.ofSuccess(salaryService.findById(id));
    }

    @GetMapping("/list")
    BaseResponse<List<SalaryResponse>> getAllSalary(){
        return BaseResponse.ofSuccess(salaryService.getAllSalary());
    }

    @PostMapping("/create")
    BaseResponse<SalaryResponse> createBuildingEmployee(@RequestBody @Valid SalaryRequest salaryRequest){
        return BaseResponse.ofSuccess(salaryService.save(salaryRequest));
    }

    @PutMapping("/update/{id}")
    BaseResponse<SalaryResponse> updateBuildingEmployee(@PathVariable Long id, @RequestBody @Valid SalaryRequest request){
        return BaseResponse.ofSuccess(salaryService.save(id,request));
    }

    @DeleteMapping("/delete/{id}")
    BaseResponse<String> deleteSalaryById(@PathVariable Long id){
        return BaseResponse.ofSuccess(salaryService.deleteById(id));
    }

    @GetMapping("/search")
    BaseResponse<List<SalaryResponse>> getSalaryByPositionLike(@RequestParam String position){
        return BaseResponse.ofSuccess(salaryService.findSalaryByPositionLike(position));
    }

    @GetMapping("/position")
    BaseResponse<List<String>> getAllPosition() {
        return BaseResponse.ofSuccess(salaryService.findAllPosition());
    }

    @GetMapping("/level")
    BaseResponse<List<String>> getAllLevelByPosition(@RequestParam String position) {
        return BaseResponse.ofSuccess(salaryService.findLevelByPosition(position));
    }

}
