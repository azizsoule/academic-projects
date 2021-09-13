package com.app.ecommerce.models;

import com.app.ecommerce.utils.Constants;
import com.app.ecommerce.utils.Generator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "article")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticle;

    @Column(length = 50)
    private String label;

    @Type(type ="text")
    private String description;

    @Column(length = 20, unique = true)
    private String sku;

    private float price;

    private int ratingSum = 0;

    private String image;

    private String brand;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private Set<Image> images  = new HashSet<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @OneToOne(orphanRemoval = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_inventory")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "id_sub_category")
    private SubCategory subCategory;

    @ManyToMany(mappedBy = "articles")
    private Set<Discount> discounts = new HashSet<>();

    @OneToOne(mappedBy = "article")
    private CartItem cartItem;

    @OneToOne(mappedBy = "article")
    private OrderItem orderItem;

    @OneToOne(mappedBy = "article")
    private WishItem wishItem;

    @Transient
    public String getMainImage() {
        if (image == null) return null;
        return "/"+ Constants.ARTICLES_MEDIA_DIR +"/" + sku + "/" + image;
    }


    @PrePersist
    public void prePersist() {
        sku = "SKU"+Generator.generate(5);
    }
}