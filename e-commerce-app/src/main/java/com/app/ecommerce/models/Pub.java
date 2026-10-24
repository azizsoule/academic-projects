package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "pub")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Pub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPub;

    String label;

    String image;

    boolean forSlider;

}