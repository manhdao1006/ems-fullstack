package net.javaguides.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDTO;
import net.javaguides.ems.entity.EmployeeEntity;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.IEmployeeService;

@Service
@AllArgsConstructor
public class EmployeeService implements IEmployeeService{

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        EmployeeEntity employeeEntity = EmployeeMapper.mapToEmployeeEntity(employeeDTO);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);

        return EmployeeMapper.mapToEmployeeDTO(savedEmployeeEntity);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                                                            .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id : " + employeeId));
        return EmployeeMapper.mapToEmployeeDTO(employeeEntity);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

        return employeeEntities.stream().map((employee) -> EmployeeMapper.mapToEmployeeDTO(employee))
                                        .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO updatedEmployee) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                                                            .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id : " + employeeId));
        
        employeeEntity.setFirstName(updatedEmployee.getFirstName());
        employeeEntity.setLastName(updatedEmployee.getLastName());
        employeeEntity.setEmail(updatedEmployee.getEmail());

        EmployeeEntity updatedEmployeeObj = employeeRepository.save(employeeEntity);
        
        return EmployeeMapper.mapToEmployeeDTO(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                                                            .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id : " + employeeId));
        
        employeeRepository.deleteById(employeeId);        
    }   
}
