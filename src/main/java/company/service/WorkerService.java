package company.service;

import company.entity.Address;
import company.entity.Department;
import company.entity.Worker;
import company.payload.ApiResponse;
import company.payload.WorkerDto;
import company.repository.AddressRepository;
import company.repository.DepartmentRepository;
import company.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    WorkerService workerService;

    public HttpEntity<?> save(WorkerDto workerReq) {
        Worker worker = new Worker();
        worker.setName(workerReq.getName());
        worker.setPhoneNumber(workerReq.getPhoneNumber());

        Optional<Department> departmentOptional = departmentRepository.findById(workerReq.getDepartmentId());
        if (!departmentOptional.isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse("Error",false));

        worker.setDepartment(departmentOptional.get());

        Optional<Address> optionalAddress = addressRepository.findById(workerReq.getAddressId());
        if (!optionalAddress.isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse("Error",false));

        workerRepository.save(worker);
        return ResponseEntity.status(200).body(new ApiResponse("Saved!",true));
    }

    public HttpEntity<?> edit(WorkerDto workerReq, Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse("Error",false));
        Worker worker = optionalWorker.get();
        if (!workerReq.getName().isEmpty())worker.setName(workerReq.getName());
        if (!workerReq.getPhoneNumber().isEmpty())worker.setPhoneNumber(workerReq.getPhoneNumber());

        if (workerReq.getDepartmentId() != null){
            Optional<Department> departmentOptional = departmentRepository.findById(workerReq.getDepartmentId());
            if (!departmentOptional.isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse("Error",false));
            worker.setDepartment(departmentOptional.get());
        }

        if (workerReq.getAddressId() != null){
            Optional<Address> optionalAddress = addressRepository.findById(workerReq.getAddressId());
            if (!optionalAddress.isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse("Error",false));
            worker.setAddress(optionalAddress.get());
        }
        workerRepository.save(worker);
        return ResponseEntity.status(200).body(new ApiResponse("Edited!",true));
    }

    public HttpEntity<?> delete(Integer id) {
        if (!workerRepository.existsById(id)) return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse("Not found",false));
        workerRepository.deleteById(id);
        return ResponseEntity.status(200).body(new ApiResponse("Succeed!",true));
    }
}
