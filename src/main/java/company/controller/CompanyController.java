package company.controller;

import company.payload.ApiResponse;
import company.payload.CompanyDto;
import company.payload.WorkerDto;
import company.repository.AddressRepository;
import company.repository.CompanyRepository;
import company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CompanyService companyService;

    @PostMapping
    public HttpEntity<?> save(@RequestBody CompanyDto companyDto){
        return companyService.save(companyDto);
    }
    @GetMapping
    public HttpEntity<?> getList(){
        return ResponseEntity.ok(new ApiResponse("Succeed!",true,companyRepository.findAll()));
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody CompanyDto companyDto, @PathVariable Integer id){
        return companyService.edit(companyDto,id);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        return companyService.delete(id);
    }

}
