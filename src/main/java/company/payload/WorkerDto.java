package company.payload;

import company.entity.Address;
import company.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {
    private String name, phoneNumber;
    private Integer departmentId;
    private Integer addressId;

}