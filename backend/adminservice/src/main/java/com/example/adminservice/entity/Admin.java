package com.example.adminservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "admin")
public class Admin {
    @Id
    private String id;
    private String emailId;
    private String name;
    private String password;
    private String role;
}
