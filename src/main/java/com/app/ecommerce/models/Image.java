package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "image")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id_article")
    Article article;

}