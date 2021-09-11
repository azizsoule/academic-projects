package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.swing.*;
import java.util.Set;

@Table(name = "privilege")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_privilege", nullable = false)
    private Long idPrivilege;

    @Column(length = 50)
    private String label;

    private String description;

    @ManyToMany(mappedBy = "privileges", fetch = FetchType.EAGER)
    private Set<UserGroup> groups;

    public Privilege(String label, String description){
        this.label=label;
        this.description=description;
    }

}