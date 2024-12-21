package com.empcrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.empcrud.entity.Employee;
import com.empcrud.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	@Autowired
	private final EmployeeRepository employeeRepository;
	
	
	public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
	
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	
	public void deleteEmployee(Long id) {
		if(!employeeRepository.existsById(id)) {
			throw new EntityNotFoundException("Employee with Id " + id + "not found");
		}
		employeeRepository.deleteById(id);
	}
	
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}
	
	public Employee updateEmployee(Long id, Employee employee) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if(optionalEmployee.isPresent()) {
			Employee existingEmployee = optionalEmployee.get();
			existingEmployee.setEmail(employee.getEmail());
			existingEmployee.setName(employee.getName());
			existingEmployee.setPhone(employee.getPhone());
			existingEmployee.setDepartment(employee.getDepartment());
			
			return employeeRepository.save(existingEmployee);
		}
		return null;
	}
}
