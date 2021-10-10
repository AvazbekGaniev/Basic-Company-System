package company.controller;

import company.payload.ApiResponse;
import company.payload.CompanyDto;
import company.payload.DepartmentDto;
import company.payload.WorkerDto;
import company.repository.DepartmentRepository;
import company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentRepository departmentRepository;

    @PostMapping
    public HttpEntity<?> save(@RequestBody DepartmentDto departmentReq){
        return departmentService.save(departmentReq);
    }

    @GetMapping
    public HttpEntity<?> getList(){
        return ResponseEntity.ok(new ApiResponse("Succeed!",true,departmentRepository.findAll()));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody DepartmentDto departmentReq, @PathVariable Integer id){
        return departmentService.edit(departmentReq,id);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        return departmentService.delete(id);
    }
}
