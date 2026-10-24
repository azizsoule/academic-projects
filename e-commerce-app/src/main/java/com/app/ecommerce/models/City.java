package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "city")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCity;

    private String label;

    @ManyToOne
    @JoinColumn(name = "region_id_region")
    private Region region;

    @OneToMany(mappedBy = "city")
    private Set<Address> addresses;

}