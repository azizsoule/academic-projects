package com.app.ecommerce.models.supers;

import com.app.ecommerce.models.Sex;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Date birthDate;

    private String email;

    private String password;

    private Date createdAt;

    private boolean blocked=false;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

}
