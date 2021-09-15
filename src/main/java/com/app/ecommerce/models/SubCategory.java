package com.app.ecommerce.models;

import com.app.ecommerce.utils.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "sub_category")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubCategory;

    @Column(length = 30)
    private String label;

    private String image;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    private Set<Article> articles;

    @Transient
    public String getMainImage() {
        if (image == null) return null;
        return "/"+ Constants.SUB_CATEGORIES_MEDIA_DIR +"/" + image;
    }

}