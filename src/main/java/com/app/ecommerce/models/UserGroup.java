package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Date createdAt;

    @OneToMany(mappedBy = "userGroup")
    private Set<User> users;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

}