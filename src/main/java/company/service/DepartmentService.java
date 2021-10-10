package company.service;

import company.entity.Company;
import company.entity.Department;
import company.payload.ApiResponse;
import company.payload.DepartmentDto;
import company.repository.CompanyRepository;
import company.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    public HttpEntity<?> save(DepartmentDto departmentReq) {
        Department department = new Department();
        department.setName(department.getName());
        Optional<Company> companyOptional = companyRepository.findById(departmentReq.getCompanyId());
        if (!companyOptional.isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse("Not found", false));
        department.setCompany(companyOptional.get());
        departmentRepository.save(department);
        return ResponseEntity.status(200).body(new ApiResponse("Succeed!", true));
    }

    public HttpEntity<?> edit(DepartmentDto departmentReq, Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse("Not found", false));
        Department department = optionalDepartment.get();
        if (!departmentReq.getName().isEmpty()) department.setName(department.getName());
        if (departmentReq.getCompanyId() != null) {
            Optional<Company> companyOptional = companyRepository.findById(departmentReq.getCompanyId());
            if (companyOptional.isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse("Not found", false));
            department.setCompany(companyOptional.get());
        }
        departmentRepository.save(department);
        return ResponseEntity.status(200).body(new ApiResponse("Succeed!", true));
    }

    public HttpEntity<?> delete(Integer id) {
        if (!companyRepository.existsById(id)) return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse("Not found",false));
        companyRepository.deleteById(id);
        return ResponseEntity.status(200).body(new ApiResponse("Succeed!",true));
    }
}
