package net.javaguides.ems.mapper;

import net.javaguides.ems.dto.EmployeeDTO;
import net.javaguides.ems.entity.EmployeeEntity;

public class EmployeeMapper {
    public static EmployeeDTO mapToEmployeeDTO(EmployeeEntity employeeEntity){
        return new EmployeeDTO(
            employeeEntity.getId(),
            employeeEntity.getFirstName(),
            employeeEntity.getLastName(),
            employeeEntity.getEmail()
        );
    }

    public static EmployeeEntity mapToEmployeeEntity(EmployeeDTO employeeDTO){
        return new EmployeeEntity(
            employeeDTO.getId(),
            employeeDTO.getFirstName(),
            employeeDTO.getLastName(),
            employeeDTO.getEmail()
        );
    }
}
