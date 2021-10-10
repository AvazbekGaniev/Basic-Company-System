package company.payload;


import company.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private String corpName, directorName;
    private Integer addressId;

}
