package com.app.ecommerce.models;

import com.app.ecommerce.utils.CommentFilter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "customer_comment")
@Entity
@EntityListeners(CommentFilter.class)
@NoArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;

    private float rating = 0;

    private String content;

    private boolean blocked = false;

    @DateTimeFormat(pattern = "yyyy-mm-dd", fallbackPatterns = "yyyy/mm/dd")
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