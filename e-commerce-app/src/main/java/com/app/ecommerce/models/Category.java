package com.app.ecommerce.models;

import com.app.ecommerce.utils.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "category")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @Column(length = 30)
    private String label;

    private String image;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<SubCategory> subCategories;

    @ManyToOne
    @JoinColumn(name = "catalog_id_catalog")
    private Catalog catalog;

    @Transient
    public String getImagePath() {
        return Constants.MEDIA_SERVER_ENDPOINT + "/categories/" + image;
    }

}