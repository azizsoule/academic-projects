package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "user_group")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserGroup;

    private String label;

    @DateTimeFormat(pattern = "yyyy-mm-dd", fallbackPatterns = "yyyy/mm/dd")
    private Date createdAt;

    @OneToMany(mappedBy = "userGroup")
    private Set<User> users;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

}