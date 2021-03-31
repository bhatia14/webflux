package com.example.webflux.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Document
public class Employee {

    private String id;
    private String name;
    private Long salary;
}
