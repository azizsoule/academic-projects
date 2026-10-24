package com.app.ecommerce.models.supers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-mm-dd", fallbackPatterns = "yyyy/mm/dd")
    private Date birthDate;

    private String email;

    private String password;

    private Date createdAt;

    private boolean blocked = false;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

}
