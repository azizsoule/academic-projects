package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "catalog")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCatalog;

    @Column(length = 30)
    private String label;

    private String image;

    @OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL)
    private Set<Category> categories;

}