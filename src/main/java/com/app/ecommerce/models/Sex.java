package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "sex")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSex;

    private String label;

    @OneToMany(mappedBy = "sex")
    private List<Customer> customers;

    @OneToMany(mappedBy = "sex")
    private List<User> users;

}