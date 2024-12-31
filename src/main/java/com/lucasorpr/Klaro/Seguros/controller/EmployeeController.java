package com.lucasorpr.Klaro.Seguros.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasorpr.Klaro.Seguros.entities.Employee;
import com.lucasorpr.Klaro.Seguros.repository.EmployeeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/funcionario")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/cadastrar")
    public Employee creatEmployee(@RequestBody Employee employee) {

        return employeeRepository.save(employee);
    }

    @GetMapping("/inicio")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PutMapping("alterar/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));

        employee.setName(employeeDetails.getName());
        employee.setStart(employeeDetails.getStart());
        employee.setFinish(employeeDetails.getFinish());
        employee.setWorkHours(employeeDetails.getWorkHours());

        return employeeRepository.save(employee);

    }

    @DeleteMapping("excluir/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeRepository.deleteById(id);
    }
}
