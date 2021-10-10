package company.service;

import company.entity.Address;
import company.payload.ApiResponse;
import company.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public HttpEntity<?> save(Address address) {
        addressRepository.save(address);
        return ResponseEntity.status(200).body(new ApiResponse("Succeed!",true));
    }

    public HttpEntity<?> edit(Address addressReq, Integer id) {

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse("Not found",false));
        Address address = optionalAddress.get();

        if (addressReq.getHomeNumber() != null) address.setHomeNumber(addressReq.getHomeNumber());
        if (!addressReq.getStreet().isEmpty()) address.setStreet(address.getStreet());

        return ResponseEntity.status(200).body(new ApiResponse("Succeed!",true));
    }

    public HttpEntity<?> delete(Integer id) {
        if (!addressRepository.existsById(id)) return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse("Not found",false));
        addressRepository.deleteById(id);
        return ResponseEntity.status(200).body(new ApiResponse("Deleted!",true));
    }
}
