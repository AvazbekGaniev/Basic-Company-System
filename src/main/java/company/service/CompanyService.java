package company.service;

import company.entity.Address;
import company.entity.Company;
import company.payload.ApiResponse;
import company.payload.CompanyDto;
import company.repository.AddressRepository;
import company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CompanyService companyService;

    public HttpEntity<?> save(CompanyDto companyDto) {
        Company company = new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse("Not found", false));
        company.setAddress(optionalAddress.get());
        companyRepository.save(company);
        return ResponseEntity.status(200).body(new ApiResponse("Succeed!", true));
    }

    public HttpEntity<?> edit(CompanyDto companyDto, Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse("Not found", false));
        Company company = optionalCompany.get();

        if (!companyDto.getCorpName().isEmpty()) company.setCorpName(companyDto.getCorpName());
        if (!companyDto.getDirectorName().isEmpty()) company.setDirectorName(companyDto.getDirectorName());
        if (companyDto.getAddressId() != null) {
            Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
            if (!optionalAddress.isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse("Not found", false));
            company.setAddress(optionalAddress.get());
        }
        companyRepository.save(company);
        return ResponseEntity.status(200).body(new ApiResponse("Succeed!", true));
    }

    public HttpEntity<?> delete(Integer id) {
        if (!companyRepository.existsById(id)) return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse("Not found",false));
        companyRepository.deleteById(id);
        return ResponseEntity.status(200).body(new ApiResponse("Succeed!",true));
    }
}
