package com.app.ecommerce.models;

import com.app.ecommerce.utils.FilterSytem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
import java.util.logging.Filter;

@Table(name = "comment")
@Entity
@EntityListeners(FilterSytem.class)
@NoArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;

    private int rating;

    private String content;

    private boolean blocked;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "article_id_article")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @PrePersist
    public void prePersist() {
//        FilterSytem filterSytem = new FilterSytem();
//        filterSytem.filter(this);
        createdAt = new Date();
    }
}