package company.controller;

import company.entity.Department;
import company.entity.Worker;
import company.payload.ApiResponse;
import company.payload.WorkerDto;
import company.repository.AddressRepository;
import company.repository.DepartmentRepository;
import company.repository.WorkerRepository;
import company.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    WorkerService workerService;

    @PostMapping
    public HttpEntity<?> save(@RequestBody WorkerDto workerReq){
       return workerService.save(workerReq);
    }
    @GetMapping
    public HttpEntity<?> getList(){
        return ResponseEntity.ok(new ApiResponse("Succeed!",true,workerRepository.findAll()));
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody WorkerDto workerReq, @PathVariable Integer id){
        return workerService.edit(workerReq,id);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        return workerService.delete(id);
    }
}
