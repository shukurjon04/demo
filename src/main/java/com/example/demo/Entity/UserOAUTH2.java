package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;
@Getter
@Setter
@ToString
@Entity
public class UserOAUTH2 {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email;
    private String lastname;
    private boolean active;
    private String ImageUrl;
}
