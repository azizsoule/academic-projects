package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "customer_comment")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;

    private int rating=0;

    private String content;

    private boolean blocked;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "id_article")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }
}