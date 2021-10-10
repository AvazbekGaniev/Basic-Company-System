package company.controller;

import company.entity.Address;
import company.payload.ApiResponse;
import company.payload.CompanyDto;
import company.repository.AddressRepository;
import company.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressService addressService;

    @PostMapping
    public HttpEntity<?> save(@RequestBody Address address) {
        return addressService.save(address);
    }

    @GetMapping
    public HttpEntity<?> getList() {
        return ResponseEntity.ok(new ApiResponse("Succeed!", true, addressRepository.findAll()));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody Address address, @PathVariable Integer id) {
        return addressService.edit(address, id);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        return addressService.delete(id);
    }
}
