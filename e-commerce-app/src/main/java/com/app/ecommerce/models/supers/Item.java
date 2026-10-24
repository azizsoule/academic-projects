package com.app.ecommerce.models.supers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    private int quantity = 1;

    private Date createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

}