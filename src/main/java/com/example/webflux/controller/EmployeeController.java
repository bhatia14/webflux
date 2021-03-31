package com.example.webflux.controller;

import com.example.webflux.model.Employee;
import com.example.webflux.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController("/rest/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/all")
    public Flux<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

}
